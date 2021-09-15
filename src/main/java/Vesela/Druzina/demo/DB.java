package Vesela.Druzina.demo;
import Vesela.Druzina.demo.model.KorisnikEntity;

import java.sql.*;
import java.util.ArrayList;

/*
    urediti za unos da nema duplikata..
    ako se doda isti mora se dodati alert
*/
public class DB
{
    private Connection konekcija = null;
    private Statement stmt = null;
    ArrayList<KorisnikEntity> listaKorisnika = new  ArrayList<KorisnikEntity>();
    String sql = null;
    
    public DB()
    {
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

            System.out.println("Procitao sve iz baze."); 
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

        return 0; //nema gresaka
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