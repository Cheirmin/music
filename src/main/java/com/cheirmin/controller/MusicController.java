package com.cheirmin.controller;

import com.cheirmin.pojo.MusicList;
import com.cheirmin.utils.CrawlerMusicFromKuwo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/2 23:21
 * @Version 1.0
 */
@Controller
public class MusicController {

    @RequestMapping({"/index", "/", "/index.html","/musiclist","/search"})
    public String musicList(Integer page, String key, Model model){
        if (page==null || page<=0 ) {
            page = 1;
        }

        String url;
        int type = 1;
        String key1 = key;

        if(key!=null && !"".equals(key.trim())) {
            try {
               key1 = java.net.URLEncoder.encode(key,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            url = "http://www.kuwo.cn/api/www/search/searchMusicBykeyWord?key="+key1+"&pn="+page+"&rn=10";
            type = 3;
        }else {
            key = "";
            url = "http://www.kuwo.cn/api/www/bang/bang/musicList?bangId=93&pn="+page+"&rn=10";
        }

        MusicList musicList = CrawlerMusicFromKuwo.getMusic(url, type,key1);
        //当前页数
        musicList.setPage(page);

        model.addAttribute("musiclist",musicList);
        model.addAttribute("key",key);
        return "musicList";
    }

    @RequestMapping("musicDetail")
    public String musicList(String rid){
        if(rid==null){
            return null;
        }
        String url = CrawlerMusicFromKuwo.getUrlById(rid);
        return "redirect:"+url;
    }
}
