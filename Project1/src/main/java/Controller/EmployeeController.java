package Controller;

import java.util.List;

import com.google.gson.Gson;

import Models.User;
import Repositories.UserDAO;
import Services.UserService;
import io.javalin.http.Handler;

public class EmployeeController {
	UserService es = new UserService();
	UserDAO userDAO = new UserDAO();

	public Handler getEmployeesHandler = (ctx) -> {
//This does not work anymore like it was intended to, do not use this		
//		if(ctx.req.getSession(false) != null) {
//			
//		}
//		else {
//			ctx.status(401);
//		}
		List<User> allEmployees = es.getEmployees();

		Gson gson = new Gson();

		String JSONObject = gson.toJson(allEmployees);

		ctx.result(JSONObject);
		ctx.status(200);
	};

	public Handler insertEmployeesHandler = (ctx) -> {
		String body = ctx.body();

		Gson gson = new Gson();

		User employee = gson.fromJson(body, User.class);

		userDAO.insertEmployee(employee);

		ctx.result("Employee successfully added!");
		ctx.status(201);

	};

}
