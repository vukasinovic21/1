package Vesela.Druzina.demo.izuzeci;

public class KorisnikNijePronadjen extends Exception {

    public KorisnikNijePronadjen() {
        super();
    }


    public KorisnikNijePronadjen(String poruka) {
        super(poruka);
    }


    public KorisnikNijePronadjen(String poruka, Throwable razlog) {
        super(poruka, razlog);
    }
}