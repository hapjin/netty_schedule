package handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


public class HttpPipelineInitializer extends ChannelInitializer<Channel>{
	private final boolean isClient;
	
	public HttpPipelineInitializer(boolean isClient) {
		this.isClient = isClient;
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if(isClient){
			pipeline.addLast("decoder", new HttpResponseDecoder());
			pipeline.addLast("encoder", new HttpResponseEncoder());
		}
		else{
			pipeline.addLast("decoder", new HttpRequestDecoder());
			pipeline.addLast("encoder", new HttpRequestEncoder());
			System.out.println("add HttpRequestDecoder");
		}
	}
}
