package com.chen.hdfsspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.hdfsspring.service.HdfsOperation;

@Controller
@RequestMapping(value="hfds")
public class HdfsController {
	
	@RequestMapping(value="/operation.action",method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String generateResult(@RequestParam(defaultValue="")String method) throws Exception{
		HdfsOperation hdfso = HdfsOperation.getInstace();
		if(method.equals("mkdir")) {
			hdfso.mkdir();
		} else if(method.equals("deldir")) {
			hdfso.del();
		} else if(method.equals("upload")) {
			hdfso.upload();
		}
		
		return "success";
	}
}
