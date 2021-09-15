package Vesela.Druzina.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oglas")
public class Oglas
{   
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idoglasa;
    private int idkorisnika;
    private String naziv;
    private int plata;
    private String opis;


    public Oglas(){
    }

    public Oglas(int idoglasa, int idkorisnika, String naziv, int plata, String opis)
    {
        this.idoglasa = idoglasa;
        this.idkorisnika = idkorisnika;
        this.naziv = naziv;
        this.plata = plata;
        this.opis = opis;
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
}
