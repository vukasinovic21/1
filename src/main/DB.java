

import java.sql.*;

public class DB
{
    private Connection conn = null;
    private Statement stmt = null;

    public DB()
    {
        try 
        {
            System.out.println("Connecting...");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/studije","root", "");
            stmt = conn.createStatement();
            System.out.println("Connected");
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
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
    }
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
