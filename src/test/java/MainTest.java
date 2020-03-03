import com.cheirmin.pojo.Music;
import com.cheirmin.utils.CrawlerMusicFromKuwo;
import org.junit.Test;

import java.util.List;

/**
 * @Message:
 * @Author£ºCheirmin
 * @Date: 2020/3/2 23:01
 * @Version 1.0
 */
public class MainTest {
    @Test
    public void test01(){
//        String musicUrl = CrawlerMusicFromKuwo.getUrlById(40079875);
//        String musicUrl = CrawlerMusicFromKuwo.getUrlById(63789244);

//        System.out.println(musicUrl);

//        CrawlerMusicFromKuwo.getMusic("http://www.kuwo.cn/api/www/bang/bang/musicList?bangId=93&pn=1&rn=30",1);

        CrawlerMusicFromKuwo.getMusic("http://www.kuwo.cn/api/www/artist/artistMusic?artistid=336&pn=1&rn=30",2);
    }
}
