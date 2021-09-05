package Vesela.Druzina.demo;
import Vesela.Druzina.*;
import Vesela.Druzina.demo.model.Korisnik;

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
            System.out.println("Povezujem se...");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/tim23","root", "");
            stmt = conn.createStatement();
            System.out.println("Povezan");
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void dodajUBazu(String sql, String increment){
        try {
            System.out.println("Dodajem...");
            stmt.execute(increment);
            stmt.executeUpdate(sql);
            System.out.println("Dodato.");
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void dodajKorisnika(Korisnik korisnik){
        String sql;
        String inkrement;
       /* sql = "INSERT INTO `student` (`Indeks`, `Upisan`, `Ime`, `Prezime`, `GodinaStudija`) " +
                "VALUES ('" + student.getIndeks() + "', " +
                "'" + student.getUpisan() + "', " +
                "'" + student.getIme() + "', " +
                "'" + student.getPrezime() + "', " +
                "'" + student.getGodina() + "')";*/

        sql = "INSERT INTO `korisnik`(`ID`, `Ime`, `Email`, `Username`, `Password`, `MestoID`, `Mobilni`, `Poslodavac`, `Admin`, `oSebi`) " +
        "VALUES ('" + korisnik.getId() + "', " +
        "'" + korisnik.getIme() + "', " +
        "'" + korisnik.getEmail() + "', " +
        "'" + korisnik.getUsername() + "', " +
        "'" + korisnik.getPassword() + "', " +
        "'" + korisnik.getMestoid() + "', " +
        "'" + korisnik.getMobilni() + "', " +
        "'" + korisnik.getPoslodavac() + "', " +
        "'" + korisnik.getOsebi() + "', " +
            "'" + korisnik.getAdmin() + "')";


        inkrement = "ALTER TABLE `student` AUTO_INCREMENT=1";
        dodajUBazu(sql,inkrement);
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

    /*
    public void dodajUBazu(String sql, String increment)
    {
        try 
        {
            System.out.println("Inserting...");
            stmt.execute(increment);
            stmt.executeUpdate(sql);
            System.out.println("Inserted.");
        } 
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }*/
    /*
    // Izvrsava SQL naredbu i vraca rs(ResultSet)
    public ResultSet izvrsiSQL(String sql)
    {
        ResultSet rs = null;
        try 
        {
            System.out.println("Taking..");
            rs = stmt.executeQuery(sql);
            System.out.println("Taken.");
        } 
        catch (SQLException throwables) 
        {
            throwables.printStackTrace();
        }

        return rs;
    }
    // pomocna funkcija za sve funkcije koje proveravaju da li postoji nesto u bazi
    // prima sql upit koji izvrsi  i vraca true ako postoji rezultat ili false ako ne postoji
    private boolean daLiPostojiSQL(String sql)
    {
        try
        {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                System.out.println("Postoji!");
                return true;
            }
        } 
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return false;
    }
    */
}
