package Vesela.Druzina.demo.web;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class KorisnikData implements Serializable {

    @NotEmpty(message = "Ime ne sme da bude prazno")
    private String ime;

    @NotEmpty(message = "Prezime ne sme da bude prazno")
    private String prezime;

    @NotEmpty(message = "Email ne sme da bude prazan")
    @Email(message = "Ukucajte valjan email")
    private String email;

    @NotEmpty(message = "Sifra ne sme da bude prazna")
    private String password;


    public String getIme() {
        return this.ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return this.prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
