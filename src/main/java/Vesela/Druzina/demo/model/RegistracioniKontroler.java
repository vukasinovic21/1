package Vesela.Druzina.demo.model;


import Vesela.Druzina.demo.DB;
import Vesela.Druzina.demo.izuzeci.KorisnikVecPostoji;
import Vesela.Druzina.demo.web.KorisnikData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
//import static com.javadevjournal.ApplicationConstant.REDIRECT;

@Controller
public class RegistracioniKontroler {
    
    DB baza = new DB();

    @Autowired
    private KorisnikService korisnikService;


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
    public String registrujSe(final @Valid  KorisnikEntity korisnikEntity, final BindingResult bindingResult, final Model model){

        System.out.println("Usao u kontroler.");

       /* if(bindingResult.hasErrors())
        {
            System.out.println("Usao u if");
            model.addAttribute("registrationForm", korisnikData);
            return "index";
        } */
        try {
            korisnikService.registrujSe(korisnikEntity);
            baza.dodajKorisnikaUBazu(korisnikEntity);
            //korisnikService.save(korisnikEntity); 
            System.out.println("Uspesna registracija");
        }catch (KorisnikVecPostoji e)
        {
            System.out.println("Odradio catch");
            bindingResult.rejectValue("email", "korisnikData.email","Korisnik sa ovom email adresom vec postoji.");
            model.addAttribute("registrationForm", korisnikEntity);
            return "index";
        }
        //return REDIRECT+"/starter";
        System.out.println("izasao iz kontrolera");
        return "index"; //vraca na pocetnu stranicu
    }
}