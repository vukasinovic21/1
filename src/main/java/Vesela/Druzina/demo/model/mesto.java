package Vesela.Druzina.demo.model;

public class mesto
{
    int mestoid;
    String naziv;


    public mesto(int mestoid, String naziv)
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
