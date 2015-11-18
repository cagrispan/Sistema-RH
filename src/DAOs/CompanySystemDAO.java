
package DAOs;

import entities.CompanySystem;
import entities.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import resources.ConnectionFactory;

public class CompanySystemDAO {

    private static final String insert = "INSERT INTO company_system(name) VALUES(?)";
    private static final String selectAll = "SELECT * FROM company_system";
    private static final String getSystems = "SELECT c.* FROM company_system c, permission p, employee e WHERE p.idEmployee=e.id AND p.idCompanySystem=c.id AND e.id=?";
    private static final String getOutSystem = "SELECT comp.* FROM company_system comp WHERE comp.id NOT IN(SELECT c.id FROM company_system c, permission p WHERE c.id=p.idCompanySystem AND p.idEmployee=?)";
    private static final String delete = "DELETE FROM company_system WHERE id = ?";
    private static final String update = "UPDATE company_system SET name=? WHERE id = ?";

    public static void add(CompanySystem system) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setString(1, system.getName());
            statment.executeUpdate();
            system.setId(setID(statment));
            JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso.");

        } catch (SQLException ex) {
            String error = "Erro ao inserir um sistema no banco de dados.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }

    }

    public static void update(CompanySystem system) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update);
            statment.setString(1, system.getName());
            statment.setInt(2, system.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            String error = "Erro ao atualizar um sistema no banco de dados\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }
    }

    private static int setID(PreparedStatement statment) throws SQLException {
        ResultSet resultSet = statment.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static List<CompanySystem> loadAll() {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<CompanySystem> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                CompanySystem system = new CompanySystem();
                system.setName(resultSet.getString("name"));
                system.setId(resultSet.getInt("id"));
                list.add(system);
            }
            return list;
        } catch (SQLException ex) {
            String error = "Erro ao carregar lista de sistemas.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }

    public static void delete(CompanySystem system) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(delete);
            statment.setInt(1, system.getId());
            statment.executeUpdate();
        } catch (SQLException ex) {
            String error = "Erro ao deletar um sistema.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }
    }

    public static List<CompanySystem> getSystems(int id) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<CompanySystem> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getSystems);
            statment.setInt(1, id);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                CompanySystem system = new CompanySystem();
                
                system.setId(resultSet.getInt("id"));
                system.setName(resultSet.getString("name"));
                
                list.add(system);
                
            }
            return list;
        } catch (SQLException ex) {
            String error = "Erro ao buscar uma lista de sistemas.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }
    
    public static List<CompanySystem> getOutterSystems(int id) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<CompanySystem> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getOutSystem);
            statment.setInt(1, id);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                CompanySystem system = new CompanySystem();
                
                system.setId(resultSet.getInt("id"));
                system.setName(resultSet.getString("name"));
                
                list.add(system);                
            }
            return list;
        } catch (SQLException ex) {
            String error = "Erro ao buscar uma lista de systemas que o funcionário não possue acesso. Origem=" + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }
}
