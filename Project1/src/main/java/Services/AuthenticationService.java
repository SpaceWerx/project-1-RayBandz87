package Services;

import Models.User;
import Repositories.UserDAO;

public class AuthenticationService {

	UserDAO employeeDAO = new UserDAO();

	public int register(User employeeToBeRegistered) {

		if (employeeDAO.getByUsername(employeeToBeRegistered.getUsername()) != null) {

			throw new NullPointerException("Employee name is alreay taken");
		}
		return employeeDAO.insertEmployee(employeeToBeRegistered);
	}

	public User login(String first_name, String password) {
		User employee;
		try {
			employee = employeeDAO.getByUsername(first_name);
			if (employee != null && password.equals(employee.getPassword())) {
				System.out.println("Logged In Successfully!");
				return employee;

			} else if (employee != null && !password.equals(employee.getPassword())) {
				System.out.println("Wrong Password");
				return null;

			} else {
				System.out.println("Employee Does Not Exist!");
				return null;

			}
		} catch (Exception e) {
			System.out.println("Login Unsuccessful");
			e.printStackTrace();
		}
		return null;
	}
}
