package Vesela.Druzina.demo;

import Vesela.Druzina.demo.model.KorisnikEntity;
import Vesela.Druzina.demo.model.KorisnikNaOglasu;
import Vesela.Druzina.demo.model.Oglas;
import Vesela.Druzina.demo.model.OglasHTML;
import Vesela.Druzina.demo.model.Prijava;
import Vesela.Druzina.demo.model.CeoOglas;
import Vesela.Druzina.demo.model.ImeOglasa;

import java.sql.*;
import java.util.ArrayList;

import org.hibernate.boot.model.source.spi.SingularAttributeSourceToOne;
import org.springframework.web.bind.annotation.GetMapping;

/*
    TO DO:

        urediti za unos da nema duplikata.. URADJENO
        ako se doda isti mora se dodati alert URADJENO
        opadajuca lista za tip poslodavca i da li je admin URADJENO
        logout URADJENO

        dodati da moze da se korisnik ucini adminom (tabela sa korisnicima i dugme da ga napravi adminom, menja u bekendu i u bazi) URADJENO

        da moze admin da brise korisnike (+ i oglase) URADJENO
        MESTO U REG da pise slovima
        dodati dugme za prijavu URADJENO
        napraviti da poslodavac moze da brise svoj oglas 
        REGEX za mail URADJENO
        Slika za radnika 
        logout u bekendu odraditi URADJENO
        ne prikazivati tabele ako su prazne
        da admin nema "napravi adminom"
        da ne pise ID Korisnika nego Username korisnika u tabeli sa oglasima
        da pise ko je ulogovan
*/
public class DB {
    private Connection konekcija = null;
    private Statement stmt = null;
    ArrayList<KorisnikEntity> listaKorisnika = new ArrayList<KorisnikEntity>();
    ArrayList<KorisnikEntity> listaPoslodavaca = new ArrayList<KorisnikEntity>();
    ArrayList<Oglas> listaOglasa = new ArrayList<Oglas>();
    ArrayList<Prijava> listaPrijava = new ArrayList<Prijava>();
    ArrayList<KorisnikNaOglasu> listaKorisnikaNaOglasu;
    String sql = null;
    private KorisnikEntity prijavljenKorisnik;

    public DB() {
        try {
            System.out.println("\nPovezujem se...");
            konekcija = DriverManager.getConnection("jdbc:mariadb://localhost/tim23", "root", "");
            stmt = konekcija.createStatement();
            System.out.println("Povezan\n");
            System.out.println("Citam sve iz baze:");

            listaKorisnika = ucitajKorisnikeIzBaze();
            System.out.println(listaKorisnika.size());
            for (int i = 0; i < listaKorisnika.size(); i++)
                System.out.println(listaKorisnika.get(i).getEmail() + " Admin: " + listaKorisnika.get(i).getAdmin());

            System.out.println("Procitao sve iz baze.\n");
            System.out.println("\nCitam poslodavce:");
            listaPoslodavaca = ucitajPoslodavce();
            for (int i = 0; i < listaPoslodavaca.size(); i++)
                System.out.println(listaPoslodavaca.get(i).getUsername());

            System.out.println("Procitao sve poslodavce.\n");

            System.out.println("\nCitam Oglase:");
            listaOglasa = ucitajOglase();
            for (int i = 0; i < listaOglasa.size(); i++)
                System.out.println(listaOglasa.get(i).getKategorija());
            System.out.println("Procitao sve oglase.\n");

            
            listaPrijava = ucitajPrijave();
            for (int i = 0; i < listaPrijava.size(); i++)
                System.out.println(listaPrijava.get(i).getIdkorisnika() +"  "+ listaPrijava.get(i).getIdoglasa());
            System.out.println("Procitao sve prijave.\n");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<KorisnikEntity> ucitajKorisnikeIzBaze() throws SQLException {

        // Connection konekcija = DBConnection.getDBConnection().getConnection();
        // Statement stm;
        stmt = konekcija.createStatement();
        String sql = "Select * From korisnik";
        ResultSet rst;
        rst = stmt.executeQuery(sql);
        ArrayList<KorisnikEntity> listaKorisnika = new ArrayList<>();
        while (rst.next()) {
            KorisnikEntity korisnik = new KorisnikEntity(rst.getInt("id"), rst.getString("ime"),
                    rst.getString("prezime"), rst.getString("email"), rst.getString("username"),
                    rst.getString("Password"), rst.getInt("mestoid"), rst.getInt("mobilni"), rst.getInt("poslodavac"),
                    rst.getInt("admin"), rst.getString("oSebi"));
            listaKorisnika.add(korisnik);
        }
        return listaKorisnika;
    }

    public int dodajKorisnikaUBazu(KorisnikEntity korisnik) throws SQLException {

        // listaKorisnika = ucitajKorisnikeIzBaze(); //moraju da se ucitaju svi
        // korisnici pri svakom unosu novog

        // prvi check za mejl:
        for (int i = 0; i < listaKorisnika.size(); i++) {
            if (listaKorisnika.get(i).getEmail().equals(korisnik.getEmail())) {

                System.out.println("\nKorisnik sa tim mejlom vec postoji \n");
                return 1; // mejl greska
            }
        }
        // check za username
        for (int i = 0; i < listaKorisnika.size(); i++) {
            if (listaKorisnika.get(i).getUsername().equals(korisnik.getUsername())) {

                System.out.println("\nKorisnik sa tim username-om vec postoji \n");
                return 2; // username greska
            }
        }

        String sql;

        sql = "INSERT INTO `korisnik`(`Ime`, `Prezime`, `Email`, `Username`, `Password`, `MestoID`, `Mobilni`, `Poslodavac`, `Admin`, `oSebi`) "
                + "VALUES ('" + korisnik.getIme() + "', " + "'" + korisnik.getPrezime() + "', " + "'"
                + korisnik.getEmail() + "', " + "'" + korisnik.getUsername() + "', " + "'" + korisnik.getPassword()
                + "', " + "'" + korisnik.getMestoid() + "', " + "'" + korisnik.getMobilni() + "', " + "'"
                + korisnik.getPoslodavac() + "', " + "'" + korisnik.getAdmin() + "', " + "'" + korisnik.getOsebi()
                + "')";

        dodajUBazu(sql);

        listaKorisnika = ucitajKorisnikeIzBaze(); // dodaje korisnika na kraj, optimizacija

        if (korisnik.getAdmin() == 1)
            return 3; // admin

        if (korisnik.getPoslodavac() == 0)
            return 4; // korisnik

        if (korisnik.getPoslodavac() == 1)
            return 5; // poslodavac

        return 0; // greska
    }

    public int ulogujKorisnika(KorisnikEntity korisnik) throws SQLException {



        // check da li postoji username
        for (int i = 0; i < listaKorisnika.size(); i++)
            if (listaKorisnika.get(i).getUsername().equals(korisnik.getUsername()))
                if (listaKorisnika.get(i).getPassword().equals(korisnik.getPassword())) {
                    // dobra sifra
                    prijavljenKorisnik = listaKorisnika.get(i);
                    System.out.println(prijavljenKorisnik.getUsername());
                    if (prijavljenKorisnik.getAdmin() == 1)
                        return 3; // admin

                    if (prijavljenKorisnik.getPoslodavac() == 0)
                        return 4; // korisnik

                    if (prijavljenKorisnik.getPoslodavac() == 1)
                        return 5; // poslodavac

                } else {
                    // losa sifra
                    return 2;
                }

        return 1; // ne postoji taj username
    }

    public KorisnikEntity getUlogovanKorisnik(){

        if(prijavljenKorisnik != null)
            return prijavljenKorisnik;
        
        return null;
    }

    public void dodajOglas(OglasHTML oglasHTML) throws SQLException {

        System.out.println("USAO U DODAJOGLAS U DB");
        System.out.println(prijavljenKorisnik.getUsername());
        Oglas oglas = new Oglas();
        oglas.setIdkorisnika(prijavljenKorisnik.getId());


        sql = "INSERT INTO `oglas`(`IDKorisnika`, `EmailKorisnika`, `Kategorija`, `Naziv`, `Plata`, `Opis`) " + "VALUES ('" + prijavljenKorisnik.getId() + "', "+" '" + prijavljenKorisnik.getEmail() + "', "+" '" + oglasHTML.getKategorija() + "', "+" '" + oglasHTML.getNaziv() + "', "+" '" + oglasHTML.getPlata() + "', " + "'"
                + oglasHTML.getOpis() + "')";

        dodajUBazu(sql);

        listaOglasa = ucitajOglase();
        
        System.out.println("Oglas uspesno dodat");
    }

    public void dodajUBazu(String sql) {
        try {
            System.out.println("Dodajem...");
            // stmt.execute(increment);
            stmt.executeUpdate(sql); // Izvrsavanje SQL-a
            System.out.println("Dodato.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<KorisnikEntity> ucitajPoslodavce() throws SQLException {

        stmt = konekcija.createStatement();
        String sql = "Select * From korisnik where Poslodavac = 1";
        ResultSet rst;
        rst = stmt.executeQuery(sql);
        ArrayList<KorisnikEntity> listaPoslodavaca = new ArrayList<>();
        while (rst.next()) {
            KorisnikEntity korisnik = new KorisnikEntity(rst.getInt("id"), rst.getString("ime"),
                    rst.getString("prezime"), rst.getString("email"), rst.getString("username"),
                    rst.getString("Password"), rst.getInt("mestoid"), rst.getInt("mobilni"), rst.getInt("poslodavac"),
                    rst.getInt("admin"), rst.getString("oSebi"));
            listaPoslodavaca.add(korisnik);
        }
        return listaPoslodavaca;
    }

    public ArrayList<Oglas> ucitajOglase() throws SQLException {

        stmt = konekcija.createStatement();
        String sql = "Select * From oglas";
        ResultSet rst;
        rst = stmt.executeQuery(sql);
        ArrayList<Oglas> listaOglasa = new ArrayList<>();
        while (rst.next()) {
            Oglas og = new Oglas(rst.getInt("idoglasa"), rst.getInt("idkorisnika"), rst.getString("emailKorisnika"), rst.getString("kategorija"), rst.getString("naziv"), rst.getInt("plata"), rst.getString("opis"));
            listaOglasa.add(og);
        } 
        return listaOglasa;
    }

    public ArrayList<Prijava> ucitajPrijave() throws SQLException{

        stmt = konekcija.createStatement();
        ResultSet rst = stmt.executeQuery("Select * from prijave");
        ArrayList<Prijava> listaPrijava = new ArrayList<>();
        while (rst.next()) {
            Prijava og = new Prijava(rst.getInt("idkorisnika"), rst.getInt("idoglasa"), rst.getString("opisPrijave"));
                    listaPrijava.add(og);
        }
        return listaPrijava;
    }

    public void dodajPrijavu(CeoOglas oglas) throws SQLException {

        String sql;

        sql = "INSERT INTO `prijave`(`IDKorisnika`, `IDOglasa`, `OpisPrijave`) " + "VALUES ('"
                + prijavljenKorisnik.getId() + "', " + "'" + oglas.getIdOglasa() + "', " + "'" + oglas.getOpis() + "')";

        dodajUBazu(sql);

        listaPrijava = ucitajPrijave();

        System.out.println("Uspesno dodao prijavu u bazu");
    }

    public void obrisiOglas(CeoOglas oglas) throws SQLException{

        for(int i = 0; i < listaOglasa.size(); i++){

            if(listaOglasa.get(i).getIdoglasa() == oglas.getIdOglasa()){

                stmt.executeQuery("DELETE FROM `oglas` WHERE `IDOglasa` = " + oglas.getIdOglasa());
                listaOglasa.remove(i);
                break;
            }
        }

        for(int i = 0; i < listaPrijava.size(); i++){

            if(listaPrijava.get(i).getIdoglasa() == oglas.getIdOglasa()){

                stmt.executeQuery("DELETE FROM `prijave` WHERE `IDOglasa` = " + oglas.getIdOglasa());
                listaPrijava = ucitajPrijave();
                i = 0;
            }
        }
        
        listaOglasa = ucitajOglase();
    }

    public void obrisiKorisnika(KorisnikEntity korisnik) throws SQLException{

        if(korisnik.getPoslodavac() == 0){

            for(int i = 0; i < listaPrijava.size(); i++){
                System.out.println("Usao");
                if(listaPrijava.get(i).getIdkorisnika() == korisnik.getId()){

                    stmt.executeQuery("DELETE FROM `prijave` WHERE `idKorisnika` = " + korisnik.getId());
                    listaPrijava = ucitajPrijave();
                    i = 0;
                }
            }
        }

        if(korisnik.getPoslodavac() == 1){
  
            for(int i = 0; i < listaOglasa.size(); i++){
                
                //System.out.println(listaOglasa.get(i).getNaziv());

                if(listaOglasa.get(i).getIdkorisnika() == korisnik.getId()){
                    System.out.println("ID Oglasa: "+listaOglasa.get(i).getIdoglasa()); 
                    System.out.println("listaPrijava.size() = " + listaPrijava.size());
                    //treba da brise sve prijave za i-ti oglas
                    for(int z = 0; z < listaPrijava.size(); z++){

                        System.out.println("Z = "+ z);

                        if(listaPrijava.get(z).getIdoglasa() == listaOglasa.get(i).getIdoglasa()){
                            System.out.println("Nasao dva ista IDoglasa");
                            stmt.executeQuery("DELETE FROM `prijave` WHERE `idoglasa` = " + listaPrijava.get(z).getIdoglasa());
                            listaPrijava = ucitajPrijave();
                            System.out.println("Stavljam z na nulu");
                            z = 0;
                            System.out.println("Z = "+ z);
                        }
                        System.out.println("Drugi for");
                    }
                    System.out.println("izasao iz drugog fora");
                }
                System.out.println("prvi for"); 
            }
            System.out.println("izaso iz prvog fora");
 
            for(int i = 0; i < listaOglasa.size(); i++){

                if(listaOglasa.get(i).getIdkorisnika() == korisnik.getId()){
                    stmt.executeQuery("DELETE FROM `oglas` WHERE `idkorisnika` = " + korisnik.getId());
                    listaOglasa = ucitajOglase();
                    i = 0;
                }
            }
        }

        //brisanje korisnika
        stmt.executeQuery("DELETE FROM `korisnik` WHERE `id` = " + korisnik.getId());
        listaKorisnika = ucitajKorisnikeIzBaze();
        System.out.println("Gotova f-ja"); 
    }

    public void napraviAdminom(KorisnikEntity korisnik) throws SQLException{

        for(int i = 0; i < listaKorisnika.size(); i++){

            if(listaKorisnika.get(i).getId() == korisnik.getId()){

                listaKorisnika.get(i).setAdmin(1);
            }
        }

        stmt.executeQuery("UPDATE `korisnik` SET `admin` = " + 1 + " WHERE `id` = " + korisnik.getId());        
    }

    public ArrayList<KorisnikNaOglasu> nizKorisnikaNaOglasu() throws SQLException{

        ArrayList<KorisnikNaOglasu> listaKorisnikaNaOglasu = new ArrayList<KorisnikNaOglasu>();
        
        stmt = konekcija.createStatement(); 
        listaOglasa = ucitajOglase();
        listaKorisnika = ucitajKorisnikeIzBaze();
        listaPrijava = ucitajPrijave();
        for (int i = 0; i < listaOglasa.size(); i++)    // Ulazimo u petlju sa svim oglasima i trazimo koji su postavljeni od trenutno prijavljenog korisnika
        {
            if(listaOglasa.get(i).getIdkorisnika() == prijavljenKorisnik.getId()) // Nasli smo idoglasa koji je postavio prijavljeni korisnik
            {
                for(int j = 0; j < listaPrijava.size(); j++)    // Prolazimo kroz sve prijave i trazimo prijave na pronadjeni idoglasa
                {
                    for(int k = 0; k <listaKorisnika.size(); k++)   // Prolazimo kroz sve korisnike i trazimo one koji su u PRIJAVI, njihove informacije treba da vidi PRIJAVLJENI KORISNIK
                    {
                        if(listaPrijava.get(j).getIdkorisnika() == listaKorisnika.get(k).getId() && listaOglasa.get(i).getIdoglasa() == listaPrijava.get(j).getIdoglasa())
                        {
                            //System.out.println(listaKorisnika.get(k).getUsername());
                            //System.out.println(listaOglasa.get(i).getNaziv());
                            KorisnikNaOglasu ko = new KorisnikNaOglasu();
                            
                            ko.setUsername(listaKorisnika.get(k).getUsername());
                            ko.setPrezime(listaKorisnika.get(k).getPrezime());
                            ko.setPoslodavac(listaKorisnika.get(k).getPoslodavac());
                            ko.setPassword(listaKorisnika.get(k).getPassword());
                            ko.setOsebi(listaKorisnika.get(k).getOsebi());
                            ko.setMobilni(listaKorisnika.get(k).getMobilni());
                            ko.setMestoid(listaKorisnika.get(k).getMestoid());
                            ko.setImeOglasa(listaOglasa.get(i).getNaziv());
                            ko.setIme(listaKorisnika.get(k).getIme());
                            ko.setId(listaKorisnika.get(k).getId());
                            ko.setEmail(listaKorisnika.get(k).getEmail());
                            ko.setAdmin(listaKorisnika.get(k).getAdmin());

                            listaKorisnikaNaOglasu.add(ko);
                            //System.out.println(ko.getUsername());
                            //System.out.println("IME OGLASA:\n");
                            //System.out.println(ko.getImeOglasa());
                            break;
                            // Treba napraviti listu : listaKorisnikaNaOglasu, dodavanjem celog korisnika listaKorisnika.get(k).getId() jer on ispunjava sta treba
                        }
                    }
                    
                }
            }
        }
        return listaKorisnikaNaOglasu; 
    }

    public ArrayList<CeoOglas> pretragaOglasa(ImeOglasa imeOglasa) throws SQLException{
        listaOglasa = ucitajOglase();
        listaKorisnika = ucitajKorisnikeIzBaze();
        ArrayList<CeoOglas> novalista = new ArrayList<CeoOglas>();
        for (int i = 0; i < listaOglasa.size(); i++)
        {
            if(imeOglasa == null)
            {
                if((((listaOglasa.get(i).getNaziv()).toLowerCase()).contains("") == true))
                {
                    CeoOglas co = new CeoOglas();
                    co.setEmailKorisnika(listaOglasa.get(i).getEmailKorisnika());
                    co.setKategorija(listaOglasa.get(i).getKategorija());
                    co.setNaziv(listaOglasa.get(i).getNaziv());
                    co.setOpis(listaOglasa.get(i).getOpis());
                    co.setPlata(listaOglasa.get(i).getPlata());
                    co.setIdKorisnika(listaOglasa.get(i).getIdkorisnika());
                    co.setIdOglasa(listaOglasa.get(i).getIdoglasa());
                    for(int j = 0; j < listaKorisnika.size(); j++)
                    {
                        if(listaKorisnika.get(j).getId() == listaOglasa.get(i).getIdkorisnika())
                        {
                            co.setPoslodavac(listaKorisnika.get(j).getUsername());
                            break;
                        }            
                    }
                    novalista.add(co);
                }
            }
            else
            {
                if((((listaOglasa.get(i).getNaziv()).toLowerCase()).contains(imeOglasa.getNaziv().toLowerCase())) == true)
                {
                    CeoOglas co = new CeoOglas();
                    co.setEmailKorisnika(listaOglasa.get(i).getEmailKorisnika());
                    co.setKategorija(listaOglasa.get(i).getKategorija());
                    co.setNaziv(listaOglasa.get(i).getNaziv());
                    co.setOpis(listaOglasa.get(i).getOpis());
                    co.setPlata(listaOglasa.get(i).getPlata());
                    co.setIdKorisnika(listaOglasa.get(i).getIdkorisnika());
                    co.setIdOglasa(listaOglasa.get(i).getIdoglasa());
                    for(int j = 0; j < listaKorisnika.size(); j++)
                    {
                        if(listaKorisnika.get(j).getId() == listaOglasa.get(i).getIdkorisnika())
                        {
                            co.setPoslodavac(listaKorisnika.get(j).getUsername());
                            break;
                        }            
                    }
                    novalista.add(co);
                }
            }
        }
        //System.out.println("Pretrazeno je : " + imeOglasa.getNaziv());
        System.out.println("Svi oglasi koji sadrze u svom nazivu dati pojam: \n");
        for(int j = 0; j < novalista.size(); j++)
            System.out.println(novalista.get(j).getNaziv());
        return novalista;
    }

    public void promeniPodatke(KorisnikEntity noviPodaci) throws SQLException{

        //naci korisnika koji se menja (po usernejmu) i apdejtovati ga u sql i u bekendu
        System.out.println("STIGAO DOVDE");
        System.out.println(prijavljenKorisnik.getEmail());
        if(!noviPodaci.getEmail().isEmpty())
            prijavljenKorisnik.setEmail(noviPodaci.getEmail());
        if(!noviPodaci.getPassword().isEmpty())
            prijavljenKorisnik.setPassword(noviPodaci.getPassword());
        if(!noviPodaci.getIme().isEmpty())
            prijavljenKorisnik.setIme(noviPodaci.getIme());
        if(!noviPodaci.getPrezime().isEmpty())
            prijavljenKorisnik.setPrezime(noviPodaci.getPrezime());
        if(!noviPodaci.getMestoIdString().isEmpty())
            prijavljenKorisnik.setMestoid(Integer.parseInt(noviPodaci.getMestoIdString()));
        if(!noviPodaci.getMobilniString().isEmpty())
            prijavljenKorisnik.setMobilni(Integer.parseInt(noviPodaci.getMobilniString()));
        if(!noviPodaci.getOsebi().isEmpty())
            prijavljenKorisnik.setOsebi(noviPodaci.getOsebi());
        
        System.out.println(prijavljenKorisnik.getEmail()+" GAS");

        String sql = "UPDATE `korisnik` SET `ime` = ?, `prezime` = ?, `email` = ?, `mestoID` = ?, `mobilni` = ?, `oSebi` = ? WHERE `id` = "
            + prijavljenKorisnik.getId();

        PreparedStatement ps = konekcija.prepareStatement(sql);
        ps.setString(1, prijavljenKorisnik.getIme());
        ps.setString(2, prijavljenKorisnik.getPrezime());
        ps.setString(3, prijavljenKorisnik.getEmail());
        ps.setInt(4, prijavljenKorisnik.getMestoid());
        ps.setInt(5, prijavljenKorisnik.getMobilni());
        ps.setString(6, prijavljenKorisnik.getOsebi());

        ps.executeUpdate();

        listaKorisnika = ucitajKorisnikeIzBaze();
    }

    public int jelPrijavljen()
    {
        if(prijavljenKorisnik == null)
        {
            return 1; //indexNeregistrovan
        }
        else
        {
            if(prijavljenKorisnik.getAdmin() == 1)
                return 2; //indexAdmin
            else
                return 3; //indexKorisnik

        }
    }

    public void izlogujKorisnika(){
        System.out.println("IZLOGOVAO SAM GA");
        prijavljenKorisnik = null;
    }
}