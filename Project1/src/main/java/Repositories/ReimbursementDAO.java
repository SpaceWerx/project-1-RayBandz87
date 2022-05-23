package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.ReimbursementType;
import Models.Status;
import Utilies.ConnectionFactoryUtility;

public class ReimbursementDAO {

	public static Reimbursement getReimbursementById(int id) {

		try (Connection connection = ConnectionFactoryUtility.getConnection()) {

			String sql = "select * from ers_reimbursements where id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				return new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author"),
						resultSet.getInt("resolver"), resultSet.getString("description"),
						ReimbursementType.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")), resultSet.getDouble("amount"));
			}
		} catch (SQLException e) {

			System.out.println("Something went wrong with the database!");
			e.printStackTrace();
		}

		return null;
	}

	public List<Reimbursement> getReimbursementsByUser(int userId) {

		try (Connection connection = ConnectionFactoryUtility.getConnection()) {

			String sql = "select * from ers_reimbursements where author = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();

			List<Reimbursement> reimbursements = new ArrayList<>();

			while (resultSet.next()) {

				reimbursements.add(new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author"),
						resultSet.getInt("resolver"), resultSet.getString("description"),
						ReimbursementType.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")), resultSet.getDouble("amount")));

			}

			return reimbursements;

		} catch (SQLException e) {

			System.out.println("Something Went Wrong Obtaining Your List!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Reimbursement> getByStatus(Status status) {

		try (Connection connection = ConnectionFactoryUtility.getConnection()) {

			String sql = "select * from ers_reimbursements where status = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, status.toString());

			ResultSet resultSet = preparedStatement.executeQuery();

			List<Reimbursement> reimbursements = new ArrayList<>();

			while (resultSet.next()) {

				reimbursements.add(new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author"),
						resultSet.getInt("resolver"), resultSet.getString("description"),
						ReimbursementType.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")), resultSet.getDouble("amount")));

			}

			return reimbursements;

		} catch (SQLException e) {

			System.out.println("Something Went Wrong Obtaining The Reimbursements!");
			e.printStackTrace();
		}
		return null;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int create(Reimbursement reimbursementToBeSubmitted) {
		// TODO Auto-generated method stub
		try (Connection connection = ConnectionFactoryUtility.getConnection()) {

			String sql = "INSERT INTO ers_reimbursements (author, description, type, status, amount)"
					+ "VALUES (?, ?, ?::type, ?::status, ?)" + "RETURNING ers_reimbursements.id";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, reimbursementToBeSubmitted.getAuthor());
			preparedStatement.setString(2, reimbursementToBeSubmitted.getDescription());
			preparedStatement.setObject(3, reimbursementToBeSubmitted.getType().name());
			preparedStatement.setObject(4, reimbursementToBeSubmitted.getStatus().name());
			preparedStatement.setDouble(5, reimbursementToBeSubmitted.getAmount());

			ResultSet resultSet;

			if ((resultSet = preparedStatement.executeQuery()) != null) {

				resultSet.next();

				return resultSet.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("Creating reimbursement has failed");
			e.printStackTrace();
		}

		return 0;
	}

	public List<Reimbursement> getAllReimbursements() {

		try (Connection connection = ConnectionFactoryUtility.getConnection()) {

			List<Reimbursement> reimbursements = new ArrayList<>();

			String sql = "select * from ers_reimbursements where author = ?";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				reimbursements.add(new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author"),
						resultSet.getInt("resolver"), resultSet.getString("description"),
						ReimbursementType.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")), resultSet.getDouble("amount")));

			}

			return reimbursements;

		} catch (SQLException e) {

			System.out.println("Something Went Wrong With The Database!");
			e.printStackTrace();
		}
		return null;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void update(Reimbursement unprocessedReimbursement) {
		try (Connection connection = ConnectionFactoryUtility.getConnection()) {

			String sql = "UPDATE ers_reimbursements SET resolver = ?, status = ?::status WHERE id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, unprocessedReimbursement.getResolver());
			preparedStatement.setObject(2, unprocessedReimbursement.getStatus().name());
			preparedStatement.setInt(3, unprocessedReimbursement.getId());

			preparedStatement.executeUpdate();

			System.out.println("Reimbursement Successfully Updated!");

		} catch (SQLException e) {
			System.out.println("Updating Failed!");
			e.printStackTrace();
		}

	}
}