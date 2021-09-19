package Vesela.Druzina.demo.model;

import Vesela.Druzina.demo.DB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

import javax.validation.Valid;

@Controller
public class RegistracioniKontroler {
    
    ImeOglasa im;
    DB baza = new DB();



    @GetMapping("/") 
    public String index1(Model model) throws SQLException
    {
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

        int n = baza.jelPrijavljen();
        if(n == 2)
            return "redirect:/indexAdmin";
        if(n == 3)
            return "redirect:/indexKorisnik";
        return "redirect:/";
    }

    @GetMapping("/indexKorisnik")
    public String indexK(Model model) throws SQLException
    {   
        model.addAttribute("oglasiDostupni", baza.pretragaOglasa(im));
        model.addAttribute("poslodavciDostupni", baza.ucitajPoslodavce());
        im = null;
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
        model.addAttribute("oglasiDostupni", baza.pretragaOglasa(im));
        model.addAttribute("korisnikDostupno", baza.ucitajKorisnikeIzBaze());
        im = null;
        System.out.println("indexAdmin");
        return "indexAdmin";
    }

    @GetMapping("/signup")
    public String registruj()
    {
        System.out.println("reg page");
        return "signup";
    }

    @PostMapping("/registrujSe")
    //@ResponseBody
    public String registrujSe(final @Valid  KorisnikEntity korisnikEntity, final BindingResult bindingResult, final Model model) throws SQLException{

        System.out.println("Usao u kontroler.");
        try {
            int flag;
            flag = baza.dodajKorisnikaUBazu(korisnikEntity);
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
        catch(SQLException e){

            System.out.println("CATCH DataIntegrityViolationException");
        } 
        return "index";
        
    }

    @GetMapping("/login")
    public String ulogujSe(final Model model)
    {
        System.out.println("log page");
        return "login";
    }

    @PostMapping("/ulogujSe")
    public String ulogujSe(KorisnikEntity korisnikEntity, Model model) throws SQLException{
        System.out.println("Usao u login");

        int flag = baza.ulogujKorisnika(korisnikEntity);

        if(flag == 1){ //nepostojeci username
            System.out.println("Ne postoji taj username");
            return "neuspesanLogin";
        }

        if(flag == 2){ //losa sifra

            System.out.println("Netacna sifra");
            return "neuspesanLogin";
        }

        if(flag == 3){
            return "redirect:/indexAdmin"; 
        }

        if(flag == 4){
            //ModelAndView model = new ModelAndView("indexKorisnik");
            model.addAttribute("ulogovanKorisnik", baza.getUlogovanKorisnik());
            return "redirect:/indexKorisnik"; 
        }
            
        if(flag == 5){
            return "redirect:/indexPoslodavac";
        }

        System.out.println("Izlazim iz logina");
        return "indexNeregistrovan";
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

        try {
            baza.dodajOglas(oglasHTML);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/indexPoslodavac";
    }

    
    @PostMapping("/apliciraj")
    public String apliciraj(CeoOglas oglas){
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
    public String obrisiOglas(CeoOglas oglas){
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

    @GetMapping("/promenaProfila")
    public String promenaProfila(){

        return "promenaProfila";
    }

    @PostMapping("/promeniProfil")
    public String promeniProfil(KorisnikEntity noviPodaci){

        System.out.println("Stigao u post mapping");
        try {
            baza.promeniPodatke(noviPodaci);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        KorisnikEntity ulogovaniKorisnik = baza.getUlogovanKorisnik();

        if(ulogovaniKorisnik.getAdmin() == 1)
            return "redirect:/indexAdmin";

        if(ulogovaniKorisnik.getPoslodavac() == 0)
            return "redirect:/indexKorisnik";

        if(ulogovaniKorisnik.getPoslodavac() == 1)
            return "redirect:/indexPoslodavac";

        return "redirect:/indexNeregistrovan"; //prebaciti redirect na korisnika/poslod/admina
    }
}