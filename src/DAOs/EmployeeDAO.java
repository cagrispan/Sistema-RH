/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import entities.Employee;
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
    private static final String selectAll = "SELECT * FROM employee";
    private static final String delete = "DELETE FROM employee WHERE id = ?";
    private static final String update = "UPDATE employee SET name=?, surname=?, rg=?, cpf=? WHERE id = ?";

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
            statment.setInt(7, employee.getOffice());
            statment.setInt(8, employee.getDepartment().getId());
            statment.executeUpdate();
            employee.setId(setID(statment));
            JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso.");

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

    public static void update(Employee employee) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(update);
            statment.setString(1, employee.getName());
            statment.setString(2, employee.getSurname());
            statment.setString(3, employee.getRG());
            statment.setString(4, employee.getCPF());
            statment.setInt(5, employee.getId());
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

}
