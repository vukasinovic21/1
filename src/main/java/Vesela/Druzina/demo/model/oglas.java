package Vesela.Druzina.demo.model;

public class oglas
{
    int idoglasa;
    int idkorisnika;
    String naziv;
    int tip;
    int plata;
    String opis;
    int mesto;


    public oglas(int idoglasa, int idkorisnika, String naziv, int tip, int plata, String opis, int mesto)
    {
        this.idoglasa = idoglasa;
        this.idkorisnika = idkorisnika;
        this.naziv = naziv;
        this.tip = tip;
        this.plata = plata;
        this.opis = opis;
        this.mesto = mesto;
    }


    public int getIdoglasa() {
        return this.idoglasa;
    }

    public void setIdoglasa(int idoglasa) 
    {
        this.idoglasa = idoglasa;
    }

    public int getIdkorisnika() 
    {
        return this.idkorisnika;
    }

    public void setIdkorisnika(int idkorisnika) 
    {
        this.idkorisnika = idkorisnika;
    }

    public String getNaziv() 
    {
        return this.naziv;
    }

    public void setNaziv(String naziv) 
    {
        this.naziv = naziv;
    }

    public int getTip() 
    {
        return this.tip;
    }

    public void setTip(int tip) 
    {
        this.tip = tip;
    }

    public int getPlata()
    {
        return this.plata;
    }

    public void setPlata(int plata) 
    {
        this.plata = plata;
    }

    public String getOpis() 
    {
        return this.opis;
    }

    public void setOpis(String opis) 
    {
        this.opis = opis;
    }

    public int getMesto() 
    {
        return this.mesto;
    }

    public void setMesto(int mesto) 
    {
        this.mesto = mesto;
    }

}
