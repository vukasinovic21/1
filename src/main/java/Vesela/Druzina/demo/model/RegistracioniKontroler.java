package Vesela.Druzina.demo.model;


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
    
    @Autowired
    private KorisnikService korisnikService;


    @GetMapping("/")
    public String djokica(final Model model){
        model.addAttribute("korisnikData", new KorisnikData());
        return "index";
    }

    @GetMapping("/register")
    public String registruj(final Model model){
        model.addAttribute("korisnikData", new KorisnikData());
        return "nalog/register";
    }

    @PostMapping("/register")
    public String registracijaKorisnika(final @Valid  KorisnikData korisnikData, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", korisnikData);
            return "nalog/register";
        }
        try {
            korisnikService.registrujSe(korisnikData);
        }catch (KorisnikVecPostoji e){
            bindingResult.rejectValue("email", "korisnikData.email","Korisnik sa ovom email adresom vec postoji.");
            model.addAttribute("registrationForm", korisnikData);
            return "nalog/register";
        }
        //return REDIRECT+"/starter";
        return "/";
    }
}
