package repository;

import model.Employee;

import java.util.List;

public interface EmployeeRepository{
    void createTable();
    void createEmployee(Employee employee);
    void createEmployeeBatch(List<Employee> employees);
    Employee findEmployeeById(int employeeId);
    List<Employee> findAllEmployee();
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
