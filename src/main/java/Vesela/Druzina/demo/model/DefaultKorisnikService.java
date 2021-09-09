package Vesela.Druzina.demo.model;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import Vesela.Druzina.demo.izuzeci.KorisnikNijePronadjen;
import Vesela.Druzina.demo.izuzeci.KorisnikVecPostoji;
import Vesela.Druzina.demo.izuzeci.NevazeciToken;
import Vesela.Druzina.demo.web.KorisnikData;

//import java.util.Objects;

@Service("KorisnikService")
public class DefaultKorisnikService implements KorisnikService{
    
    @Autowired
    private KorisnikRepo korisnikRepo;

    @Override
    public boolean proveriDaLiKorisnikPostoji(String email) {

        return korisnikRepo.findByEmail(email) !=null ? true : false;
    }

   /* private void encodePassword(KorisnikEntity korisnikEntity, UserData user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    } */

    @Override 
    public void registrujSe(KorisnikEntity korisnik1) throws KorisnikVecPostoji { //probati sa KorisnikEntity

        System.out.println("Usao u f-ju registrujSe u default K. servisu");
        if(proveriDaLiKorisnikPostoji(korisnik1.getEmail())){
            throw new KorisnikVecPostoji("Korisnik sa ovim mejlom vec postoji.");
        }

        KorisnikEntity noviKorisnik = new KorisnikEntity(); 
        BeanUtils.copyProperties(korisnik1, noviKorisnik); 
        korisnikRepo.save(noviKorisnik);
    /*    System.out.println(korisnik1.getId());
        System.out.println(korisnik1.getEmail());
        System.out.println(korisnik1.getIme());
        System.out.println(korisnik1.getPrezime());
        System.out.println(korisnik1.getMobilni());
        System.out.println(korisnik1.getPassword());
        System.out.println(korisnik1.getUsername());
        System.out.println(korisnik1.getOsebi());
        System.out.println(korisnik1.getAdmin());
       /* System.out.println(korisnikRepo.count());
        System.out.println(korisnikRepo.findAll()); */
        System.out.println("Korisnik prijavljen");


    }


    @Override
    public KorisnikEntity nadjiKorisnikaPoId(String id) throws KorisnikNijePronadjen {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void posaljiEmailPotvrdjeneRegistracije(KorisnikEntity korisnik) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean verifikujKorisnika(String token) throws NevazeciToken {
        // TODO Auto-generated method stub
        return false;
    }
    
}