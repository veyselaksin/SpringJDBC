import model.Employee;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import repository.EmployeeRepository;
import repository.repositoryImplement.EmployeeRepositoryImplement;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeRepository employeeRepository=context.getBean("employeeRepositoryImplement", EmployeeRepositoryImplement.class);

//        // create a table for employees
//        employeeRepository.createTable();
//
//        // create a function for employees
//        Employee employee=new Employee(1,"Veysel","Aksin","Turkey","Istanbul");
//        employeeRepository.createEmployee(employee);
//        List<Employee> employees= new ArrayList<Employee>();
//        employees.add(new Employee(2, "Fatih","Aksin", "Turkey", "Istanbul"));
//        employees.add(new Employee(3, "Henry","Zalenka","Russian","Moscow"));
//        employeeRepository.createEmployeeBatch(employees);
//
//        Employee employee=employeeRepository.findEmployeeById(3);
//        System.out.println(employee);
//
//        List<Employee> employees=employeeRepository.findAllEmployee();
//        for (Employee employee: employees) {
//            System.out.println(employee);
//        }
          // update a employee infos
//        Employee employee=employeeRepository.findEmployeeById(1);
//        employee.setName("Ali");
//        employee.setSurname("Ballı");
//        employeeRepository.updateEmployee(employee);

         // delete employees by id
        Employee employee=employeeRepository.findEmployeeById(1);
        employeeRepository.deleteEmployee(employee);

        context.close();
    }
}
