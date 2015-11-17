
package DAOs;

import entities.Salary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import resources.ConnectionFactory;

public class SalaryDAO {
    
    public static final String selectAll = "SELECT *, o.name as officeName FROM salary s, office o WHERE idOffice = o.id";
    public static final String update = "UPDATE salary SET salary=? WHERE idSalary=?";
    
    public static List<Salary> loadAll()
    {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Salary> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setId(resultSet.getInt("idSalary"));
                salary.setIdOffice(resultSet.getInt("idOffice"));
                salary.setLevel(resultSet.getInt("level"));
                salary.setValue(resultSet.getFloat("salary"));
                salary.setOfficeName(resultSet.getString("officeName"));
                       
                list.add(salary);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }
    
    public static void update(Salary salary)
    {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update);
            statment.setFloat(1, salary.getValue());
            statment.setInt(2, salary.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao alterar um departamento no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {

            ConnectionFactory.close(statment, con);
        }
    }
    
}
