package Vesela.Druzina.demo.model;

//import org.json.simple.JSONObject;
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
        return "indexNeregistrovan";
    }

   @GetMapping("/indexNeregistrovan")
    public String index1()
    {
        System.out.println("neregistrovan");
        return "indexNeregistrovan";
    }  

    @GetMapping("/indexPoslodavac")
    public String index2()
    {
        System.out.println("poslodavac");
        return "indexPoslodavac";
    } 

    @GetMapping("/signup")
    public String registruj(final Model model)
    {
        System.out.println("reg page");
        model.addAttribute("korisnikData", new KorisnikData());
        return "signup";
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
            if(flag == 3){
                System.out.println("Uspesna registracija");
                return "indexAdmin";
            }
            if(flag == 4){
                System.out.println("Uspesna registracija");
                return "indexKorisnik";
            }
            if(flag == 5){
                System.out.println("Uspesna registracija");
                return "indexPoslodavac";
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

    @GetMapping("/login")
    public String ulogujSe(final Model model)
    {
        System.out.println("log page");
        model.addAttribute("korisnikData", new KorisnikData());
        return "login";
    }

    //korisnik ne postoji
    //sifra nije dobra
    @PostMapping("/ulogujSe")
    public String ulogujSe(KorisnikEntity korisnikEntity) throws SQLException{
        System.out.println("Usao u login");

        int flag = baza.ulogujKorisnika(korisnikEntity);

        if(flag == 1){ //nepostojeci username
            System.out.println("Ne postoji taj username");
            return "neuspesnaRegMail";
        }

        if(flag == 2){ //losa sifra

            System.out.println("Netacna sifra");
            return "neuspesnaRegMail";
        }

        if(flag == 4)
            return "indexKorisnik";

        if(flag == 5)
            return "indexPoslodavac";
        System.out.println("Izlazim iz logina");
        return "index";
    }

    @GetMapping("/novOglas")
    public String novOglas()
    {
        System.out.println("nov oglas page");
        //model.addAttribute("korisnikData", new KorisnikData());
        return "novOglas";
    }

    @PostMapping("/napraviOglas")
    public String napraviOglas(OglasHTML oglasHTML){
        System.out.println("Usao u napravi oglas");

        baza.dodajOglas(oglasHTML);

        return "indexPoslodavac";
    }

}