package com.cheirmin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpUtil {
	//获取响应体
	public String getHttp(String url, int mark) {
		// 获取一页带音乐id的json数据
		StringBuilder body=new StringBuilder();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		if(mark==1) {
			// 设置请求头
			httpGet.setHeader("csrf", "AOPHIP5DF2M");
			httpGet.setHeader("Cookie",
					"_ga=GA1.2.207336405.1578105222; _gid=GA1.2.1290422414.1578105222; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1578105222,1578105233,1578123281; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1578123667; _gat=1; kw_token=AOPHIP5DF2M");
		}
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			String s;
			while ((s = reader.readLine()) != null) {
				body.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return body.toString();
	}

	//下载音乐
	public void DownLoadMusic(String url, String musicName) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response;
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			response = httpClient.execute(httpGet);
			inputStream = response.getEntity().getContent();
			// 创建输出流
			File file = new File("C:/Users/Edward/Desktop/music/" + musicName + ".mp3");
			outputStream = new FileOutputStream(file);
			// 创建缓冲区
			byte[] buffer = new byte[1024*1024];
			int temp;
			while ((temp = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				assert outputStream != null;
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
