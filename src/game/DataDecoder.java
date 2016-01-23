package game;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class DataDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf arg1, List<Object> arg2){
		System.out.println("Decoder");
		byte[] bytes = new byte[arg1.readableBytes()];
		arg1.readBytes(bytes);
		
		
		arg2.add(bytes);
		
	}

}
