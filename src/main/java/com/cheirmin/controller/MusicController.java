package com.cheirmin.controller;

import com.cheirmin.pojo.Music;
import com.cheirmin.service.MusicService;
import com.cheirmin.utils.CrawlerMusicFromKuwo;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/2 23:21
 * @Version 1.0
 */
@Controller
public class MusicController {

    @Resource
    private MusicService musicService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(){
        System.out.println("indexPage");
        return "index";
    }

    @GetMapping("musiclist")
    public String musicList(String singerId,Integer page, Model model){

        if (page==null) page = 1;

        String url ;
        Integer type = 1;
        if (singerId != null && !singerId.equals("") && !singerId.equals("null")){
            url = "http://www.kuwo.cn/api/www/artist/artistMusic?artistid="+singerId+"&pn="+page+"&rn=30";
            type = 2;
        }else {
            url = "http://www.kuwo.cn/api/www/bang/bang/musicList?bangId=93&pn="+page+"&rn=30";
        }

        List<Music> music = CrawlerMusicFromKuwo.getMusic(url, type);
        System.out.println("music--"+music.get(0));
        model.addAttribute("musiclist",music);
        model.addAttribute("page",page);
        model.addAttribute("singerId",singerId);
        return "musicList";
    }
}
