package r.d.testemployees.screens.employees;

import java.util.List;

import r.d.testemployees.pojo.Employee;

public interface EmployeesListView {
    void showData(List<Employee> employees);
    void showError();
}
