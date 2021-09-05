package Vesela.Druzina.demo.model;

public class Prijava 
{
    private int idkorisnika;
    private int idoglasa;
    private String opisPrijave;


    public Prijava(int idkorisnika, int idoglasa, String opisPrijave)
    {
        this.idkorisnika = idkorisnika;
        this.idoglasa = idoglasa;
        this.opisPrijave = opisPrijave;
    }


    public int getIdkorisnika()
    {
        return this.idkorisnika;
    }

    public void setIdkorisnika(int idkorisnika)
    {
        this.idkorisnika = idkorisnika;
    }

    public int getIdoglasa()
    {
        return this.idoglasa;
    }

    public void setIdoglasa(int idoglasa)
    {
        this.idoglasa = idoglasa;
    }

    public String getOpisPrijave()
    {
        return this.opisPrijave;
    }

    public void setOpisPrijave(String opisPrijave)
    {
        this.opisPrijave = opisPrijave;
    }

}
