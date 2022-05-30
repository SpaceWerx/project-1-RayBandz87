package Services;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Role;
import Models.Status;
import Models.User;
import Repositories.ReimbursementDAO;

public class ReimbursementService {
	ReimbursementDAO rd = new ReimbursementDAO();
	UserService us = new UserService();

	public Reimbursement getReimbursementById(int id) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		for (Reimbursement reimbursement : reimbursements) {
			if (reimbursement.getId() == id) {
				return reimbursement;
			}
		}
		return null;
	}

	public List<Reimbursement> getReimbursementsByAuthor(int userId) {

		List<Reimbursement> userReimbursement = new ArrayList<>();

		for (Reimbursement r : userReimbursement) {
			if (r.getAuthor() == userId || r.getResolver() == userId) {
				userReimbursement.add(r);
			}
		}
		return userReimbursement;

	}

	public List<Reimbursement> getPendingReimbursemts() {
		List<Reimbursement> pendingReimbursements = new ArrayList<>();

		for (Reimbursement reimbursement : pendingReimbursements) {
			if (reimbursement.getStatus() == Status.Pending) {
				pendingReimbursements.add(reimbursement);

			}
		}
		return pendingReimbursements;
	}

	public List<Reimbursement> getResolvedReimbursements() {
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();

		for (Reimbursement reimbursement : resolvedReimbursements) {
			if (reimbursement.getStatus() == Status.Approved || reimbursement.getStatus() == Status.Denied) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		return resolvedReimbursements;
	}

	public void submitReimbursement(Reimbursement reimbursementToBeSubmitted) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		Reimbursement lastestReimbursement = reimbursements.get(reimbursements.size() - 1);
		int id = lastestReimbursement.getId() + 1;

		reimbursementToBeSubmitted.setId(id);
		reimbursementToBeSubmitted.setStatus(Status.Pending);
		reimbursements.add(reimbursementToBeSubmitted);
	}

	public Reimbursement update(Reimbursement reimbursement, int id, Status statusInput) {
		User manager = us.getEmployeeById(id);

		if (manager.getRole() != Role.Manager) {
			throw new IllegalArgumentException("An Employee cannot process reimbursement requests.");
		} else {

			reimbursement.setResolver(id);
			reimbursement.setStatus(statusInput);

			rd.update(reimbursement);

			return reimbursement;
		}

	}

}
