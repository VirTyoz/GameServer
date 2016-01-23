package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;

import game.pocket.PocketId;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<byte[]>{
	public volatile PocketId pocketId;
	public volatile Client client;
	private TDeserializer deserializer;
	public volatile GalaxyInitializer galaxyInitializer;
	
	private static final Logger logger = LogManager.getLogger(ClientHandler.class);
	
	public ClientHandler(GalaxyInitializer galaxyInitializer){
		pocketId = new PocketId();
		client = new Client(galaxyInitializer);
		
		this.galaxyInitializer = galaxyInitializer;
		deserializer = new TDeserializer(new TBinaryProtocol.Factory());
	}
	
	public void channelActive(ChannelHandlerContext arg0) throws TException{
    	System.out.println(arg0.channel().remoteAddress()+"connacted");
    	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, byte[] arg1) throws Exception {
		deserializer.deserialize(pocketId, arg1);
		logger.info("PocketId Ok"+pocketId+arg1);
		
		switch(pocketId.id){
        case 1:
        	logger.info("RequestPlanetInfo Ok");
        	arg0.writeAndFlush(client.sendPlanetInfo(arg1));
        	break;
        default:
        	break;
        }
		
	}

}
