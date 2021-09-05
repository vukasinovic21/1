package Vesela.Druzina.demo.model;

public class KorisnikNijePronadjen extends RuntimeException {

    KorisnikNijePronadjen(Integer id) {
      super("Korisnik nije mogao biti pronadjen " + id);
    }
}