package Vesela.Druzina.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
@Entity
@Table(name = "Korisnik")
public class KorisnikEntity 
{
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String ime;
        private String prezime;
        @Column(unique = true)
        private String email;
        private String username;
        private String password;
        private int mestoid;
        private int mobilni;
        private int poslodavac;
        private int admin;
        private String osebi;

        private String mestoIdString;
        private String mobilniString;

        public KorisnikEntity(int id, String ime, String prezime, String email, String username, String password, int mestoid, int mobilni, int poslodavac, int admin, String osebi) 
        {   
            this.id = id;
            this.ime = ime;
            this.prezime = prezime;
            this.email = email;
            this.username = username;
            this.password = password;
            this.mestoid = mestoid;
            this.mobilni = mobilni;
            this.poslodavac = poslodavac;
            this.admin = admin;
            this.osebi = osebi;
        }
    
        public String getMestoIdString() {
            return this.mestoIdString;
        }

        public void setMestoIdString(String mestoIdString) {
            this.mestoIdString = mestoIdString;
        }

        public String getMobilniString() {
            return this.mobilniString;
        }

        public void setMobilniString(String mobilniString) {
            this.mobilniString = mobilniString;
        }

        public KorisnikEntity(){

        } 

        public void setId(int id) {
            this.id = id;
        }

        public String getPrezime() {
            return this.prezime;
        }

        public void setPrezime(String prezime) {
            this.prezime = prezime;
        }


        public int getId()
        {
            return this.id;
        }

        public String getIme() 
        {
            return this.ime;
        }

        public void setIme(String ime) 
        {
            this.ime = ime;
        }

        public String getEmail() 
        {
            return this.email;
        }

        public void setEmail(String email) 
        {
            this.email = email;
        }

        public String getUsername() 
        {
            return this.username;
        }

        public void setUsername(String username) 
        {
            this.username = username;
        }

        public String getPassword() 
        {
            return this.password;
        }

        public void setPassword(String password) 
        {
            this.password = password;
        }

        public int getMestoid() 
        {
            return this.mestoid;
        }

        public void setMestoid(int mestoid) 
        {
            this.mestoid = mestoid;
        }

        public int getMobilni() 
        {
            return this.mobilni;
        }

        public void setMobilni(int mobilni)
        {
            this.mobilni = mobilni;
        }

        public int getPoslodavac() 
        {
            return this.poslodavac;
        }

        public void setPoslodavac(int poslodavac) 
        {
            this.poslodavac = poslodavac;
        }

        public int getAdmin() 
        {
            return this.admin;
        }

        public void setAdmin(int admin) 
        {
            this.admin = admin;
        }

        public String getOsebi() 
        {
            return this.osebi;
        }

        public void setOsebi(String osebi) 
        {
            this.osebi = osebi;
        }
    
}