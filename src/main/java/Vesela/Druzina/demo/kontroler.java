package Vesela.Druzina.demo;

//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.view.RedirectView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.security.Principal;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

@Controller
public class Kontroler
{
    DB b = new DB();


    @GetMapping("/vukasinovic21")
    public String vukasinovic()
    {
        return "Follow @vukasinovic21";
    }

    @GetMapping("/login") 
    public String login()
    {
        return "login";
    }

    @GetMapping("/dule")
    public String dule()
    {
        return "Follow @dusan_markovic7";
    }

    @GetMapping("/index.html")
    public String houm()
    {
        return "index";
    }

    @GetMapping("/matrif")
    public String matrif()
    {
        return "Pickica";
    }

    @GetMapping("/kole")
    public String kole()
    {
        return "Pickica";
    }


    

}
