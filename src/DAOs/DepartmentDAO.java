package DAOs;

import entities.Department;
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
    private static final String update = "UPDATE department SET name=? WHERE id = ?";
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
            JOptionPane.showMessageDialog(null, "Erro ao inserir um departamento no banco de dados.");
            throw new RuntimeException(
                    "Erro ao inserir um departamento no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {

            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    public static void update(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update);
            statment.setString(1, department.getName());
            statment.setInt(2, department.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao alterar um departamento no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {

            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    private static int setID(PreparedStatement statment) throws SQLException {
        ResultSet resultSet = statment.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }
    
    public static Department loadById(Department department)
    {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        
        try
        {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectId);
            statment.setInt(1, department.getId());
            resultSet = statment.executeQuery();
            
            resultSet.next();
            department.setId(resultSet.getInt("id"));
            department.setName(resultSet.getString("name"));
            
            return department;
            
            
        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao alterar um departamento no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {

            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
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
                Department department = new Department();
                department.setName(resultSet.getString("name"));
                department.setId(resultSet.getInt("id"));
                list.add(department);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            };
            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();;
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
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
            JOptionPane.showMessageDialog(null, "Erro ao deletar um departamento no banco de dados.");

            throw new RuntimeException(
                    "Erro ao deletar um departamento no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {

            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
    }

    public static void addDirector(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(updateDirector);
            statment.setInt(1, department.getIdDirector());
            statment.setInt(2, department.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao alterar um departamento no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {

            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

    public static void addManager(Department department) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(updateManager);
            statment.setInt(1, department.getIdManager());
            statment.setInt(2, department.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao alterar um departamento no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {

            try {
                statment.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };

            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }

    }

}
