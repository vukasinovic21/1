package Vesela.Druzina.demo.model;

public class CeoOglas {

    private String poslodavac;
    private int idOglasa;
    private int idKorisnika;
    private String naziv;
    private int plata;
    private String opis;
    private String emailKorisnika;

    public CeoOglas(String poslodavac, int idOglasa, int idKorisnika, String naziv, int plata, String opis, String emailKorisnika) {
        this.poslodavac = poslodavac;
        this.idOglasa = idOglasa;
        this.idKorisnika = idKorisnika;
        this.naziv = naziv;
        this.plata = plata;
        this.opis = opis;
        this.emailKorisnika = emailKorisnika;
    }



    public int getIdKorisnika() {
        return idKorisnika;
    }


    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }


    public int getIdOglasa() {
        return idOglasa;
    }

    public void setIdOglasa(int idOglasa) {
        this.idOglasa = idOglasa;
    }

    public CeoOglas() {
    }


    public String getPoslodavac() {
        return this.poslodavac;
    }

    public void setPoslodavac(String poslodavac) {
        this.poslodavac = poslodavac;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPlata() {
        return this.plata;
    }

    public void setPlata(int plata) {
        this.plata = plata;
    }

    public String getOpis() {
        return this.opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getEmailKorisnika() {
        return this.emailKorisnika;
    }

    public void setEmailKorisnika(String emailKorisnika) {
        this.emailKorisnika = emailKorisnika;
    }
}
