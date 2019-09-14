package boot.dao;

import boot.entities.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**Класс-реализация интерфейса BannerDAO для работы с базой данных.
@author Артемьев Р.А.
@version 13.09.2019 */
@Repository
public class BannerDaoImpl implements BannerDao
{
    /**Запрос для получения всех записей из таблицы banners*/
    private static final String BANNERS_QUERY
            = "Select banner_id, img_src, width, height, target_url, lang_id" +
            " From banners;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**Метод заполняет данными из базы данных и возвращает список экземпляров класса Banner.
     @return список объектов класса Banner */
    @Override
    public List<Banner> getAllBanners()
    {
        List<Map<String, Object>> listResalt = jdbcTemplate.queryForList(BANNERS_QUERY);

        Iterator<Map<String, Object>> it = listResalt.iterator();
        if (!it.hasNext())
        {
            return null;
        }

        List<Banner> listBanner = new ArrayList<>();
        while(it.hasNext())
        {
            Map<String, Object> row = it.next();
            listBanner.add(BannerDaoImpl.fillBanner(row));
        }
        return listBanner;
    }

    /**Метод создаёт, заполняет и возвращает экземпляр класса Banner.
     @param rs данные полученные из базы данных
     @return объект класса Banner */
    public static final Banner fillBanner(Map<String, Object> rs)
    {
        if (rs.isEmpty())
        {
            return null;
        }

        Integer id = (Integer)rs.get("banner_id");
        String imgSrc = (String)rs.get("img_src");
        Integer width = (Integer)rs.get("width");
        Integer height = (Integer)rs.get("height");
        String targetUrl = (String)rs.get("target_url");
        String langId = (String)rs.get("lang_id");

        return new Banner(id, imgSrc, width, height, targetUrl, langId);
    }
}
