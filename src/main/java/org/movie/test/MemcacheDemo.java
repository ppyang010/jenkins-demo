package org.movie.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class MemcacheDemo {
	
	public static void main (String[] str) {
		XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.101.178:11212"));
		try {
			MemcachedClient memcachedClient = builder.build();
			memcachedClient.set("hello", 0, "world");//set操作 设置（添加更新） 参数 key  超时时间（单位为s） 0为永久
			String val=memcachedClient.get("hello");//get操作 获取
			System.out.println("hello="+val+"---------");
			memcachedClient.delete("hello");
			val = memcachedClient.get("hello",3000);//等待3秒否则抛出超时
			System.out.println("hello=" + val);
			//close memcached client
      		memcachedClient.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		}finally {
			
		}
//		String s1 = "a";
//		String s2 = s1 + "b";
//		String s3 = "a" + "b";
//		String s4="ab";
//		System.out.println(s2 == "ab");
//		System.out.println(s3 == "ab");
//		System.out.println(s3 == s4);
	}

}
