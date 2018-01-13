package com.chen.hdfsspring.service.hdfs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HdfsOperation {
	Logger logger  = LoggerFactory.getLogger(HdfsOperation.class);
	private static HdfsOperation instance;
	
	private HdfsOperation(){
		
	}
	public static HdfsOperation getInstace(){
		if(instance == null) {
			instance = new HdfsOperation();
		}
		return instance;
	}
	String rootpath = "hdfs://localhost:9000";
	
	public void mkdir() throws Exception {
		FileSystem fs = FileSystem.get(new URI(rootpath),new Configuration());
		fs.mkdirs(new Path("/upload"));
		fs.close();
	}
	
	public void upload() throws Exception{
		FileSystem fs = FileSystem.get(new URI(rootpath), new Configuration());
		FSDataOutputStream fsDataOutputStream = fs.create(new Path("/upload/profile"));
		FileInputStream in = new FileInputStream(new File("/etc/profile"));
		IOUtils.copyBytes(in, fsDataOutputStream, 2048, true);
		fs.close();
	}
	
	public void del() throws Exception{
		FileSystem fs = FileSystem.get(new URI(rootpath), new Configuration());
		fs.delete(new Path("/upload"), true);
		fs.close();
	}
	
	public void readData(String path) throws Exception {
		FileSystem fs = FileSystem.get(new URI(rootpath), new Configuration());
		InputStream in = null;
		in = fs.open(new Path(rootpath+path));
		IOUtils.copyBytes(in, System.out, 4096, false);
		IOUtils.closeStream(in);
	}
	
	public void writeData(String file, String dest) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		FileSystem fs = FileSystem.get(URI.create(rootpath+ dest), new Configuration());
		OutputStream out = fs.create(new Path(dest), new Progressable() {

			@Override
			public void progress() {
				// 展示进度
				logger.info(".");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
		IOUtils.closeStream(in);
		IOUtils.closeStream(out);
	}
}
