package com.chen.hdfsspring.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HdfsOperation {
	private static HdfsOperation instance;
	
	private void HdfsOperation(){
		
	}
	public static HdfsOperation getInstace(){
		if(instance == null) {
			instance = new HdfsOperation();
		}
		return instance;
	}
	String path = "hdfs://localhost:9000";
	
	public void mkdir() throws Exception {
		FileSystem fs = FileSystem.get(new URI(path),new Configuration());
		fs.mkdirs(new Path("/upload"));
		fs.close();
	}
	
	public void upload() throws Exception{
		FileSystem fs = FileSystem.get(new URI(path), new Configuration());
		FSDataOutputStream fsDataOutputStream = fs.create(new Path("/upload/profile"));
		FileInputStream in = new FileInputStream(new File("/etc/profile"));
		IOUtils.copyBytes(in, fsDataOutputStream, 2048, true);
		fs.close();
	}
	
	public void del() throws Exception{
		FileSystem fs = FileSystem.get(new URI(path), new Configuration());
		fs.delete(new Path("/upload"), true);
		fs.close();
	}
	
}
