package com.cheirmin.pojo;

import lombok.Data;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/2 23:16
 * @Version 1.0
 */
@Data
public class Music {
    private String id;
    private String musicName;
    private String singerName;
    private String musicUrl;
    private String musicPhotoUrl;
    private String musicWords;
    private String musicTime;
    private String album;

    public Music(String id, String musicName, String singerName, String musicPhotoUrl, String musicTime, String album) {
        this.id = id;
        this.musicName = musicName;
        this.singerName = singerName;
        this.musicPhotoUrl = musicPhotoUrl;
        this.musicTime = musicTime;
        this.album = album;
    }
}
