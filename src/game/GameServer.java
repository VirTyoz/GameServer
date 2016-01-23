package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import game.service.GameService;
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
		int databaseServicePort = 6067;
		String databaseServoceIP = "127.0.0.1";
		String loginServerIP= "127.0.0.1";
		
		
		
		//rpc
   	 	TTransport transport = new TSocket(databaseServoceIP, databaseServicePort);
   	 	transport.open();
   		TProtocol protocol = new TBinaryProtocol(transport);
	 	GameService.Client clientService = new GameService.Client(protocol);
	 	
	 	GalaxyInitializer galaxyInitializer = new GalaxyInitializer(clientService);
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
			try {
				ServerBootstrap s = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.handler(new LoggingHandler(LogLevel.INFO))
				.channel(NioServerSocketChannel.class)
				.childHandler(new ClientInitializer(galaxyInitializer));
				
				Bootstrap c = new Bootstrap()
				.group(bossGroup)
				.channel(NioSocketChannel.class)
				.handler(new ServerInitializer());
				
				
		
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
