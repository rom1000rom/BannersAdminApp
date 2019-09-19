package boot.dao;


import boot.model.BannerChange;
import java.util.List;

/**Интерфейс служит для определения функций хранилища данных
 * об действиях над интернет-баннерами
 @author Артемьев Р.А.
 @version 17.09.2019 */
public interface BannerChangeDAO
{
    /*Типы действий над баннерами*/
    String CREATE = "CREATE";
    String UPDATE = "UPDATE";
    String DELETE = "DELETE";


    /**Получить список всех действий над баннерами*/
    List<BannerChange> getAllBannersChanges();

    /**Получить список действий над баннерами в зависимости от типа отбора:
     * 1 - по id действия над баннером(класс BannerChange)
     * 2 - по id баннера(класс Banner)
     * 3 - по id администратора*/
    List<BannerChange> getBannersChanges(Integer id, Integer type);

    /**Добавить баннер действие над баннером*/
    Integer addBannerChange(BannerChange bannerChange);
}
