package boot.dao;

import boot.entities.Banner;

import java.util.List;

/**Интерфейс служит для определения функций хранилища данных об интернет-баннерах
 @author Артемьев Р.А.
 @version 13.09.2019 */
public interface BannerDao
{
    /**Получить список пользователей*/
    List<Banner> getAllBanners();
}
