package Vesela.Druzina.demo.izuzeci;

public class KorisnikVecPostoji extends Exception {

    public KorisnikVecPostoji() {
        super();
    }


    public KorisnikVecPostoji(String poruka) {
        super(poruka);
    }


    public KorisnikVecPostoji(String poruka, Throwable razlog) {
        super(poruka, razlog);
    }
}
