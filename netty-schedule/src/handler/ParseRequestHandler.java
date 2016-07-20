package handler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import util.ParseJsonStr;
import job.CommonJobFactory;
import job.CompileJobFactory;
import job.Job;
import job.JobFactory;
import net.sf.json.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.CharsetUtil;

public class ParseRequestHandler extends ChannelHandlerAdapter{

	private FullHttpRequest request;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		System.out.println("received message");
		
		if(msg instanceof FullHttpRequest)
			request = (FullHttpRequest)msg;
		
		Job job = null;
		JobFactory jobFactory = null;
		
		String jsonStr = parseJosnRequest(request);
		String type = getJobType(jsonStr);
		if(type.equals("0")){
			jobFactory = CompileJobFactory.createInstance();
			job = jobFactory.createJob(jsonStr);
		}else if(type.equals("1")){
			jobFactory = CommonJobFactory.createInstance();
			job = jobFactory.createJob(jsonStr);
		}else{
			throw new Exception("rquest param illegal");
		}
//		String jobJsonStr = ParseJsonStr.Obj2Str(job);
		ctx.fireChannelReadComplete();
//		ctx.writeAndFlush(Unpooled.copiedBuffer(jobJsonStr, CharsetUtil.UTF_8));//传递给下一个Handler
		ctx.fireChannelRead(job);
	}
	
	  @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) {
	        ctx.flush();
	    }
	  
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        cause.printStackTrace();
	        ctx.close();
	    }
	
	
	//parse job type 0,1
	private String getJobType(String jsonStr) throws IOException{
		JSONObject jsonObj = ParseJsonStr.createJsonObj(jsonStr);
		String jobType = jsonObj.getString("jobType");
		return jobType;
	}
	
	
	private String parseJosnRequest(FullHttpRequest request){
		ByteBuf jsonBuf = request.content();
		String jsonStr = jsonBuf.toString(CharsetUtil.UTF_8);
		return jsonStr;
	}
}