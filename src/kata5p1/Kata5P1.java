package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

    public static void main(String[] args) {
        String sql = "CREATE TABLE IF NOT EXISTS direc_email (\n" +
             "id integer PRIMARY KEY AUTOINCREMENT, \n" +
             "direccion text NOT NULL);";
        createTable("kata5.db", sql);
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
}
