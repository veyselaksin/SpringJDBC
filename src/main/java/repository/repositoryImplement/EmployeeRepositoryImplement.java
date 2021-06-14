package repository.repositoryImplement;

import RowMapper.EmployeeRowMapper;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import repository.EmployeeRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepositoryImplement implements EmployeeRepository {

    JdbcTemplate jdbcTemplate=new JdbcTemplate();
    String query;

    //Datasoruce for mysql implementation
    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate.setDataSource(dataSource);
    }

    //We are creating a table for employees
    @Override
    public void createTable() {
        query="CREATE TABLE employees(employeeId INT NOT NULL, name VARCHAR (20), surname VARCHAR (20), country VARCHAR (20), city VARCHAR (20), PRIMARY KEY (employeeId))";

        try{
            jdbcTemplate.execute(query);
        }catch (RuntimeException exception){
            System.out.println("Error: "+exception);
        }
    }
    //create employee function
    @Override
    public void createEmployee(Employee employee) {
        query="INSERT INTO employees(employeeId, name, surname, country, city) VALUES(?,?,?,?,?)";
        try {
            Object[] objects=new Object[]{employee.getEmployeeId(), employee.getName(), employee.getSurname(), employee.getCountry(),employee.getCity()};
            jdbcTemplate.update(query, objects);
        }catch (RuntimeException exception){
            System.out.println("Error: "+ exception);
        }

    }

    @Override
    public void createEmployeeBatch(List<Employee> employees) {
        query="INSERT INTO employees(employeeId, name, surname, country, city) VALUES(?,?,?,?,?)";
        try{
            jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    Employee employee=employees.get(i);

                    preparedStatement.setInt(1,employee.getEmployeeId());
                    preparedStatement.setString(2, employee.getName());
                    preparedStatement.setString(3, employee.getSurname());
                    preparedStatement.setString(4, employee.getCountry());
                    preparedStatement.setString(5, employee.getCity());
                }

                @Override
                public int getBatchSize() {
                    return employees.size();
                }
            });
        }catch (RuntimeException exception){
            System.out.println("Error: "+exception);
        }
    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        query="SELECT * FROM employees WHERE employeeId=?";
        Object[] employeesObject={employeeId};
        Employee employee=null;
        try{
            employee =jdbcTemplate.queryForObject(query, new EmployeeRowMapper(), employeesObject);
        } catch (RuntimeException exception){
            System.out.println("Error: "+ exception);
        }
        return employee;
    }

    @Override
    public List<Employee> findAllEmployee() {

        query="SELECT * FROM employees";
        List<Employee> employee=null;

        try{
            employee=jdbcTemplate.query(query, new EmployeeRowMapper());
        }catch (RuntimeException exception){

        }
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        query ="UPDATE employees SET name=?, surname=?, country=?, city=? WHERE employeeId=?";
        Object[] objects=new Object[]{employee.getName(), employee.getSurname(), employee.getCountry(), employee.getCity(), employee.getEmployeeId()};
        try{
            jdbcTemplate.update(query, objects);
        }catch (RuntimeException exception){
            System.out.println("Error: "+ exception);
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        query="DELETE FROM employees WHERE employeeId=?";
        Object[] objects=new Object[]{employee.getEmployeeId()};

        try{
            jdbcTemplate.update(query, objects);
        }catch (RuntimeException exception){
            System.out.println("Error: "+ exception);
        }
    }

}
