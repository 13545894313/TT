package com.taotao.controller;


import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {
	
	

	
	
	@Test
	public void testFtpUtil()throws Exception{
		 FileInputStream fileInputStream =new FileInputStream(new File("C:\\Users\\Administrator.PC-20160729CAPM\\Pictures\\1.jpg"));
		 FtpUtil.uploadFile("10.164.84.220", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/","/2018/01/22", "gaigeming.jpg", fileInputStream);  
	
	}
	
	
}
