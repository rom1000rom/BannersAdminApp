package services;

import boot.App;
import boot.dao.BannerDAOImpl;
import boot.model.Banner;
import boot.model.BannerChange;
import boot.services.BannersAdminService;
import boot.services.BannersAdminServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;


/*Используя специальный runner  SpringJUnit4ClassRunner, мы
 инициализируем Spring контест автоматически при запуске теста*/
@RunWith(SpringJUnit4ClassRunner.class)
/*Аннотация  @ContextConfiguration указывает, как именно мы хотим
сконфигурировать контекст.*/
@ContextConfiguration(loader= AnnotationConfigContextLoader.class,
        classes = App.class)
@SqlGroup({
        @Sql("/banners-table.sql"),
        @Sql("/banners-change-table.sql"),
        @Sql("/banners-change-data.sql"),
        @Sql("/banners-data.sql")})//Выполняем перед каждым тестом инициализирующие sql-скрипты
@ActiveProfiles("test")//Активизируем профиль для тестирования
public class BannersAdminServiceImplIT
{
    @Autowired
    BannersAdminService testedObject = new BannersAdminServiceImpl();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @After
    public void tearDown()
    {
        //Метод dropTables удаляет указанную таблицу
        //JdbcTestUtils.deleteFromTables() очищает таблицы, не удаляя их
        //JdbcTestUtils.countRowsInTable() подсчитывает текущее количество строк в таблице
        JdbcTestUtils.dropTables(jdbcTemplate,  "banners", "banners_changes");
    }

    @Test
    public void testAddBannerIsNull()
    {
        assertNull(testedObject.addBanner(null));
    }

    @Test
    public void testAddBanner()
    {
        Banner expectedBanner = new Banner(3, "TEST", 3, 3,
                "TEST2", "TEST3");

        BannerChange expectedBannerChange = new BannerChange(5, 3, 1,
                BannersAdminService.CREATE, expectedBanner.toString(),
                LocalDateTime.now());

        Integer id = testedObject.addBanner(expectedBanner);
        LocalDateTime localDateTime = testedObject.getBannersChanges(3, 2).get(0).getDateChange();
        assertEquals(expectedBanner, testedObject.getBanner(id));
        expectedBannerChange.setDateChange(localDateTime);
        assertEquals(expectedBannerChange, testedObject.getBannersChanges(id, 2).get(0));
    }

    @Test
    public void testDeleteBannerIsNull()
    {
        assertNull(testedObject.deleteBanner(null));
    }

    @Test
    public void testDeleteBannerNotExist()
    {
        assertNull(testedObject.deleteBanner(0));
        assertTrue(testedObject.getBannersChanges(5, 1).isEmpty());
    }

    @Test
    public void testDeleteBanner()
    {
        BannerChange expectedBannerChange = new BannerChange(5, 1, 1,
                BannersAdminService.DELETE, "", LocalDateTime.now());

        Integer id = testedObject.deleteBanner(1);

        LocalDateTime localDateTime = testedObject.getBannersChanges(5, 1).get(0).getDateChange();
        assertNull(testedObject.getBanner(id));
        expectedBannerChange.setDateChange(localDateTime);
        assertEquals(expectedBannerChange, testedObject.getBannersChanges(5, 1).get(0));
    }

    @Test
    public void testUpdateBannerIsNull()
    {
        assertNull(testedObject.updateBanner(null));
    }

    @Test
    public void testUpdateBannerNotExist()
    {
        Banner banner = new Banner(0, "TEST", 3, 3,
                "TEST2", "TEST3");
        assertNull(testedObject.updateBanner(banner));
    }

    @Test
    public void testUpdateBanner()
    {
        Banner expectedBanner = new Banner(2, "TEST", 3, 3,
                "TEST", "TEST");
        BannerChange expectedBannerChange = new BannerChange(5, 2,
                1, BannersAdminService.UPDATE,
                "New value: " + expectedBanner.toString(), LocalDateTime.now());

        Integer id = testedObject.updateBanner(expectedBanner);

        LocalDateTime localDateTime = testedObject.getBannersChanges(5, 1).get(0).getDateChange();
        assertEquals(expectedBanner, testedObject.getBanner(id));
        expectedBannerChange.setDateChange(localDateTime);
        assertEquals(expectedBannerChange, testedObject.getBannersChanges(5, 1).get(0));
    }
}
