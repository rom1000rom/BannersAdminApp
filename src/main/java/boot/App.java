package boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

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

    /*JdbcTemplate это базовый класс, который управляет обработкой всех событий и связями с БД.
     Он выполняет SQL-запросы, выполняет итерации по ResultSet и извлекает вызываемые значения,
     обновляет инструкции и вызовы процедур, “ловит” исключения и транслирует их в исключения,
     определённые в пакете org.springframwork.dao .*/
    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate()
    {
        return new JdbcTemplate(getDriverManager());
    }
}