/*package Vesela.Druzina.demo.model;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KorisnikKontroler {
    
    private final KorisnikRepo repozitorijum;

    KorisnikKontroler(KorisnikRepo repozitorijum) {
    this.repozitorijum = repozitorijum;
  }
  
    //svi korisnici
    @GetMapping("/korisnik")
    List<KorisnikEntity> all() {
        return repozitorijum.findAll();
    }

    @PostMapping("/korisnik")
    KorisnikEntity noviKorisnik(@RequestBody KorisnikEntity noviKorisnik) {
        return repozitorijum.save(noviKorisnik);
    }
    
    //pojedinacno
    @GetMapping("/korisnik/{id}")
    KorisnikEntity jedan(@PathVariable Integer id) {
    
        return repozitorijum.findById(id)
        .orElseThrow(() -> new KorisnikNijePronadjen(id));
    }

    
    @DeleteMapping("/korisnik/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        repozitorijum.deleteById(id);
  }
}
*/