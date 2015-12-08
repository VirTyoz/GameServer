package game;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ConnectionHandler extends ChannelInboundHandlerAdapter{
	
	public void channelRegistered(ChannelHandlerContext arg0) throws Exception{
		System.out.println(arg0.channel().remoteAddress()+"Registered");
	}
	
	public void channelUnregistered(ChannelHandlerContext arg0) throws Exception{
		System.out.println(arg0.channel().remoteAddress()+"Unregistered");
	}
	
	public void channelInactive(ChannelHandlerContext arg0) throws Exception{
		System.out.println(arg0.channel().remoteAddress()+"Inactive");
	}
	
}