package boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController
{
    @RequestMapping("/")
    public String welcome()
    {
        return "index";
    }

    @RequestMapping("/add")
    public String add()
    {
        return "add";
    }

    @RequestMapping("/edit")
    public String edit()
    {
        return "edit";
    }

    @RequestMapping("/history")
    public String history()
    {
        return "history";
    }

    @RequestMapping("/login" )
    public String login()
    {
        //Очищаем контекст безопасности при каждом запросе
        SecurityContextHolder.clearContext();
        return "login";
    }

    @RequestMapping("/registration" )
    public String registration()
    {
        return "registration";
    }

    @RequestMapping("/error_page" )
    public String error()
    {
        return "error_page";
    }
}
