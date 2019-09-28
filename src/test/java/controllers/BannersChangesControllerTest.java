package controllers;


import boot.App;
import boot.controllers.BannersChangesController;
import boot.model.Banner;
import boot.model.BannerChange;
import boot.services.BannersAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= App.class)
/*Аннотация @WebMvcTest(BannersController.class) создаёт тестовое окружение с настроенным
Spring MVC и входящим в него Jackson, в том виде, в каком они настроены в реальном приложении.*/
@WebMvcTest(BannersChangesController.class)
public class BannersChangesControllerTest
{
    @MockBean
    private BannersAdminService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllBannersChanges() throws Exception
    {
        List<BannerChange> listBannerChange = new ArrayList<>();

        BannerChange expectedFirst = new BannerChange(3, 1, 1,
                "CREATE", null, LocalDate.parse("2016-09-21"));

        BannerChange expectedSecond = new BannerChange(5, 2, 3,
                "CREATE", null, LocalDate.parse("2016-09-21"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //Задаём сериализацию LocalDate в численном виде(yyyy-mm-dd)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        listBannerChange.add(expectedFirst);
        listBannerChange.add(expectedSecond);

        when(service.getAllBannersChanges()).thenReturn(listBannerChange);

        mockMvc.perform(get("/bannersChanges"))//Совершаем тестовый Http-запрос
                .andExpect(status().isOk())//Проверяем Http-ответ
                .andExpect(content().string(
                        objectMapper.writeValueAsString(listBannerChange)));//Конвертируем в json
    }

    @Test
    public void testGetBannersChangesIsNull() throws Exception
    {
        Integer id = 0;
        Integer type = 4;

        when(service.getBannersChanges(id, type)).thenReturn(null);

        mockMvc.perform(get("/bannersChanges/{id}", id)
                .param("type", type.toString()))
                .andExpect(status().is(404));//Проверяем Http-ответ

    }

    @Test
    public void testGetBannersChanges() throws Exception
    {
        Integer bannerId = 1;
        Integer type = 2;

        List<BannerChange> listBannerChange = new ArrayList<>();

        BannerChange expectedFirst = new BannerChange(3, bannerId, 1,
                "CREATE", null, LocalDate.parse("2016-09-21"));

        BannerChange expectedSecond = new BannerChange(5, bannerId, 3,
                "CREATE", null, LocalDate.parse("2016-09-21"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        //Задаём сериализацию LocalDate в численном виде(yyyy-mm-dd)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        listBannerChange.add(expectedFirst);
        listBannerChange.add(expectedSecond);

        when(service.getBannersChanges(bannerId, type)).thenReturn(listBannerChange);

        mockMvc.perform(get("/bannersChanges/{id}", bannerId)
                .param("type", type.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                                objectMapper.writeValueAsString(listBannerChange)));//Конвертируем в json

    }
}
