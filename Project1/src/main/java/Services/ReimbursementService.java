package Services;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Role;
import Models.Status;
import Models.User;
import Repositories.ReimbursementDAO;

public class ReimbursementService {

	public ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	public UserService rService = new UserService();
	public static ArrayList<Reimbursement> reimbursements = new ArrayList<>();

	public static void clearData() {
		reimbursements.clear();
	}

	public void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {

		for (Reimbursement reimbursement : reimbursements) {
			if (reimbursement.getId() == unprocessedReimbursement.getId()) {
				reimbursement.setResolver(resolverId);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again");
	}

	public int submitReimbursement(Reimbursement reimbursementToBeSubmitted) {

		User employee = rService.getUserById(reimbursementToBeSubmitted.getAuthor());

		if (employee.getRole() != Role.Employee) {

			throw new IllegalArgumentException("Managers cannot submit reimbursement requests.");
		} else {
			reimbursementToBeSubmitted.setStatus(Status.Pending);

			return reimbursementDAO.create(reimbursementToBeSubmitted);
		}
	}

	public List<Reimbursement> getResolvedReimbursements() {

		List<Reimbursement> resolvedReimbursements = new ArrayList<>();

		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.Approved));
		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.Denied));
		return resolvedReimbursements;

	}

	public List<Reimbursement> getPendingReimbursements() {
		return reimbursementDAO.getByStatus(Status.Pending);

	}

	public Reimbursement getReimbursementById(int id) {
		return ReimbursementDAO.getReimbursementById(id);
	}

	public List<Reimbursement> getReimbursementsByAuthor(int userId) {

		List<Reimbursement> userReimbursements = new ArrayList<>();

		for (Reimbursement r : reimbursements) {
			if (r.getAuthor() == userId || r.getResolver() == userId) {
			}
		}
		return userReimbursements;
	}
}
