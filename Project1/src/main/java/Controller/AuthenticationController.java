package Controller;

import com.google.gson.Gson;

import Models.Role;
import Models.User;
import Services.AuthenticationService;
import io.javalin.http.Handler;

public class AuthenticationController {

	AuthenticationService as = new AuthenticationService();

	public Handler loginHandler = (ctx) -> {
		String body = ctx.body();

		Gson gson = new Gson();
		// I recommend you make this an employee object
		User user = gson.fromJson(body, User.class);
		if (as.login(user.getUsername(), user.getPassword()) != null) {
			if (user.getRole() == Role.Employee) {
				ctx.status(201);
				ctx.result("Employee Login Sucessful!");
			} else if (user.getRole() == Role.Manager) {
				ctx.status(202);
				ctx.result("Manager Login Sucessful!");
			}
		} else {
			ctx.result("Login Failed!");
			ctx.status(401);

		}

	};
};
