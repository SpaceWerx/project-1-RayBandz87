package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Role;
import Models.User;
import Utilities.ConnectionFactory;

public class UserDAO {
	public User getUserbyId(int id) {

		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "select * from ers_users where id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return new User(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), Role.valueOf(resultSet.getString("role")));
			}

		} catch (SQLException e) {

			System.out.println("Something when wrong with getting user by id via the database!");
			e.printStackTrace();
		}
		return null;
	}

	public User getByUsername(String username) {

		try (Connection connection = ConnectionFactory.getConnection()) {
			String sql = "select * from ers_users where username = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return new User(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), Role.valueOf(resultSet.getString("role")));
			}

		} catch (SQLException e) {
			System.out.println("Something when wrong with obtaining user by username via the database!");
			e.printStackTrace();
		}
		return null;

	}

	public int insertEmployee(User newEmployee) {
		try (Connection connection = ConnectionFactory.getConnection()) {

			String sql = "INSERT INTO ers_users (username, password, role)" + "VALUES (?, ?, ?::role)"
					+ "RETURNING ers_users.id";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, newEmployee.getUsername());
			preparedStatement.setString(2, newEmployee.getPassword());
			preparedStatement.setObject(3, newEmployee.getRole().name());

			ResultSet resultSet;

			if ((resultSet = preparedStatement.executeQuery()) != null) {
				resultSet.next();
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Creating User has failed");
			e.printStackTrace();
		}
		return 0;
	}

	public List<User> getEmployees() {

		try (Connection connection = ConnectionFactory.getConnection()) {

			List<User> users = new ArrayList<>();

			String sql = "select * from ers_users";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				users.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), Role.valueOf(resultSet.getString("role"))));
			}

			return users;

		} catch (SQLException sqlException) {

			System.out.println("Something went wrong with the database!");
			sqlException.printStackTrace();

		}
		return null;
	}

}
