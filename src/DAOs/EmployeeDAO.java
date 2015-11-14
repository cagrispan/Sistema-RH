/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import entities.Director;
import entities.Employee;
import entities.Manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import resources.ConnectionFactory;

/**
 *
 * @author TUNTS
 */
public class EmployeeDAO {

    private static final String insert = "INSERT INTO employee(name, surname, rg, cpf, phone, password, idOffice, idDepartment) VALUES(?,?,?,?,?,?,?,?)";
    private static final String selectAll = "SELECT e.*, s.idOffice,s.level FROM employee e, salary s WHERE e.idSalary=s.id";
    private static final String delete = "DELETE FROM employee WHERE id = ?";
    private static final String update = "UPDATE employee SET name=?, surname=?, rg=?, cpf=?, phone=?, idSalary=? WHERE id = ?";
    private static final String insertDirector = "INSERT INTO director(idEmployee) VALUES(?)";
    private static final String insertManager = "INSERT INTO manager(idEmployee) VALUES(?)";

    public static void add(Employee employee) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setString(1, employee.getName());
            statment.setString(2, employee.getSurname());
            statment.setString(3, employee.getRG());
            statment.setString(4, employee.getCPF());
            statment.setString(5, employee.getPhone());
            statment.setString(6, employee.getPassword());
            statment.setInt(7, employee.getOffice() + employee.getLevel());
            statment.setInt(8, employee.getDepartment().getId());
            statment.executeUpdate();
            employee.setId(setID(statment));
            JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um empregado no banco de dados.");
            throw new RuntimeException(
                    "Erro ao inserir um empartamento no banco de dados. Origem=" + ex.getMessage()
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
    
    private static int getSalaryId(int idOffice, int level, Connection con)
    {
        String query = "SELECT id FROM salary WHERE idOffice=? AND level=?";
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        
        int id;
        try
        {
            statment = con.prepareStatement(query);
            statment.setInt(1, idOffice+1);
            statment.setInt(2, level+1);
            resultSet = statment.executeQuery();
            resultSet.next();            
            id = resultSet.getInt("id");
        } catch(SQLException ex)
        {
            throw new RuntimeException("Erro ao buscar id de salario do Funcionario (idOffice:"+(idOffice+1) + " Level:"+ (level+1) + " Erro: " + ex.getMessage());
        }
        return id;
    }

    public static void update(Employee employee) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            int idSalary = getSalaryId(employee.getOffice(), employee.getLevel(), con);
            
            statment = con.prepareStatement(update);
            statment.setString(1, employee.getName());
            statment.setString(2, employee.getSurname());
            statment.setString(3, employee.getRG());
            statment.setString(4, employee.getCPF());            
            statment.setString(5, employee.getPhone());            
            statment.setInt(6, idSalary);
            statment.setInt(7, employee.getId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(
                    "Erro ao alterar um empregado no banco de dados. Origem=" + ex.getMessage()
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

    public static List<Employee> loadAll() {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Employee> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setId(resultSet.getInt("id"));
                employee.setCPF(resultSet.getString("cpf"));
                employee.setRG(resultSet.getString("rg"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setOffice(resultSet.getInt("idOffice")-1);
                employee.setLevel(resultSet.getInt("level")-1);                
                
                list.add(employee);
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

    public static void delete(Employee employee) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(delete);
            statment.setInt(1, employee.getId());
            statment.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar um empartamento no banco de dados.");

            throw new RuntimeException(
                    "Erro ao deletar um empartamento no banco de dados. Origem=" + ex.getMessage()
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

    public static void addDirector(Director director) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(insertDirector, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, director.getId());
            statment.executeUpdate();
            director.setIdDirector(setID(statment));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um empartamento no banco de dados.");
            throw new RuntimeException(
                    "Erro ao inserir um empartamento no banco de dados. Origem=" + ex.getMessage()
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

    public static void addManager(Manager manager) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(insertManager, PreparedStatement.RETURN_GENERATED_KEYS);
            statment.setInt(1, manager.getId());
            statment.executeUpdate();
            manager.setIdManager(setID(statment));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir um empartamento no banco de dados.");
            throw new RuntimeException(
                    "Erro ao inserir um empartamento no banco de dados. Origem=" + ex.getMessage()
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
