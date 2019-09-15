package boot.dao;

import boot.model.Banner;

import java.util.List;

/**Интерфейс служит для определения функций хранилища данных об интернет-баннерах
 @author Артемьев Р.А.
 @version 13.09.2019 */
public interface BannerDao
{
    /**Получить список баннеров*/
    List<Banner> getAllBanners();

    /**Получить баннер по его id*/
    Banner getBanner(Integer id);

    /**Удалить баннер по его id*/
    void deleteBanner(Integer id);
}
