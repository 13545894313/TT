package com.taotao.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;



public class HttpClientTest {
	
	@Test
	public void doGet() throws Exception {
		//创建一个HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个GET请求
		HttpGet get =new HttpGet("http://www.sogou.com");
		//执行请求
		CloseableHttpResponse response =httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string =EntityUtils.toString(entity);
		System.out.println(string);
		//关闭httpClient
		response.close();
		httpClient.close();
		
	}
	
	
	@Test
	public void doGetWithParam() throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		URIBuilder uriBuilder =new URIBuilder("http://localhost:8082/index.html");
		uriBuilder.addParameter("query", "java");
		HttpGet get =new HttpGet(uriBuilder.build());
		
		CloseableHttpResponse response =httpClient.execute(get);
		//取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string =EntityUtils.toString(entity);
		System.out.println(string);
		//关闭httpClient
		response.close();
		httpClient.close();
		
	}
	
	
	
	@Test
	public void doPost() throws Exception{
		CloseableHttpClient httpClient =HttpClients.createDefault();
		HttpPost post= new HttpPost("http://localhost:8082/httpclient/post.html");
		CloseableHttpResponse response =httpClient.execute(post);
		String string =EntityUtils.toString(response.getEntity());
		System.out.println(string);
		//关闭httpClient
		response.close();
		httpClient.close();
	}
	
	
	
	@Test
	public void doPostWithParam() throws Exception{
		CloseableHttpClient httpClient =HttpClients.createDefault();
		HttpPost post= new HttpPost("http://localhost:8082/httpclient/post.action");
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "张三"));
		kvList.add(new BasicNameValuePair("password", "123"));
		StringEntity entity= new UrlEncodedFormEntity(kvList,"utf-8");
		post.setEntity(entity);
		CloseableHttpResponse response =httpClient.execute(post);
		String string =EntityUtils.toString(response.getEntity());
		System.out.println(string);
		//关闭httpClient
		response.close();
		httpClient.close();
		
	}	

}
