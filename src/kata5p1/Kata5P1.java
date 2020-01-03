package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Kata5P1 {

    public static void main(String[] args) {
        String path = "emails.txt";
        List<String> list  = MailListReader.read(path);
        for (String email : list) {
            insert(email, "kata5.db");
        }
    }
    
    public static Connection connect(String name) {
        Connection con = null;
        String url = "jdbc:sqlite:" + name;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    public static void selectAll(String name) {
        String sql = "SELECT * FROM PEOPLE";
        Connection con = connect(name);
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()) {
                System.out.println(result.getInt("id") + " " +  
                                   result.getString("Nombre") + " " + 
                                   result.getString("Apellidos") + " " +
                                   result.getString("Departamento"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createTable(String name, String sql) {
        Connection con = connect(name);
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Tabla creada");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insert(String email, String name) {
        String sql  = "INSERT INTO direc_email(direccion) VALUES(?)";
        Connection con = connect(name);
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
