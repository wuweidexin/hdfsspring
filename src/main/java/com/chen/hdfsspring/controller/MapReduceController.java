package com.chen.hdfsspring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.hdfsspring.service.mapreduce.WordCount;

@Controller
@RequestMapping(value="mapreduce")
public class MapReduceController {
	Logger logger = LoggerFactory.getLogger(MapReduceController.class);
	@RequestMapping(value="/wordcount.action",method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public @ResponseBody String generateResult(@RequestParam(defaultValue="")String input, @RequestParam(defaultValue="")String output) throws Exception{
		try {
			WordCount v = WordCount.getInstance();
			v.doJob(input, output);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "success";
	}
}
