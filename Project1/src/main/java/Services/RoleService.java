package Services;

import java.sql.SQLException;
import java.util.List;

import Models.Reimbursement;
import Models.Status;
import Repositories.RoleDAO;

public class RoleService {

	RoleDAO rDAO = new RoleDAO();

	public void updateSalary(String roleTitle, int newSalary) throws SQLException {

		rDAO.updateSalary(roleTitle, newSalary);

	}

	public List<Reimbursement> getReimbursementsByAuthor(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getResolvedReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getPendingReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	public Reimbursement getReimbursementById(int selection) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Reimbursement reimbursementToBeProcessed, int user_id, Status status) {
		// TODO Auto-generated method stub

	}
}
