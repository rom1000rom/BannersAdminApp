package boot.services;


import boot.dao.BannerChangeDAO;
import boot.dao.BannerDAO;
import boot.model.Banner;
import boot.model.BannerChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BannersAdminServiceImpl implements BannersAdminService
{
    @Autowired
    BannerDAO bannerDAO;

    @Autowired
    BannerChangeDAO bannerChangeDAO;

    /**Метод возвращает список всех баннеров, экземпляров класса Banner.
     @return список объектов класса Banner*/
    @Override
    public List<Banner> getAllBanners()
    {
        return bannerDAO.getAllBanners();
    }

    /**Метод возвращает из базы данных объект класса Banner по его id.
     @param id id баннера
     @return объект класса Banner, или Null если такового нет.*/
    @Override
    public Banner getBanner(Integer id)
    {
        return bannerDAO.getBanner(id);
    }

    /**Метод заполняет данными из базы данных и возвращает список всех действий над баннерами
     * экземпляров класса BannerChange.
     @return список объектов класса BannerChange*/
    @Override
    public List<BannerChange> getAllBannersChanges()
    {
        return bannerChangeDAO.getAllBannersChanges();
    }

    /**Метод позволяет получить список действий над баннерами в зависимости от типа отбора:
     * 1 - по id действия над баннером
     * 2 - по id баннера
     * 3 - по id администратора
     *@param id - id номер действия над баннером, баннера или администратора в зависимости
     *          от выбранного типа отбора
     *@param type - тип отбора
     *@return список действий над баннерами или null, если введены неккоректные параметры*/
    @Override
    public List<BannerChange> getBannersChanges(Integer id, Integer type)
    {
        return bannerChangeDAO.getBannersChanges(id, type);
    }

    /**Метод добавляет в базу данных информацию о баннере
     * и об этом действии в целях аудита в рамках одной транзакции.
     * @param banner - объект баннера который нужно добавить
     * @return id добавленного баннера или null, если в параметре null*/
    @Override
    /*Если функция кидает RuntimeException или его наследника (или настроенное исключение),
    транзакция автоматически откатывается*/
    @Transactional(rollbackFor = Exception.class)
    public Integer addBanner(Banner banner)
    {
        Integer bannerId = bannerDAO.addBanner(banner);//Добавляем информацию о баннере в базу

        if(bannerId != null)
        {
            //Добавляем информацию об операции в базу, для целей аудита
            bannerChangeDAO.addBannerChange(new BannerChange(0, bannerId,
                    1, BannersAdminService.CREATE, banner.toString(),
                    LocalDate.now()));
        }
        return bannerId;
    }

    /**Метод удаляет из базы данных объект класса Banner по его id и добавляет
     * в в неё информацию об этом действии в целях аудита в рамках одной транзакции.
     @param id id баннера
     @return id удалённого баннера или null, если в параметре null,
     или баннер с таким id не существует*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteBanner(Integer id)
    {
        Integer bannerId = bannerDAO.deleteBanner(id);

        if(bannerId != null)
        {
            //Добавляем информацию об операции в базу, для целей аудита
            bannerChangeDAO.addBannerChange(new BannerChange(0, bannerId,
                    1, BannersAdminService.DELETE, "",
                    LocalDate.now()));
        }
        return bannerId;
    }

    /**Метод редактирует информацию о баннере в базе данных и добавляет в неё
     * информацию об этом действии в целях аудита в рамках одной транзакции.
     * @param banner  объект баннера которым нужно обновить существующий
     * @return номер отредактированного баннера или null, если в параметр null
     * или баннера с таким id в базе не существует*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateBanner(Banner banner)
    {
        Integer bannerId = bannerDAO.updateBanner(banner);

        if(bannerId != null)
        {
            //Добавляем информацию об операции в базу, для целей аудита
            bannerChangeDAO.addBannerChange(new BannerChange(0,
                    bannerId, 1, BannersAdminService.UPDATE,
                    "New value: " + banner.toString(), LocalDate.now()));
        }
        return bannerId;
    }


}
