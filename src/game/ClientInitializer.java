package game;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		
ChannelPipeline pipeline = arg0.pipeline();
		
		pipeline.addLast(new ConnectionHandler());
		pipeline.addLast(new DataDecoder());
		
		pipeline.addLast(new ClientHandler());
		
	}

}
