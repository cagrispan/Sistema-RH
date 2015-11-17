package resources;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {
    
    private static int num = 0;

    public static Connection getConnection() throws SQLException {
        num++;        
        return DriverManager.getConnection("jdbc:mysql://localhost/sistemarh", "root", "root");
    }

    public static void popError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void popInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean close(PreparedStatement statment)
    {
        return closeStatment(statment);
    }
    
    public static boolean close(Connection connection)
    {
        return closeConnection(connection);
    }
    
    public static boolean close(ResultSet resultSet)
    {
        return closeResultSet(resultSet);
    }
    
    public static boolean close(PreparedStatement statment ,Connection connection)
    {
        return closeStatment(statment) && closeConnection(connection);
    }
    
    public static boolean close(PreparedStatement statment,ResultSet resultSet, Connection connection)
    {
        return closeStatment(statment) && closeResultSet(resultSet) && closeConnection(connection);
    }
    
    private static boolean closeStatment(PreparedStatement statment) {
        try {
            statment.close();
            return true;
        } catch (Exception ex) {
            String error = "Erro ao fechar Statment. Erro= " + ex.getMessage() + "\n\n Statment= " + statment.toString();

            System.out.println(error);
            popError(error);
        }
        return false;
    }

    private static boolean closeConnection(Connection connection) {
        try {
            connection.close();
            num--;
            return true;
        } catch (Exception ex) {
            String error = "Erro ao fechar conexão. Erro= " + ex.getMessage();

            System.out.println(error);
        }
        finally
        {
            System.out.println("Conexões abertas: " + num);
        }
        return false;
    }

    private static boolean closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
            return true;
        } catch (Exception ex) {
            String error = "Erro ao fechar o resultSet. Erro= " + ex.getMessage() + "\n\n resultSet=" + resultSet.toString();

            System.out.println(error);
        }

        return false;
    }
}
