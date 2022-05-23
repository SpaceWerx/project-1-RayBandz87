package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import Controller.AuthController;
import Controller.ReimbursementController;
import Controller.UserController;
import Utilies.ConnectionFactoryUtility;
import io.javalin.Javalin;

public class DriverClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthController authController = new AuthController();
		UserController userController = new UserController();
		ReimbursementController reimbursementController = new ReimbursementController();
		// DELETE IF NECESSARY
		try (Connection conn = ConnectionFactoryUtility.getConnection()) {
			System.out.println("Connection Successful!");
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}

//        CLI_Menu_Service options = new CLI_Menu_Service();
//        options.displayLoginMenu();
//        options.displayMenu();

		// LEAVE IT JUST IN CASE
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins(); // This is what allows the server to process JS
		}).start(3000);

		// app.get("/employee", userController.getEmployeesHandler);

		// app.post("/employee", userController.insertEmployeesHandler);

		// app.post("/login", null);
	}

}
