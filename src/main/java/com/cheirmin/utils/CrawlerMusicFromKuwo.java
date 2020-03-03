package com.cheirmin.utils;

import com.alibaba.fastjson.JSON;
import com.cheirmin.pojo.Music;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/3 14:29
 * @Version 1.0
 */
public class CrawlerMusicFromKuwo {
    /**
     * 通过音乐id获取其MP3文件地址
     * @param rid
     * @return
     */
    public static String getUrlById(String rid){
        String url = "http://www.kuwo.cn/url?format=mp3&rid="+ rid +"&response=url&type=convert_url3&br=128kmp3&from=web&t=1561517488040";
        try {
            Document document = Jsoup.connect(url).get();
//            System.out.println(document.select("body").html());
            JSONObject jsonObject = new JSONObject(document.select("body").html());
            return jsonObject.get("url").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url 获取的歌单
     * @param type 类型，1 排名版， 2 歌手页
     * @return
     */
    public static List<Music> getMusic(String url,int type){

        HttpUtil httpUtil = new HttpUtil();
        List<Music> musicList = new ArrayList<>();//存放音乐

        //获取响应体
        String body = httpUtil.GetHttp(url,1);

        //json字符串转对象
        Map<String,Object> map = JSON.parseObject(body,Map.class);
        Map<String,Object> data= (Map<String, Object>) map.get("data");

        String ss ;
        switch (type){
            case 1 : ss = "musicList";break;
            case 2 : ss = "list";break;
            default: ss = "musicList";
        }
        List<Map<String,Object>> musics= (List<Map<String, Object>>) data.get(ss);

        //将当前页的音乐id、作者和音乐名按照顺序存入集合中
        for (Map<String, Object> music : musics) {
            String rid = music.get("rid").toString();
            String pic = music.get("pic").toString();
            String time = music.get("songTimeMinutes").toString();
            String musicName = music.get("name").toString();
            String singerName = music.get("artist").toString();
            String album = music.get("album").toString();
            String musicUrl = getUrlById(rid);

            Music musicInfo = new Music(rid,musicName,singerName,musicUrl,pic,time,album);
            musicList.add(musicInfo);

            System.out.println("musicInfo--"+musicInfo);
        }
        return musicList;
    }
}
