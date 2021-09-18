package Vesela.Druzina.demo.model;

import Vesela.Druzina.demo.DB;
import Vesela.Druzina.demo.web.KorisnikData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.SQLException;

import javax.validation.Valid;

@Controller
public class RegistracioniKontroler {
    
    ImeOglasa im;
    DB baza = new DB();



    @GetMapping("/") //ovako nesto se salju podaci da bi se koristili u html-u
    public String index1(Model model) throws SQLException
    {
        //model.addAttribute("oglasiDostupni", baza.ucitajOglase());
        model.addAttribute("oglasiDostupni", baza.pretragaOglasa(im));
        model.addAttribute("poslodavciDostupni", baza.ucitajPoslodavce());
        im=null;
        return "indexNeregistrovan";
    }

    @PostMapping("/pretraga")
    public String pretraga(ImeOglasa imeOglasa)
    {
        System.out.println("Usao u pretragu oglasa");
        try
        {
            baza.pretragaOglasa(imeOglasa);
            im = imeOglasa;
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return "redirect:/#Oglasi";
    }  

    @GetMapping("/indexKorisnik")
    public String indexK(Model model) throws SQLException
    {   
        model.addAttribute("oglasiDostupni", baza.ucitajOglase());
        model.addAttribute("poslodavciDostupni", baza.ucitajPoslodavce());
        System.out.println("indexKorisnik");
        return "indexKorisnik";
    }

    @GetMapping("/indexPoslodavac")
    public String index2(Model model) throws SQLException
    {
        model.addAttribute("korisnikNaOglasu", baza.nizKorisnikaNaOglasu());
        System.out.println("poslodavac");
        return "indexPoslodavac";
    }

    @GetMapping("/indexAdmin")
    public String indexA(Model model) throws SQLException
    {   
        model.addAttribute("oglasiDostupni", baza.ucitajOglase());
        model.addAttribute("korisnikDostupno", baza.ucitajKorisnikeIzBaze());
        System.out.println("indexAdmin");
        return "indexAdmin";
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
            if(flag == 4){
                System.out.println("Uspesna registracija");
                return "redirect:/";
            }
            if(flag == 5){
                System.out.println("Uspesna registracija");
                return "redirect:/";
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

        if(flag == 3){
            return "redirect:/indexAdmin"; 
        }

        if(flag == 4){
            return "redirect:/indexKorisnik"; 
        }
            
        if(flag == 5){
            return "redirect:/indexPoslodavac";
        }

        System.out.println("Izlazim iz logina");
        return "indexNeregistrovan";
    }

  /*  @GetMapping("/indexPoslodavac")
    public String indexP()
    {
        System.out.println("indexPoslodavac");
        return "indexPoslodavac";
    } */

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

        try {
            baza.dodajOglas(oglasHTML);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/indexPoslodavac";
    }

    
    @PostMapping("/apliciraj")
    public String apliciraj(Oglas oglas){
        /*
            poslati na mejl CV i prijavu
            ubaciti u bazu
        */
        System.out.println("Usao u apliciraj");
       
        try {
            baza.dodajPrijavu(oglas);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "redirect:/indexKorisnik";
    }
    
    @PostMapping("/obrisiOglas")
    public String obrisiOglas(Oglas oglas){
        System.out.println("Usao u obrisi");
       
        try {
            baza.obrisiOglas(oglas);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "redirect:/indexAdmin";
    }

    @PostMapping("/obrisiKorisnika")
    public String obrisiKorisnika(KorisnikEntity korisnik){

        System.out.println("Usao u obrisiKorisnika");
        System.out.println(korisnik.getId());
        try {
            baza.obrisiKorisnika(korisnik);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "redirect:/indexAdmin";
    }

    @PostMapping("/napraviAdminom")
    public String napraviAdminom(KorisnikEntity korisnik){

        System.out.println(korisnik.getId());
        System.out.println("Usao u napraviAdminom");
        try {
            baza.napraviAdminom(korisnik);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/indexAdmin";
    }
}