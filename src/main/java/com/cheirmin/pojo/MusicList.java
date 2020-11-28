package com.cheirmin.pojo;

import java.util.List;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/4 15:53
 * @Version 1.0
 */
public class MusicList {
    private List<Music> musicList;
    private String keyWord;
    private Double sumPage;
    private Integer page;

    public MusicList() {
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public Double getSumPage() {
        return sumPage;
    }

    public void setSumPage(Double sumPage) {
        this.sumPage = sumPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
