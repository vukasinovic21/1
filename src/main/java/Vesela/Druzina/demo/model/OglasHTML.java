package Vesela.Druzina.demo.model;

public class OglasHTML {
    
    private String naziv;
    private int plata;
    private String opis;
    private String kategorija;

    public String getKategorija() {
        return this.kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }


    public OglasHTML(String naziv, int plata, String opis) {
        this.naziv = naziv;
        this.plata = plata;
        this.opis = opis;
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
}
