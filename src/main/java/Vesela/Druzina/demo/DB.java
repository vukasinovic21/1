package Vesela.Druzina.demo;
import Vesela.Druzina.demo.model.KorisnikEntity;

import java.sql.*;

public class DB
{
    private Connection conn = null;
    private Statement stmt = null;
    String sql = null;
    
    public DB()
    {
        try 
        {
            System.out.println("\nPovezujem se...");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/tim23","root", "");
            stmt = conn.createStatement();
            System.out.println("Povezan\n");
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }



    public void dodajKorisnikaUBazu(KorisnikEntity korisnik){
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
    }


    public void dodajUBazu(String sql){
        try {
            System.out.println("Dodajem...");
            //stmt.execute(increment);
            stmt.executeUpdate(sql);
            System.out.println("Dodato.");
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    

    public ResultSet izvrsiSQL(String sql) {
        ResultSet rezultat = null;
        try {
            System.out.println("Uzimam n-torku...");
            rezultat = stmt.executeQuery(sql);
            System.out.println("Uzeto.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rezultat;
    }
}