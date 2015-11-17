/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import entities.CompanySystem;
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
public class CompanySystemDAO {

    private static final String insert = "INSERT INTO company_system(name) VALUES(?)";
    private static final String selectAll = "SELECT * FROM company_system";
    private static final String getSystems = "SELECT c.name FROM company_system c, permission p, employee e WHERE e.id=? AND p.idEmployee=e.id AND p.idCompanySystem=c.id";
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
            JOptionPane.showMessageDialog(null, "Erro ao inserir um sistema no banco de dados.");
            throw new RuntimeException(
                    "Erro ao inserir um sistema no banco de dados. Origem=" + ex.getMessage()
            );
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
            throw new RuntimeException(
                    "Erro ao alterar um sistema no banco de dados. Origem=" + ex.getMessage()
            );
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
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Erro ao deletar um sistema no banco de dados.");

            throw new RuntimeException(
                    "Erro ao deletar um sistema no banco de dados. Origem=" + ex.getMessage()
            );
        } finally {
            ConnectionFactory.close(statment, con);
        }
    }

    public static List<String> getSystems(int id) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getSystems);
            statment.setInt(1, id);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
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

}
