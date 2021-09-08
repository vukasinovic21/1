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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
//import static com.javadevjournal.ApplicationConstant.REDIRECT;

@Controller
public class RegistracioniKontroler {
    
    @Autowired
    private KorisnikService korisnikService;

 //Crnja ovako radio addNew funkciju
    @PostMapping(value="users/addNew")
    public RedirectView addNew(KorisnikData user, RedirectAttributes redir)
    {
        korisnikService.save(user);
        RedirectView redirectView = new RedirectView("/login", true);
        redir.addFlashAttribute("message", "You successfully registred! You can now login!");
        return redirectView;
    }


    @GetMapping("/")
    public String djokica()
    {
        return "index";
    }

    @GetMapping("/signup")
    public String registruj(final Model model)
    {
        model.addAttribute("korisnikData", new KorisnikData());
        return "signup";
    }

    @PostMapping("/signup")
    public String registracijaKorisnika(final @Valid  KorisnikData korisnikData, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("registrationForm", korisnikData);
            return "signup";
        }
        try {
            korisnikService.registrujSe(korisnikData); 
        }catch (KorisnikVecPostoji e)
        {
            bindingResult.rejectValue("email", "korisnikData.email","Korisnik sa ovom email adresom vec postoji.");
            model.addAttribute("registrationForm", korisnikData);
            return "signup";
        }
        //return REDIRECT+"/starter";
        return "index"; //vraca na pocetnu stranicu
    }
}
