package Services;

import Models.User;
import Repositories.UserDAO;

public class AuthService {
	public int register(User userToBeRegistered) {

		if (UserDAO.getByUsername(userToBeRegistered.getUsername()) != null) {

			throw new NullPointerException("Username is already taken");

		}

		return UserDAO.create(userToBeRegistered);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public User login(String username, String password) {

		User user;

		try {
			user = UserDAO.getByUsername(username);

			if (user != null && password.equals(user.getPassword())) {
				System.out.println("Logged In Successfully!");
				return user;

			} else if (user != null && !password.equals(user.getPassword())) {
				System.out.println("Wrong Password");
				return null;

			} else {
				System.out.println("User Does Not Exist!");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Login Unsuccessful");
			e.printStackTrace();
		}

		return null;
	}
}