package DAOs;

import entities.Analyst;
import entities.CompanySystem;
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

public class EmployeeDAO {

    private static final String insert = "INSERT INTO employee(name, surname, rg, cpf, phone, password, idSalary, idDepartment) VALUES(?,?,?,?,?,?,?,?)";
    private static final String selectAll = "SELECT e.*,s.*, o.name as officeName FROM employee e, salary s, office o WHERE e.idSalary=s.idSalary AND s.idOffice = o.id";
    private static final String countDepSize = "SELECT count(*) as size FROM employee WHERE idDepartment=?";
    private static final String getDep = "SELECT d.name, d.id, m.idManager FROM department d, manager m, employee e WHERE e.id=? AND d.id=m.idDepartment AND e.id = m.idEmployee";
    private static final String getDeps = "SELECT d.name, d.id, dir.idDirector FROM department d, director dir, employee e WHERE d.idDirector = dir.idDirector AND e.id = dir.idEmployee AND e.id=?";
    private static final String getManager = "SELECT e.*, m.*, s.*,o.name as officeName FROM employee e, manager m, salary s, office o WHERE e.id = m.idEmployee AND s.idSalary = e.idSalary AND s.idOffice = o.id AND m.idManager = ?";
    private static final String getDirector = "SELECT e.*, d.*, s.*,o.name as officeName FROM employee e, director d, salary s, office o WHERE e.id = d.idEmployee AND s.idSalary = e.idSalary AND s.idOffice = o.id AND d.idDirector = ?";;
    private static final String getByCPF = "SELECT e.*,s.* FROM employee e, salary s, office o WHERE e.cpf=? AND e.idSalary=s.idSalary AND s.idOffice = o.id";
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
            String error = "Erro ao inserir um Funcionário no banco de dados.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
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
            String error = "Erro ao buscar id de salario do Funcionario (idOffice:" + (idOffice + 1) + " Level:" + (level + 1) + "\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
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
            String error = "Erro ao buscar nome do cargo pelo id.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
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
            String error = "Erro ao atualizar dados de Funcionário.\n\n Origem = " + ex.getMessage();

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

    public static List<Employee> loadAll() {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        List<Employee> list = new ArrayList();

        Employee employee = new Employee();
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(selectAll);
            resultSet = statment.executeQuery();
            while (resultSet.next()) {
                Employee[] classes = {new Director(), new Manager(), new Analyst(), new Programmer(), new Janitor()};
                employee = classes[resultSet.getInt("idOffice") - 1];
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setId(resultSet.getInt("id"));
                employee.setCPF(resultSet.getString("cpf"));
                employee.setRG(resultSet.getString("rg"));
                employee.setPhone(resultSet.getString("phone"));

                employee.setSalary(Salary.getById(resultSet.getInt("idSalary")));

                if (resultSet.getInt("idOffice") == 1) {
                    getDepsDir((Director) employee);
                }

                if (resultSet.getInt("idOffice") == 2) {
                    getDepMan((Manager) employee);
                }

                Department d = Department.getById(resultSet.getInt("idDepartment"));

                employee.setDepartment(d);

                list.add(employee);
            }
            return list;
        } catch (SQLException ex) {
            String error = "Erro ao carregar lista de Funcionários.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
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
            String error = "Erro ao deletar um funcionário.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
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
            String error = "Erro ao adicionar diretor no banco de dados.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
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
            String error = "Erro ao adicionar gerente no banco de dados\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, con);
        }

    }
    
    

    public static Manager getManager(int id) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        try {

            Manager employee = new Manager();

            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getManager);
            statment.setInt(1, id);

            resultSet = statment.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            employee.setName(resultSet.getString("name"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setId(resultSet.getInt("id"));
            employee.setCPF(resultSet.getString("cpf"));
            employee.setRG(resultSet.getString("rg"));
            employee.setPhone(resultSet.getString("phone"));

            employee.setSalary(Salary.getById(resultSet.getInt("idSalary")));

            Department d = Department.getById(resultSet.getInt("idDepartment"));

            getDepMan((Manager) employee);

            employee.setDepartment(d);

            return employee;

        } catch (SQLException ex) {
            String error = "Erro ao buscar gerente.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }

    public static Director getDirector(int id) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        try {

            Director employee = new Director();

            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getDirector);
            statment.setInt(1, id);

            resultSet = statment.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            employee.setName(resultSet.getString("name"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setId(resultSet.getInt("id"));
            employee.setCPF(resultSet.getString("cpf"));
            employee.setRG(resultSet.getString("rg"));
            employee.setPhone(resultSet.getString("phone"));

            getDepsDir((Director) employee);

            employee.setSalary(Salary.getById(resultSet.getInt("idSalary")));

            //Department d = Department.getById(resultSet.getInt("idDepartment"));
            //employee.setDepartment(d);
            return employee;

        } catch (SQLException ex) {
            String error = "Erro ao buscar diretor.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
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
            if(manager.getDep().getId()==0){
                return 0;
            }
            
            return resultSet.getInt("size")>0 ? resultSet.getInt("size"):0;
        } catch (SQLException ex) {
            String error = "Erro ao buscar quantidade de funcionários de um departamento.\n\n Origem = " + ex.getMessage();
            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
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

            if (!resultSet.next()) {
                return null;
            }

            manager.setIdManager(resultSet.getInt("idManager"));
            d.setId(resultSet.getInt("id"));
            d.setName(resultSet.getString("name"));

            return d;
        } catch (SQLException ex) {
            String error = "Erro ao buscar departamento do gerente.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
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

                if (!set) {
                    director.setIdDirector(resultSet.getInt("idDirector"));
                    set = true;
                }

                d.setId(resultSet.getInt("id"));
                d.setName(resultSet.getString("name"));
                deps.add(d);
            }

            director.setDeps(deps);
        } catch (SQLException ex) {
            String error = "Erro ao buscar departamentos do diretor.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }

    public static Employee getByCPF(String cpf) {
        Connection con = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        try {
            con = ConnectionFactory.getConnection();
            statment = con.prepareStatement(getByCPF);
            statment.setString(1, cpf);
            resultSet = statment.executeQuery();
            
            if(!resultSet.next())
                return null;

            Employee[] classes = {new Director(), new Manager(), new Analyst(), new Programmer(), new Janitor()};
            Employee employee = classes[resultSet.getInt("idOffice") - 1];
            
            
            employee.setName(resultSet.getString("name"));
            employee.setCPF(resultSet.getString("cpf"));
            employee.setId(resultSet.getInt("id"));
            employee.setPassword(resultSet.getString("password"));
            
            employee.setSalary(Salary.getById(resultSet.getInt("idSalary")));
            
            employee.setSystems();

            return employee;
        } catch (SQLException ex) {
            String error = "Erro ao buscar Funcionário pelo CPF.\n\n Origem = " + ex.getMessage();

            ConnectionFactory.popError(error);
            throw new RuntimeException(error);
        } finally {
            ConnectionFactory.close(statment, resultSet, con);
        }
    }
    

}
