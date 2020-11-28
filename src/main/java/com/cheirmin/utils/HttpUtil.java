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
	public String getHttp(String url, int mark, String key) {
		// 获取一页带音乐id的json数据
		StringBuilder body=new StringBuilder();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		if(mark==1) {
			// 设置请求头
			httpGet.setHeader("csrf", "SMFRMYM0XO9");
			httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36");
			httpGet.setHeader("Cookie",
					"_ga=GA1.2.1308832223.1583160106; _gid=GA1.2.1039451114.1583160106; ad_dist=%25D6%25D0%25B9%25FA; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1583218234,1583219113,1583230427,1583300319; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1583300666; _gat=1; kw_token=SMFRMYM0XO9");
			httpGet.setHeader("Host", "www.kuwo.cn");
			httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			httpGet.setHeader("Referer", "http://www.kuwo.cn/search/list?key="+key);
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
			File file = new File("C:/" + musicName + ".mp3");
			outputStream = new FileOutputStream(file);
			// 创建缓冲区
			byte[] buffer = new byte[1024*1024];
			int temp;
			while ((temp = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,temp);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				assert outputStream != null;
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
