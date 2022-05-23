package Controller;

import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import Models.User;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class AuthController {
	public void handleRegister(Context ctx) {

		try {
			String input = ctx.body();

			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(input, User.class);

			int id = authService.register(user);

			if (id == 0) {
				ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
				ctx.result("Registration unsuccessful.");
			} else {
				ctx.status(HttpCode.CREATED);
				ctx.result("Registration successful.");
			}
		} catch (Exception e) {
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			if (!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}

			e.printStackTrace();
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void handleLogin(Context ctx) {

		String username = ctx.formParam("username");
		String password = ctx.formParam("password");

		if (Objects.equals(username, "") || Objects.equals(password, "")) {
			ctx.status(HttpCode.BAD_REQUEST);
			ctx.result("Invalid Credentials");
		} else {

			User user = authService.login(username, password);
			if (user != null) {
				ctx.status(HttpCode.ACCEPTED);
				ctx.header("Access-Control-Expose-Headers", "Current-User");
				ctx.header("Current-User", "" + user.getId());
				ctx.result(user.getRole().toString());
			} else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Invalid Credentials");
			}
		}
	}
}