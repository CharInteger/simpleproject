package pro.simpleproject.core.intra.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.IdleStateHandler;

public class Client {

	private String host;
	private int port;
	private EventLoopGroup group;
	private Channel channel;
	private SslContext sslCtx;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
		group = new NioEventLoopGroup();
		try {
			sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline p = ch.pipeline();
				p.addLast(sslCtx.newHandler(ch.alloc()));
				p.addLast(new IdleStateHandler(5, 0, 0));
			}
		});
		try {
			channel = b.connect(host, port).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean run(Object o) {
		if (channel == null || !channel.isActive()) {
			init();
		}
		channel.writeAndFlush(o);
		return true;
	}

	public void close() {
		if (channel != null) {
			channel.close();
		}
		if (group != null) {
			group.shutdownGracefully();
		}
	}

}
