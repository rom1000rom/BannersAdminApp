package boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
