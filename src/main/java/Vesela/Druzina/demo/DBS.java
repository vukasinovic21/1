package Vesela.Druzina.demo;

import Vesela.Druzina.*;
import java.sql.*;

public class DBS {
    
    private Connection conn = null;
    private Statement stmt = null;

    public DBS()
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
}
