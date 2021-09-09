package Vesela.Druzina.demo.model;


import Vesela.Druzina.demo.DB;
import Vesela.Druzina.demo.izuzeci.KorisnikVecPostoji;
import Vesela.Druzina.demo.web.KorisnikData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

import javax.validation.Valid;

@Controller
public class RegistracioniKontroler {
    
    DB baza = new DB();

    @GetMapping("/")
    public String index()
    {
        System.out.println("home page");
        return "index";
    }

    @GetMapping("/index.html")
    public String index1()
    {
        System.out.println("home page");
        return "index";
    }

    @GetMapping("/signup")
    public String registruj(final Model model)
    {
        System.out.println("reg page");
        model.addAttribute("korisnikData", new KorisnikData());
        return "signup";
    }

    @GetMapping("/login")
    public String logovanje(final Model model)
    {
        System.out.println("log page");
        return "login";
    }

    @PostMapping("/registrujSe")
    //@ResponseBody
    public String registrujSe(final @Valid  KorisnikEntity korisnikEntity, final BindingResult bindingResult, final Model model) throws SQLException{

        System.out.println("Usao u kontroler.");

       /* if(bindingResult.hasErrors())
        {
            System.out.println("Usao u if");
            model.addAttribute("registrationForm", korisnikData);
            return "index";
        } */
        try {
            int flag;
           // korisnikService.registrujSe(korisnikEntity);
            flag = baza.dodajKorisnikaUBazu(korisnikEntity);
            //korisnikService.save(korisnikEntity);
            if(flag == 0){
                System.out.println("Uspesna registracija");
                return "index";
            }
            else if(flag == 1){
                System.out.println("Neuspesna registracija. Uneli ste email koji vec postoji");
                return "neuspesnaRegMail";
            }
            else if(flag == 2){
                System.out.println("Neuspesna registracija. Uneli ste username koji vec postoji");
                return "neuspesnaRegUser";
            }
        }
      /*  }catch (KorisnikVecPostoji e){
            System.out.println("Odradio catch");
            bindingResult.rejectValue("email", "korisnikData.email","Korisnik sa ovom email adresom vec postoji.");
            model.addAttribute("registrationForm", korisnikEntity);
            return "index";
            
        }*/catch(SQLException e){

            System.out.println("CATCH DataIntegrityViolationException");
            //return "index";
        } 
        //System.out.println("izasao iz kontrolera");
        //return "index"; //vraca na pocetnu stranicu
        return "index";
        
    }
}