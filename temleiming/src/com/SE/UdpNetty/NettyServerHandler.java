/**
 * 
 */
package com.SE.UdpNetty;

import java.awt.geom.FlatteningPathIterator;
import java.io.UnsupportedEncodingException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.SE.Manscdp.KeyboardTimer;
import com.SE.Manscdp.Parser2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.ChannelHandlerContext;
/**
 * @author Administrator
 *
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
	
	private byte[] m_keyBoardHead = new byte[]{85,-86,85};
	KeyboardTimer kt =new KeyboardTimer(800);	
	Parser2 m_keyParser = new Parser2();
	Fliter m_fliter=new Fliter();
	
	//*******************************************
//	private Boolean RorD = false;
//	public Boolean getRorD(){
//		return this.RorD;
//	}
	//*******************************************
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		DatagramPacket dp = (DatagramPacket) msg;
		ByteBuf buf = dp.content();
		
		byte[] receiveMsg = new byte[buf.capacity()];
		buf.getBytes(0, receiveMsg);
		buf.clear();
		byte[] head = new byte[3];
		System.arraycopy(receiveMsg, 0, head, 0, 3);
		boolean key = Arrays.equals(head, m_keyBoardHead);
		if (key){
			//call Lei Ming method
			byte[]actualMsg = new byte[receiveMsg.length-4];
			System.arraycopy(receiveMsg, 4, actualMsg, 0, actualMsg.length);
			String content = new String(actualMsg);
			System.out.println(content);
			//String b=m_fliter.msgflitration2(content);
			m_keyParser.msghandle2(content);
			String a=m_fliter.msgflitration(content);
			System.out.println(a);	
			//this.RorD = true;
			m_keyParser.msghandle(a);
			kt.stopTimer();
			kt.startTimer();
			
			
		}
		System.out.println("服务器接收到消息：" + receiveMsg.toString());
	}
	
	
}
