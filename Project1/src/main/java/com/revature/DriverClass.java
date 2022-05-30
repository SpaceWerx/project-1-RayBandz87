package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import Controller.AuthenticationController;
import Controller.EmployeeController;
import Controller.ReimbursementController;
import Utilities.ConnectionFactory;
import io.javalin.Javalin;

public class DriverClass {
	public static void main(String[] args) throws SQLException {
		EmployeeController ec = new EmployeeController();
		AuthenticationController ac = new AuthenticationController();
		ReimbursementController rs = new ReimbursementController();

		try (Connection conn = ConnectionFactory.getConnection()) {
			System.out.println("Connection Successful :)");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}

		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(4000);

		app.get("/employee", ec.getEmployeesHandler);

		app.post("/employee", ec.insertEmployeesHandler);

		app.get("/reimbursements", rs.getReimbursementHandler);

		app.post("/submit", rs.submitReimbursemetHandler);

		app.post("/login", ac.loginHandler);

		app.put("/process", rs.processHandler);

	}
}