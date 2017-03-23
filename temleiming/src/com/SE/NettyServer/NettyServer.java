/**
 * 
 */
package com.SE.NettyServer;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author Administrator
 *
 */
public class NettyServer {
	private static Logger logger = Logger.getLogger(NettyServer.class);

	private int port;

	public NettyServer(int port) {
		this.port = port;
		bind();
	}

	private void bind() {
		
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		try {
			ServerBootstrap keyBoot = new ServerBootstrap();
			keyBoot.group(boss,worker);
			keyBoot.channel(NioServerSocketChannel.class);
			keyBoot.option(ChannelOption.SO_BACKLOG, 1024);
			keyBoot.childHandler(new ChildChannelHandler()); 
			
			ChannelFuture tcpF = keyBoot.bind("192.168.0.155",this.port).sync();
			
			
			
			if (tcpF.isSuccess()){
				logger.debug("����Netty����ɹ����˿ںţ�" + this.port);
			}
			// �ر�����
			tcpF.channel().closeFuture().sync();
			logger.debug("�ر�Netty");

		} catch (Exception e) {
			logger.error("����Netty�����쳣���쳣��Ϣ��" + e.getMessage());
			e.printStackTrace();
		} finally {
			worker.shutdownGracefully();
		}
	}
	
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{  
        @Override  
        protected void initChannel(SocketChannel ch) throws Exception {  
        	ChannelPipeline p = ch.pipeline();
        	logger.debug("��ʼ��ͨ��");
            p.addLast(new KeyBoardHandler());  
        }  
    }  
}
