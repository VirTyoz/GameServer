package game;

import org.apache.thrift.TException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<byte[]>{
	
	public void channelActive(ChannelHandlerContext arg0) throws TException{
    	System.out.println(arg0.channel().remoteAddress()+"connacted");
    	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, byte[] arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
