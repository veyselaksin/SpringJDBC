package RowMapper;

import model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        int employeeId=resultSet.getInt("employeeId");
        String employeeName= resultSet.getString("name");
        String employeeSurname=resultSet.getString("surname");
        String employeeCountry=resultSet.getString("country");
        String employeeCity=resultSet.getString("city");

        Employee employee=new Employee(employeeId,employeeName,employeeSurname,employeeCountry,employeeCity);
        return employee;
    }
}
