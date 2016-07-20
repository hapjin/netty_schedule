package handler;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import job.Job;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class OozieRequestHandler extends ChannelHandlerAdapter {

	private static final String OOZIE_SUBMIT_URI = "http://192.168.121.35:11000/oozie/v1/jobs";

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		System.out.println("receive job");
		if (msg instanceof Job) {
			Job job = (Job) msg;
			String jobConfig = job.getJobConfig();
			
			String jobId = doPost(jobConfig);
			FullHttpResponse response = new DefaultFullHttpResponse(
					HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
					Unpooled.wrappedBuffer(jobId.getBytes()));

			response.headers().set(CONTENT_TYPE, "application/xml");
			
			response.headers().setInt(CONTENT_LENGTH,
					response.content().readableBytes());
			ctx.write(response).addListener(ChannelFutureListener.CLOSE);
		}
	}

	private String doPost(String reqXml) {
		HttpPost httpPost = new HttpPost(OOZIE_SUBMIT_URI);
		URIBuilder uriBuilder = new URIBuilder(httpPost.getURI()).setParameter(
				"action", "start");
		httpPost.addHeader("Content-Type", "application/xml");
		StringEntity entity = null;
		try {
			entity = new StringEntity(reqXml, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			httpPost.setURI(uriBuilder.build());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		httpPost.setEntity(entity);// http post with xml data
		return sendHttpPost(httpPost);
	}

	private String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(httpPost);
			entity = httpResponse.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {

		} finally {
			try {
				if (httpResponse != null)
					httpResponse.close();
				if (httpClient != null)
					httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
}
