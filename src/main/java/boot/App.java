package boot;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

/**Класс представляет собой java-конфигурацию Spring Context а также точку входа
 * в Spring Boot приложение.
 @author Артемьев Р.А.
 @version 15.09.2019 */
@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    /**Метод возвращает бин-источник данных для готового приложения*/
    @Bean("dataSource")
    public DriverManagerDataSource getDriverManager()
    {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName("org.postgresql.Driver");
        driverManager.setUrl("jdbc:postgresql://localhost:5432/banners_admin_db");
        driverManager.setUsername("postgres");
        driverManager.setPassword("rgb111RGB");
        return driverManager;
    }

    /**Метод возвращает бин-источник данных для целей тестирования*/
    @Bean("dataSource")
    @Profile("test")
    public DriverManagerDataSource getDriverManagerTest()
    {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName("org.postgresql.Driver");
        driverManager.setUrl("jdbc:postgresql://localhost:5432/banners_admin_db_test");
        driverManager.setUsername("postgres");
        driverManager.setPassword("rgb111RGB");
        return driverManager;
    }

    /*JdbcTemplate это базовый класс, который управляет обработкой всех событий и связями с БД.
     Он выполняет SQL-запросы, выполняет итерации по ResultSet и извлекает вызываемые значения,
     обновляет инструкции и вызовы процедур, “ловит” исключения и транслирует их в исключения,
     определённые в пакете org.springframwork.dao .*/
    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }

}