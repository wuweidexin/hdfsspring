package com.chen.hdfsspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.hdfsspring.service.mapreduce.WordCount;

@Controller
@RequestMapping(value="mapreduce")
public class MapReduceController {
	
	@RequestMapping(value="/wordcount.action",method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String generateResult(@RequestParam(defaultValue="")String input, @RequestParam(defaultValue="")String output) throws Exception{
		WordCount v = WordCount.getInstance();
		v.doJob(input, output);
		return "success";
	}
}
