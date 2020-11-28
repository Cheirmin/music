package com.cheirmin.controller;

import com.cheirmin.pojo.Music;
import com.cheirmin.utils.CrawlerMusicFromKuwo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/2 23:21
 * @Version 1.0
 */
@Controller
public class MusicController {

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(){
        return "index";
    }

    @GetMapping("musiclist")
    public String musicList(String singerId,Integer page, Model model){
        if(page==null){
            page = 1;
        }

        String url ;
        int type = 1;
        if (singerId != null && !"".equals(singerId) && !"null".equals(singerId)){
            url = "http://www.kuwo.cn/api/www/artist/artistMusic?artistid="+singerId+"&pn="+page+"&rn=30";
            type = 2;
        }else {
            url = "http://www.kuwo.cn/api/www/bang/bang/musicList?bangId=93&pn="+page+"&rn=30";
        }

        List<Music> music = CrawlerMusicFromKuwo.getMusic(url, type);
        model.addAttribute("musiclist",music);
        model.addAttribute("page",page);
        model.addAttribute("singerId",singerId);
        return "musicList";
    }

    @GetMapping("music")
    public String musicList(String rid){
        if(rid==null){
            return null;
        }
        String url = CrawlerMusicFromKuwo.getUrlById(rid);
        return "redirect:"+url;
    }
}
