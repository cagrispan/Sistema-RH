/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import entities.Analyst;
import entities.Department;
import entities.Director;
import entities.Employee;
import entities.Janitor;
import entities.Manager;
import entities.Programmer;
import entities.Salary;
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

    private static final String insert = "INSERT INTO employee(name, surname, rg, cpf, phone, password, idSalary, idDepartment) VALUES(?,?,?,?,?,?,?,?)";
    private static final String selectAll = "SELECT e.*,s.*, o.name as officeName FROM employee e, salary s, office o WHERE e.idSalary=s.idSalary AND s.idOffice = o.id";
    private static final String countDepSize = "SELECT count(*) as size FROM employee WHERE idDepartment=?";
    private static final String getDep = "SELECT d.name, d.id, m.idManager FROM department d, manager m, employee e WHERE e.id=? AND d.id=m.idDepartment AND e.id = m.idEmployee";
    private static final String getDeps = "SELECT d.name, d.id, dir.idDirector FROM department d, director dir, employee e WHERE e.id=? AND e.id = dir.idEmployee";
    private static final String selectOfficeName = "SELECT name FROM Office WHERE id=?";
    private static final String delete = "DELETE FROM employee WHERE id = ?";
    private static final String update = "UPDATE employee SET name=?, surname=?, rg=?, cpf=?, phone=?, idSalary=?, idDepartment=? WHERE id = ?";
    private static final String insertDirector = "INSERT INTO director(idEmployee) VALUES(?)";
    private static final String insertManager = "INSERT INTO manager(idEmployee, idDepartment) VALUES(?, ?)";

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
            statment.setInt(7, getSalaryId(employee.getSalary().getIdOffice(), employee.getSalary().getLevel(), con));
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

    private static int getSalaryId(int idOffice, int level, Connection con) {
        String query = "SELECT idSalary FROM salary WHERE idOffice=? AND level=?";
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        int id;
        try {
            statment = con.prepareStatement(query);
            statment.setInt(1, idOffice + 1);
            statment.setInt(2, level + 1);
            resultSet = statment.executeQuery();
            resultSet.next();
            id = resultSet.getInt("idSalary");
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar id de salario do Funcionario (idOffice:" + (idOffice + 1) + " Level:" + (level + 1) + " Erro: " + ex.getMessage());
        }
        return id;
    }

    public static String getOfficeNamebyId(int id) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectOfficeName);

            System.out.println("id: " + id);

            statment.setInt(1, id);

            resultSet = statment.executeQuery();
            resultSet.next();

            return resultSet.getString("name");
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

    public static void update(Employee employee) {
        Connection con = null;
        PreparedStatement statment = null;
        try {
            con = ConnectionFactory.getConnection();
            int idSalary = getSalaryId(employee.getSalary().getIdOffice(), employee.getSalary().getLevel(), con);

            statment = con.prepareStatement(update);
            statment.setString(1, employee.getName());
            statment.setString(2, employee.getSurname());
            statment.setString(3, employee.getRG());
            statment.setString(4, employee.getCPF());
            statment.setString(5, employee.getPhone());
            statment.setInt(6, idSalary);
            statment.setInt(7, employee.getDepartment().getId());
            statment.setInt(8, employee.getId());
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
                Employee[] classes = {new Director(), new Manager(), new Analyst(), new Programmer(), new Janitor()};
                Employee employee = classes[resultSet.getInt("idOffice") - 1];
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setId(resultSet.getInt("id"));
                employee.setCPF(resultSet.getString("cpf"));
                employee.setRG(resultSet.getString("rg"));
                employee.setPhone(resultSet.getString("phone"));

                if (resultSet.getInt("idOffice") == 1) {
                    getDepsDir((Director) employee);
                }

                if (resultSet.getInt("idOffice") == 2) {
                    getDepMan((Manager) employee);
                }

                Salary s = new Salary();

                s.setId(resultSet.getInt("idSalary"));
                s.setIdOffice(resultSet.getInt("idOffice") - 1);
                s.setLevel(resultSet.getInt("level") - 1);
                s.setValue(resultSet.getFloat("salary"));
                s.setOfficeName(resultSet.getString("officeName"));
                employee.setSalary(s);

                Department d = new Department();

                d.setId(resultSet.getInt("idDepartment"));
                d.get();

                employee.setDepartment(d);

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
            statment.setInt(2, manager.getDep().getId());
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

    public static int getDepartmentSize(Manager manager) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(countDepSize);
            statment.setInt(1, manager.getDep().getId());
            resultSet = statment.executeQuery();
            resultSet.next();
            return resultSet.getInt("size");
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

    public static Department getDepMan(Manager manager) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        try {
            Department d = new Department();

            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getDep);
            statment.setInt(1, manager.getId());
            resultSet = statment.executeQuery();
            resultSet.next();
            manager.setIdManager(resultSet.getInt("idManager"));
            d.setId(resultSet.getInt("id"));
            d.setName(resultSet.getString("name"));

            return d;
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

    private static void getDepsDir(Director director) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        try {
            List<Department> deps = new ArrayList();
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getDeps);
            statment.setInt(1, director.getId());
            
            
            resultSet = statment.executeQuery();
            boolean set = false;
            while (resultSet.next()) {

                Department d = new Department();
                
                if(!set)
                {
                    director.setIdDirector(resultSet.getInt("idDirector"));
                    set = true;
                }
                
                d.setId(resultSet.getInt("id"));
                d.setName(resultSet.getString("name"));
                deps.add(d);
            }

            director.setDeps(deps);
            
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
