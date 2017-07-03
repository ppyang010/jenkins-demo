package org.movie.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.movie.model.News;
import org.movie.model.PageBean;
import org.movie.service.INewsService;
import org.movie.service.IndexService;
import org.movie.tools.MySessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	IndexService indexService;
	
	@Autowired
	INewsService newsService;
	

	@RequestMapping(value={"/index","/"})
	public String index(Model model,HttpServletRequest request) {
		PageBean<News> pageBean = newsService.getNewsListByPage(1, 10);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("sessionCount",MySessionContext.getInstance().getSessionCount());
		model.addAttribute("test",null);
		request.getSession().setAttribute("hello", "hello");
		return "index";
	}
	
	

}
