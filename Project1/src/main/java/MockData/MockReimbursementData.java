package MockData;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.ReimbursementType;
import Models.Status;

public class MockReimbursementData {
	private final List<Reimbursement> reimbursements = new ArrayList<>();

	public MockReimbursementData() {
		MockUserData userData = new MockUserData();

		Reimbursement Reimbursement_To_Process_1 = new Reimbursement(1, 1, 0, "Oracle Java Certification",
				ReimbursementType.Other, Status.Pending, 250.00);
		Reimbursement Reimbursement_To_Process_2 = new Reimbursement(2, 2, 0, "Travel to Reston HQ",
				ReimbursementType.Travel, Status.Pending, 600.00);
		Reimbursement Reimbursement_Approved_1 = new Reimbursement(3, 1, 3, "Free lunch offer from Sean",
				ReimbursementType.Food, Status.Approved, 25.00);
		Reimbursement Reimbursement_Approved_2 = new Reimbursement(4, 2, 4,
				"2-night hotel stay near Orlando Office for visit", ReimbursementType.Lodging, Status.Approved, 300.00);
		Reimbursement Reimbursement_Denied_1 = new Reimbursement(5, 1, 3, "Rental Car to drive from Reston to Orlando",
				ReimbursementType.Travel, Status.Denied, 2500.00);

		reimbursements.add(Reimbursement_To_Process_1);
		reimbursements.add(Reimbursement_To_Process_2);
		reimbursements.add(Reimbursement_Approved_1);
		reimbursements.add(Reimbursement_Approved_2);
		reimbursements.add(Reimbursement_Denied_1);

	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

}
