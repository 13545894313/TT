package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	public void tetsJedisSingle(){
		
		Jedis jedis=new Jedis("10.164.84.220",6379);
		jedis.set("key1","jedis test");
		String string=jedis.get("key1");
		System.out.println(string);
		jedis.close();
	}
	
	@Test
	public void testJedisPool(){
		//创建连接池
		JedisPool jedisPool=new JedisPool("10.164.84.220",6379);
		//从连接池获取对象
		Jedis jedis=jedisPool.getResource();
		String string=jedis.hget("INDEX_CONTENT_REDIS_KEY", "89");
		System.out.println(string);
		jedis.close();
		jedisPool.close();
		
	}
	
	@Test
	public void testjedisCluster(){
		
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("10.164.84.220",7001));
		nodes.add(new HostAndPort("10.164.84.220",7002));
		nodes.add(new HostAndPort("10.164.84.220",7003));
		nodes.add(new HostAndPort("10.164.84.220",7004));
		nodes.add(new HostAndPort("10.164.84.220",7005));
		nodes.add(new HostAndPort("10.164.84.220",7006));
		
		JedisCluster jedisCluster=new JedisCluster(nodes);
		jedisCluster.set("key1", "1000");
		String string =jedisCluster.get("key1");
		System.out.println(string);
		
		jedisCluster.close();
	}
	
	
	//jedis单机版
	@Test
	public void testSpringJedisSingle(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis =pool.getResource();
		String string  = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();		
	}
	
	
	@Test
	public void testSpringJedisCluster(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}

}
