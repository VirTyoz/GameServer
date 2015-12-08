package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class GameServer {
	
	private static final Logger logger = LogManager.getLogger(GameServer.class);
	
	public static void main(String[] args) throws Exception{
		int gameServerPort = 3167;
		int loginServerConnectionPort = 3190;
		String loginServerIP="127.0.0.1";
		
		new GalaxyInitializer();
		
		//rpc
   	 	/*TTransport transport = new TSocket(databaseServoceIP, databaseServicePort);
   	 	transport.open();
   		TProtocol protocol = new TBinaryProtocol(transport);
	 	LoginService.Client clientService = new LoginService.Client(protocol);*/
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
			try {
				ServerBootstrap s = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.handler(new LoggingHandler(LogLevel.INFO))
				.channel(NioServerSocketChannel.class)
				.childHandler(new ServerInitializer());
				
				Bootstrap c = new Bootstrap()
				.group(bossGroup)
				.channel(NioSocketChannel.class)
				.handler(new ClientInitializer());
				
				
		
				//start Server
				ChannelFuture sf = s.bind(gameServerPort).sync();
				
				//startClient
				ChannelFuture cf = c.connect(loginServerIP,loginServerConnectionPort).sync();
				
				sf.channel().closeFuture().sync();
				cf.channel().closeFuture().sync();
			} finally {
				workerGroup.shutdownGracefully();
	            bossGroup.shutdownGracefully();
			}

	}
}
