package boot.services;

import boot.model.Banner;
import boot.model.BannerChange;

import java.util.List;

/**Интерфейс служит для определения серсисных функций по работе с баннерами.
 @author Артемьев Р.А.
 @version 19.09.2019 */
public interface BannersAdminService
{
    /*Типы действий над баннерами*/
    String CREATE = "CREATE";
    String UPDATE = "UPDATE";
    String DELETE = "DELETE";

    /**Получить список всех баннеров*/
    List<Banner> getAllBanners();

    /**Получить баннер по его id*/
    Banner getBanner(Integer id);

    /**Получить список всех действий над баннерами*/
    List<BannerChange> getAllBannersChanges();

    /**Получить список действий над баннерами в зависимости от типа отбора:
     * 1 - по id действия над баннером(класс BannerChange)
     * 2 - по id баннера(класс Banner)
     * 3 - по id администратора*/
    List<BannerChange> getBannersChanges(Integer id, Integer type);

    /**Добавить баннер и информацию об этом действии в целях аудита*/
    Integer addBanner(Banner banner);

    /**Удалить баннер по его id и добавить информацию об этом действии в целях аудита*/
    Integer deleteBanner(Integer id);

    /**Редактировать баннер и добавить информацию об этом действии в целях аудита*/
    Integer updateBanner(Banner banner);
}
