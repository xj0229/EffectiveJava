/**
 * 
 */
package com.SE.NettyServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author 03010213
 *
 */
public class KeyBoardHandler extends ChannelInboundHandlerAdapter{
	
	@Override  
	  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
		ByteBuf buf = (ByteBuf)msg;
		byte[] receiveMsg = new byte[buf.readableBytes()];
		buf.readBytes(receiveMsg);
		buf.clear();
		System.out.print(" ’µΩ"+receiveMsg);
	}
}
