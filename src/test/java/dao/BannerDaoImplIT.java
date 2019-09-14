package dao;

import boot.App;
import boot.dao.BannerDaoImpl;
import boot.entities.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;


/*Используя специальный runner  SpringJUnit4ClassRunner, мы
 инициализируем Spring контест автоматически при запуске теста*/
@RunWith(SpringJUnit4ClassRunner.class)
/*Аннотация  @ContextConfiguration указывает, как именно мы хотим
сконфигурировать контекст.*/
@ContextConfiguration(loader= AnnotationConfigContextLoader.class,
        classes = App.class)
@Sql("/banners-init.sql")

public class BannerDaoImplIT
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    BannerDaoImpl testedObject = new BannerDaoImpl();

    @Test
    public void testFillBannerIsNull()
    {
        Map<String, Object> source = new HashMap<String, Object>();
        assertNull(testedObject.fillBanner(source));
    }

    @Test
    public void testFillBanner()
    {
        Banner expected = new Banner(1, "TEST", 2, 3,
                "TEST2", "TEST3");

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("banner_id", 1);
        source.put("img_src", "TEST");
        source.put("width", 2);
        source.put("height", 3);
        source.put("target_url", "TEST2");
        source.put("lang_id", "TEST3");

        assertEquals(expected, testedObject.fillBanner(source));
    }

    @Test
    public void testGetAllBanners()
    {
        Banner expected = new Banner(1, "TEST", 2, 3,
                "TEST2", "TEST3");

        List<Banner> actual = testedObject.getAllBanners();
        System.out.println(actual.get(0));
        assertEquals(expected, actual.get(0));
    }
}
