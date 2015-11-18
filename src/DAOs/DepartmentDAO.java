package DAOs;

import entities.Department;
import entities.Director;
import entities.Manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import resources.ConnectionFactory;

public class DepartmentDAO {

    private static final String insert = "INSERT INTO department(name) VALUES(?)";
    private static final String selectAll = "SELECT * FROM department";
    private static final String selectId = "SELECT * FROM department WHERE id=?";
    private static final String delete = "DELETE FROM department WHERE id = ?";
    private static final String update = "UPDATE department SET name=?,idManager=?,idDirector =? WHERE id = ?";
    private static final String updateDirector = "UPDATE department SET idDirector=? WHERE id = ?";
    private static final String updateManager = "UPDATE department SET idManager=? WHERE id = ?";

    public static void add(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setString(1, department.getName());
            statment.executeUpdate();
            department.setId(setID(statment));
            JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso.");

        } catch (SQLException ex) {
            String error = "Erro ao adicionar departamento.\n\n Origem = " + ex.getMessage();
            
            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }

    }

    public static void update(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update);
            statment.setString(1, department.getName());
            statment.setInt(2, department.getManager().getIdManager());
            statment.setInt(3, department.getDirector().getIdDirector());
            statment.setInt(4, department.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            String error = "Erro ao atualizar departamento.\n\n Origem = " + ex.getMessage();

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

    public static Department loadById(int id) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectId);
            statment.setInt(1, id);
            resultSet = statment.executeQuery();
            Department department = new Department();
            

            resultSet.next();
            department.setId(resultSet.getInt("id"));
            department.setName(resultSet.getString("name"));
            department.setManager(EmployeeDAO.getManager(resultSet.getInt("idManager")));
            department.setDirector(EmployeeDAO.getDirector(resultSet.getInt("idDirector")));
            

            return department;

        } catch (SQLException ex) {
            String error = "Erro ao buscar departamento pelo id.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }
    }

    public static List<Department> loadAll() {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Department> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            resultSet = statment.executeQuery();
            
            while (resultSet.next()) {
                int idManager = resultSet.getInt("idManager");
                int idDirector = resultSet.getInt("idDirector");
                
                
                Department department = new Department();
                department.setName(resultSet.getString("name"));
                department.setId(resultSet.getInt("id"));
                if(idManager!=0){
                    department.setManager(EmployeeDAO.getManager(idManager));
                }else{
                    department.setManager(new Manager());
                    department.getManager().setIdManager(0);
                }
                
                if(idDirector!=0){
                    department.setDirector(EmployeeDAO.getDirector(idDirector));
                }else{
                    department.setDirector(new Director());
                    department.getDirector().setIdDirector(0);
                }
                
                
                list.add(department);
            }
            return list;
        } catch (SQLException ex) {
            String error = "Erro ao carregar departamentos.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }
    
    public static void delete(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(delete);
            statment.setInt(1, department.getId());
            statment.executeUpdate();
        } catch (SQLException ex) {
            String error = "Erro ao excluir departamento.\n\n Origem = " + ex.getMessage();
            
            if (ex.getSQLState().startsWith("23"));
                error = "Erro ao excluir departamento.\n"
                        + "Existem funcionários associados a ele.\n"
                        + "Por favor, remova-os ou transfira-os para outro departamento."
                        + "\n\n Origem = " + ex.getMessage();
            
            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        }
        finally {
            ConnectionFactory.close(statment, con);
        }
    }

    public static void addDirector(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(updateDirector);
            statment.setInt(1, department.getDirector().getIdDirector());
            statment.setInt(2, department.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            String error = "Erro ao adicionar diretor ao departamento.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }

    }

    public static void addManager(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(updateManager);
            statment.setInt(1, department.getManager().getIdManager());
            statment.setInt(2, department.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            String error = "Erro ao adicionar gerente ao departamento.\n\n Origem = " + ex.getMessage();
            
            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }

    }

}
