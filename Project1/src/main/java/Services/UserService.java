package Services;

import java.sql.SQLException;
import java.util.List;

import Models.User;
import Repositories.UserDAO;

public class UserService {
	UserDAO eDAO = new UserDAO(); // so that I can use the methods from the EmployeeDAO

	public List<User> getEmployees() throws SQLException {

		// get the List of Employees by calling the DAO method that selects them from
		// the database
		List<User> employees = eDAO.getEmployees();

		// return the list of employees
		return employees;
	}

	public void addEmployee(User newEmployee) throws SQLException {

		// take in the Employee object sent from the menu and send it to the EmployeeDAO
		// to be inserted into the database

		// call the DAO method that inserts the new Employee
		eDAO.insertEmployee(newEmployee);
	}

	// This is only returning one object so it doesn't necessarily have to be a
	// list...
	public User getEmployeeById(int id) {

		User employee = eDAO.getUserbyId(id);

		return employee;
	}

	public List<User> getByRole(Models.Role role) {
		// TODO Auto-generated method stub
		return null;
	}
}