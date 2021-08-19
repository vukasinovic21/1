package Vesela.Druzina.demo;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class kontroler 
{
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
