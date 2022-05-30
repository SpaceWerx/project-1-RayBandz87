package Controller;

import java.util.List;

import com.google.gson.Gson;

import Models.Reimbursement;
import Models.Status;
import Repositories.ReimbursementDAO;
import Services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {
	ReimbursementDAO rd = new ReimbursementDAO();
	ReimbursementService rs = new ReimbursementService();

	public Handler getReimbursementHandler = (ctx) -> {
		List<Reimbursement> allReimbursements = rd.getAllReimbursements();

		Gson gson = new Gson();

		String JSONObject = gson.toJson(allReimbursements);

		ctx.result(JSONObject);
		ctx.status(200);
	};
	public Handler submitReimbursemetHandler = (ctx) -> {
		String body = ctx.body();

		Gson gson = new Gson();

		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);

		rd.create(reimbursement);

		ctx.result("Reimbursement successfully added!");
		ctx.status(201);
	};
	public Handler processHandler = (ctx) -> {
		String body = ctx.body();
		Gson gson = new Gson();

		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		int reimid = reimbursement.getId();
		int id = reimbursement.getResolver();
		Status statusInput = reimbursement.getStatus();
		reimbursement = rd.getReimbursementsById(reimid);
		Reimbursement processedReimbursement = rs.update(reimbursement, id, statusInput);
		if (processedReimbursement != null) {
			ctx.status(201);
			ctx.result("Reimbursement was updated");
		} else {
			ctx.status(400);
		}
	};

}
