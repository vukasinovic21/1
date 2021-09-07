package Vesela.Druzina.demo.model;

import Vesela.Druzina.demo.izuzeci.KorisnikNijePronadjen;
import Vesela.Druzina.demo.izuzeci.KorisnikVecPostoji;
import Vesela.Druzina.demo.izuzeci.NevazeciToken;
import Vesela.Druzina.demo.web.KorisnikData;

public interface KorisnikService {

    void registrujSe(final KorisnikData korisnik) throws KorisnikVecPostoji;
    boolean proveriDaLiKorisnikPostoji(final String email);
    void posaljiEmailPotvrdjeneRegistracije(final KorisnikEntity korisnik);
    boolean verifikujKorisnika(final String token) throws NevazeciToken;
    KorisnikEntity nadjiKorisnikaPoId(final String id) throws KorisnikNijePronadjen;
}