package org.movie.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.movie.cache.UserLoginStatusCache;
import org.movie.model.UserLoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	MemcachedClient memcachedClient;
	 
	@Autowired
	UserLoginStatusCache userLoginStatusCache;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("file") MultipartFile  file) {
		String[] strings = request.getParameterValues("checkbox");
		String fileName=file.getOriginalFilename();
		String path=request.getServletContext().getRealPath("static");
		String filePath=path+"/"+fileName;
		try {
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "upload";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	//有点问题
	@RequestMapping(value="/upload1",method=RequestMethod.POST)
	public String upload1(HttpServletRequest request,HttpServletResponse response,@RequestPart("file") byte[] file,@RequestParam("file") String fileName) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(file);
		FileOutputStream fileOutputStream =null;
		String path=request.getServletContext().getRealPath("static");
		String filePath=path+"/"+fileName;
		File toFile = new File(filePath);
		try {
			byte[] b=new byte[1024];
			fileOutputStream = new FileOutputStream(toFile);
			while((inputStream.read(b))!=-1){
				fileOutputStream.write(b);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "upload";
	}
	
	
	//表单获取测试
	@RequestMapping(value="/upload2",method=RequestMethod.POST)
	public String upload2(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("checkbox") String[] box1,TestBean bean) {
		System.out.println(box1);
		System.out.println(bean.checkbox);
		return "upload";
	}
	
	//重定向（转发）到https 小试
	@RequestMapping("redirect/{num}")
	public String	redirect(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("num") String num,Model model) throws IOException, ServletException {
		model.addAttribute("num", num);
		switch (num) {
		case "1":
			return "redirect:/news/{num}";
		case "2":
			return "forward:/news/2";
		case "3":
			response.sendRedirect("/news/3");//这种会报 Cannot forward after response has been committed错误
		case "4":
			request.getRequestDispatcher("/news/4").forward(request, response);
		default:
			return "redirect:/news/{num}";
		}
		
	}
	
	//memcached demo
	@RequestMapping("setcache")
	public String setMemcached (@RequestParam("key") String key,@RequestParam("value") String value){
		try {
			memcachedClient.set(key, 0, value);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			System.out.println("setMemcached ERROR !!!!!!"+e);
		}
		return "forward:/test/getcache";
	}
	@RequestMapping("getcache")
	public void getMemcached (@RequestParam("key") String key){
		try {
			String val = memcachedClient.get(key,3000);//等待3秒否则抛出超时
			System.out.println(val);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			System.out.println("setMemcached ERROR !!!!!! "+e);
		}
		
	}
	@RequestMapping("sessionID")
	public String sessionID(HttpServletRequest request,Model model){
		String id=request.getSession().getId();
		System.out.println("sessionID="+id);
		model.addAttribute("id", id);
		return "demo";
		
	}
	@RequestMapping(value="redistest1")
	public String redisTest1(HttpServletRequest request,Model model){
		UserLoginStatus userLoginStatus = userLoginStatusCache.getCache("14");
		System.out.println(JSON.toJSONString(userLoginStatus));
		return "demo";
	}
	
	
	
}
class TestBean{
	String [] checkbox;

	public String[] getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}
	
}
