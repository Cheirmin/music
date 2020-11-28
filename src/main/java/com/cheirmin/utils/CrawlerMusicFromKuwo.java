package com.cheirmin.utils;

import com.alibaba.fastjson.JSON;
import com.cheirmin.pojo.Music;
import com.cheirmin.pojo.MusicList;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
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
     * @param rid 音乐id
     * @return return
     */
    public static String getUrlById(String rid){
        String url = "http://www.kuwo.cn/url?format=mp3&rid="+ rid +"&response=url&type=convert_url3&br=128kmp3&from=web&t=1561517488040";
        try {
            Document document = Jsoup.connect(url).get();
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
     * @param type 类型，1 默认， 2 歌手页 3 直接搜索结果
     * @return return
     */
    public static MusicList getMusic(String url, int type, String key){
        if (key==null){ key="";}

        HttpUtil httpUtil = new HttpUtil();
        //存放音乐
        MusicList musicList = new MusicList();
        //获取响应体
        String body = httpUtil.getHttp(url,1,key);

        //json字符串转对象
        Map map = JSON.parseObject(body,Map.class);
        Map<String,Object> data= (Map<String, Object>) map.get("data");

        //总页数
        String sum = "";

        String listName ;
        switch (type){
            case 1 :
                listName = "musicList";
                if (data ==null ){
                    return musicList;
                }
                sum = (String) data.get("num");
                break;
            case 2 : listName = "list";break;
            case 3 :
                listName = "list";
                if (data ==null ){
                    return musicList;
                }
                sum = (String) data.get("total");
                break;
            default: listName = "musicList";
        }

        Double sumPage = Math.ceil(Double.parseDouble(sum)/10);
        musicList.setSumPage(sumPage);

        List<Map<String,Object>> musics= (List<Map<String, Object>>) data.get(listName);
        if (musics == null || musics.isEmpty()){
            return musicList;
        }

        List<Music> musicList1 = new ArrayList<>();
        //将当前页的音乐id、作者和音乐名按照顺序存入集合中
        for (Map<String, Object> music : musics) {
            String rid = music.get("rid").toString();
            String pic = null;
            if(music.get("pic") != null){
                pic= music.get("pic").toString();
            }
            String time = music.get("songTimeMinutes").toString();
            String musicName = music.get("name").toString();
            String singerName = music.get("artist").toString();
            String album = music.get("album").toString();

            Music musicInfo = new Music(rid,musicName,singerName,pic,time,album);
            musicList1.add(musicInfo);
        }
        musicList.setMusicList(musicList1);
        return musicList;
    }
}
