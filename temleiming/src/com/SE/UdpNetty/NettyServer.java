/**
 * 
 */
package com.SE.UdpNetty;

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
	
	private String m_ip;

	public NettyServer(String ip,int port) {
		this.port = port;
		this.m_ip = ip;
		bind();
	}

	private void bind() {
		
		EventLoopGroup worker = new NioEventLoopGroup();

		try {
			Bootstrap udpBoot = new Bootstrap();
			udpBoot.group(worker);
			udpBoot.channel(NioDatagramChannel.class);
			udpBoot.option(ChannelOption.SO_BROADCAST,true);
			udpBoot.handler(new NettyServerHandler());
			
			ChannelFuture udpF = udpBoot.bind(this.m_ip,this.port);
			if (udpF.isSuccess()){
				logger.debug("����Netty����ɹ����˿ںţ�" + this.port);
			}
			// �ر�����
			udpF.sync().channel().closeFuture().await();
			logger.debug("�ر�Netty");

		} catch (Exception e) {
			logger.error("����Netty�����쳣���쳣��Ϣ��" + e.getMessage());
			e.printStackTrace();
		} finally {
			worker.shutdownGracefully();
		}
	}
}
