package game;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

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
	
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception{
		System.out.println(arg0.channel().remoteAddress()+"handlerAdded");
		
		
	}
	
	public void handlerRemoved(ChannelHandlerContext arg0) throws Exception{
		System.out.println(arg0.channel().remoteAddress()+"handlerRemoved");
		
		
	}
	
	
	protected void channelIdle(ChannelHandlerContext arg0, IdleStateEvent evt) throws Exception{
		System.out.println(arg0.channel().remoteAddress()+"channelIdle");
		
		
	}
	
}