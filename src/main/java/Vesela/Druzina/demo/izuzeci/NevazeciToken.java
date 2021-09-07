package Vesela.Druzina.demo.izuzeci;

public class NevazeciToken extends Exception {

    public NevazeciToken() {
        super();
    }


    public NevazeciToken(String poruka) {
        super(poruka);
    }


    public NevazeciToken(String poruka, Throwable razlog) {
        super(poruka, razlog);
    }
}
