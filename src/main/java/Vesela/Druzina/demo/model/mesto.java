package Vesela.Druzina.demo.model;

public class Mesto
{
    private int mestoid;
    private String naziv;


    public Mesto(int mestoid, String naziv)
    {
        this.mestoid = mestoid;
        this.naziv = naziv;
    }


    public int getMestoid()
    {
        return this.mestoid;
    }

    public void setMestoid(int mestoid)
    {
        this.mestoid = mestoid;
    }

    public String getNaziv()
    {
        return this.naziv;
    }

    public void setNaziv(String naziv)
    {
        this.naziv = naziv;
    }

}
