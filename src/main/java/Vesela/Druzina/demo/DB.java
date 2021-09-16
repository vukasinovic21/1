package Vesela.Druzina.demo;
import Vesela.Druzina.demo.model.KorisnikEntity;
import Vesela.Druzina.demo.model.Oglas;
import Vesela.Druzina.demo.model.OglasHTML;

import java.sql.*;
import java.util.ArrayList;

/*
    urediti za unos da nema duplikata.. URADJENO
    ako se doda isti mora se dodati alert URADJENO
    opadajuca lista za tip poslodavca i da li je admin
    logout 
    dodati proveru za admina
    dodati da moze da se korisnik ucini adminom
    da moze admin da brise korisnike (+ i oglase)
    MESTO U REG
    dodati dugme za prijavu
    napraviti da poslodavac moze da brise svoj oglas
*/
public class DB
{
    private Connection konekcija = null;
    private Statement stmt = null;
    ArrayList<KorisnikEntity> listaKorisnika = new  ArrayList<KorisnikEntity>();
    ArrayList<KorisnikEntity> listaPoslodavaca = new  ArrayList<KorisnikEntity>();
    ArrayList<Oglas> listaOglasa = new  ArrayList<Oglas>();
    String sql = null;
    private KorisnikEntity prijavljenKorisnik;

    public DB(){
        try 
        {
            System.out.println("\nPovezujem se...");
            konekcija = DriverManager.getConnection("jdbc:mariadb://localhost/tim23","root", "");
            stmt = konekcija.createStatement();
            System.out.println("Povezan\n");
            System.out.println("Citam sve iz baze:");

            listaKorisnika = ucitajKorisnikeIzBaze();
            System.out.println(listaKorisnika.size());
            for(int i = 0; i < listaKorisnika.size(); i++)
                System.out.println(listaKorisnika.get(i).getEmail());

            System.out.println("Procitao sve iz baze.\n"); 
            System.out.println("\nCitam poslodavce:");
            listaPoslodavaca = ucitajPoslodavce();
            for(int i = 0; i < listaPoslodavaca.size(); i++)
                System.out.println(listaPoslodavaca.get(i).getUsername());

            System.out.println("Procitao sve poslodavce.\n"); 

            System.out.println("\nCitam Oglase:");
            listaOglasa = ucitajOglase();
            for(int i = 0; i < listaOglasa.size(); i++)
                System.out.println(listaOglasa.get(i).getNaziv());

            System.out.println("Procitao sve oglase.\n"); 

        }
        catch (SQLException throwables)
        {
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
            KorisnikEntity korisnik = new KorisnikEntity(rst.getInt("id"), rst.getString("ime"), rst.getString("prezime"), rst.getString("email"),
                rst.getString("username"), rst.getString("Password"), rst.getInt("mestoid"), rst.getInt("mobilni"), 
                rst.getInt("poslodavac"), rst.getInt("admin"), rst.getString("oSebi"));
            listaKorisnika.add(korisnik);
        }
        return listaKorisnika;
    }


    public int dodajKorisnikaUBazu(KorisnikEntity korisnik) throws SQLException{
        
       // listaKorisnika = ucitajKorisnikeIzBaze(); //moraju da se ucitaju svi korisnici pri svakom unosu novog

        //prvi check za mejl:
        for(int i = 0; i < listaKorisnika.size(); i++){
            if(listaKorisnika.get(i).getEmail().equals(korisnik.getEmail())){

                System.out.println("\nKorisnik sa tim mejlom vec postoji \n");
                return 1; //mejl greska
            }
        }
        //check za username
        for(int i = 0; i < listaKorisnika.size(); i++){
            if(listaKorisnika.get(i).getUsername().equals(korisnik.getUsername())){

                System.out.println("\nKorisnik sa tim username-om vec postoji \n");
                return 2; //username greska
            }
        }

        String sql;

        sql = "INSERT INTO `korisnik`(`Ime`, `Prezime`, `Email`, `Username`, `Password`, `MestoID`, `Mobilni`, `Poslodavac`, `Admin`, `oSebi`) " +
        "VALUES ('" + korisnik.getIme() + "', " +
        "'" + korisnik.getPrezime() + "', " +
        "'" + korisnik.getEmail() + "', " +
        "'" + korisnik.getUsername() + "', " +
        "'" + korisnik.getPassword() + "', " +
        "'" + korisnik.getMestoid() + "', " +
        "'" + korisnik.getMobilni() + "', " +
        "'" + korisnik.getPoslodavac() + "', " +
        "'" + korisnik.getAdmin() + "', " +
        "'" + korisnik.getOsebi() + "')";

        dodajUBazu(sql);

        listaKorisnika.add(korisnik); //dodaje korisnika na kraj, optimizacija

        if(korisnik.getAdmin() == 1)
            return 3; //admin

        if(korisnik.getPoslodavac() == 0)
            return 4; //korisnik

        if(korisnik.getPoslodavac() == 1)
            return 5; //poslodavac

        return 0; //greska
    }

    public int ulogujKorisnika(KorisnikEntity korisnik) throws SQLException{

        //check da li postoji username
        for(int i = 0; i < listaKorisnika.size(); i++)
            if(listaKorisnika.get(i).getUsername().equals(korisnik.getUsername()))
                if(listaKorisnika.get(i).getPassword().equals(korisnik.getPassword())){
                    //dobra sifra
                    prijavljenKorisnik = listaKorisnika.get(i);
                    
                    if(prijavljenKorisnik.getAdmin() == 1)
                        return 3; //admin

                    if(prijavljenKorisnik.getPoslodavac() == 0)
                        return 4; //korisnik

                    if(prijavljenKorisnik.getPoslodavac() == 1)
                        return 5; //poslodavac
                        
                    
                }
                else{
                    //losa sifra
                    return 2;
                }
                
        
        return 1; //ne postoji taj username
    }

    public void dodajOglas(OglasHTML oglasHTML){

        System.out.println("USAO U DODAJOGLAS U DB");
        System.out.println(prijavljenKorisnik.getUsername());
        Oglas oglas = new Oglas();
        oglas.setIdkorisnika(prijavljenKorisnik.getId());
        


        sql = "INSERT INTO `oglas`(`IDKorisnika`, `Naziv`, `Plata`, `Opis`) " +
        "VALUES ('" + prijavljenKorisnik.getId() + "', " +
        "'" + oglasHTML.getNaziv() + "', " +
        "'" + oglasHTML.getPlata() + "', " +
        "'" + oglasHTML.getOpis() + "')";

        dodajUBazu(sql);

        System.out.println("Oglas uspesno dodat");
    }

    public void dodajUBazu(String sql){
        try {
            System.out.println("Dodajem...");
            //stmt.execute(increment);
            stmt.executeUpdate(sql); //Izvrsavanje SQL-a
            System.out.println("Dodato.");
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public ArrayList<KorisnikEntity> ucitajPoslodavce() throws SQLException
    {
         stmt = konekcija.createStatement();
         String sql = "Select * From korisnik where Poslodavac = 1";
         ResultSet rst;
         rst = stmt.executeQuery(sql);
         ArrayList<KorisnikEntity> listaPoslodavaca = new ArrayList<>();
         while (rst.next())
         {
             KorisnikEntity korisnik = new KorisnikEntity(rst.getInt("id"), rst.getString("ime"), rst.getString("prezime"), rst.getString("email"),
                 rst.getString("username"), rst.getString("Password"), rst.getInt("mestoid"), rst.getInt("mobilni"), 
                 rst.getInt("poslodavac"), rst.getInt("admin"), rst.getString("oSebi"));
             listaPoslodavaca.add(korisnik);
         }
         return listaPoslodavaca;
     }

    public ArrayList<Oglas> ucitajOglase() throws SQLException
    {
         stmt = konekcija.createStatement();
         String sql = "Select * From oglas";
         ResultSet rst;
         rst = stmt.executeQuery(sql);
         ArrayList<Oglas> listaOglasa = new ArrayList<>();
         while (rst.next())
         {
             Oglas og = new Oglas(rst.getInt("idoglasa"), rst.getInt("idkorisnika"), rst.getString("naziv"), rst.getInt("plata"),
                 rst.getString("opis"));
                 listaOglasa.add(og);
         }
         return listaOglasa;
     }





/*    public ResultSet izvrsiSQL(String sql) {
        ResultSet rezultat = null;
        try {
            System.out.println("Uzimam n-torku...");
            rezultat = stmt.executeQuery(sql);
            System.out.println("Uzeto.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rezultat;
    }*/
}