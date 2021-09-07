package Vesela.Druzina.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.security.Principal;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

@RestController
public class Kontroler
{
    DB b = new DB();


    @GetMapping("/vukasinovic21")
    public String vukasinovic()
    {
        return "Follow @vukasinovic21";
    }

    @GetMapping("/dule")
    public String dule()
    {
        return "Follow @dusan_markovic7";
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
