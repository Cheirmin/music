package com.cheirmin.pojo;

/**
 * @Message:
 * @Author：Cheirmin
 * @Date: 2020/3/2 23:16
 * @Version 1.0
 */
public class Music {
    private String id;
    private String musicName;
    private String singerName;
    private String musicPhotoUrl;
    private String musicWords;
    private String musicTime;
    private String album;

    public Music() {
    }

    public Music(String id, String musicName, String singerName, String musicPhotoUrl, String musicTime, String album) {
        this.id = id;
        this.musicName = musicName;
        this.singerName = singerName;
        this.musicPhotoUrl = musicPhotoUrl;
        this.musicTime = musicTime;
        this.album = album;
    }

    public Music(String id, String musicName, String singerName, String musicPhotoUrl, String musicWords, String musicTime,String album) {
        this.id = id;
        this.musicName = musicName;
        this.singerName = singerName;
        this.musicPhotoUrl = musicPhotoUrl;
        this.musicWords = musicWords;
        this.musicTime = musicTime;
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getMusicPhotoUrl() {
        return musicPhotoUrl;
    }

    public void setMusicPhotoUrl(String musicPhotoUrl) {
        this.musicPhotoUrl = musicPhotoUrl;
    }

    public String getMusicWords() {
        return musicWords;
    }

    public void setMusicWords(String musicWords) {
        this.musicWords = musicWords;
    }

    public String getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime = musicTime;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id='" + id + '\'' +
                ", musicName='" + musicName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", musicPhotoUrl='" + musicPhotoUrl + '\'' +
                ", musicWords='" + musicWords + '\'' +
                ", musicTime='" + musicTime + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
