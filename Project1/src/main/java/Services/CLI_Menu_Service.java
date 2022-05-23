package Services;

import java.util.List;
import java.util.Scanner;

import Models.Reimbursement;
import Models.ReimbursementType;
import Models.Role;
import Models.Status;
import Models.User;

public class CLI_Menu_Service {
	ReimbursementService rService = new ReimbursementService();
	UserService userService = new UserService();
	static Scanner scan = new Scanner(System.in);

	public static String fetchInput() {
		return scan.nextLine().split(" ")[0];

	}

	public void submitReimbursement(User employee) {
		Reimbursement reimbursementToBeSubmitted = new Reimbursement();
		reimbursementToBeSubmitted.setAuthor(employee.getId());

		System.out.println("What type of reimbursement would you like to submit?");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		System.out.println("1 -> Lodgin");
		System.out.println("2 -> Travel");
		System.out.println("3 -> Food");
		System.out.println("4 -> Other");
		int typeDecision = promptSelection(1, 2, 3, 4);

		switch (typeDecision) {
		case 1:
			reimbursementToBeSubmitted.setType(ReimbursementType.Lodging);
			break;
		case 2:
			reimbursementToBeSubmitted.setType(ReimbursementType.Travel);
			break;
		case 3:
			reimbursementToBeSubmitted.setType(ReimbursementType.Food);
			break;
		case 4:
			reimbursementToBeSubmitted.setType(ReimbursementType.Other);
			break;
		}

		System.out.println("Please enter the dollar amount you are requesting to be reimbursed: ");
		System.out.print("$");

		reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
		if (reimbursementToBeSubmitted.getAmount() <= 0) {
			System.out.println("Invalid Amount has been entered, please input a correct dollar amount.");
			boolean valid = false;
			while (!valid) {
				reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
				if (reimbursementToBeSubmitted.getAmount() != 0) {
					valid = true;
				}
			}
		}

		System.out.println("Please enter a description/reason for your reimbursement request. ");

		reimbursementToBeSubmitted.setDescription(scan.nextLine());
		if (reimbursementToBeSubmitted.getDescription().trim().equals("")) {
			System.out.println(
					"You cannot submit a request with an empty description, please explain the reason for your request.");
			boolean valid = false;
			while (!valid) {
				reimbursementToBeSubmitted.setDescription(scan.nextLine());
				if (!reimbursementToBeSubmitted.getDescription().trim().equals("")) {
					valid = true;
				}
			}
		}
		rService.submitReimbursement(reimbursementToBeSubmitted);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int promptSelection(int... validEntries) {
		int input;
		boolean valid = false;

		do {
			input = parseIntegerInput(fetchInput());
			for (int entry : validEntries) {

				if (entry == input) {
					valid = true;
					break;
				}
			}
			if (!valid) {
				System.out.println("Input recieved was not a valid option, please try again.");
			}
		} while (!valid);
		return input;

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int parseIntegerInput(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("The input received was malformed, please try again.");
			return -1;
		}
	}

	public static double parseDoubleInput(String input) {
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			System.out.println("The input received was not valid, please try again.");
			return -1;
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	public void displayLoginMenu() {

		AuthService au = new AuthService();
		boolean accountFound = false;

		System.out.println("~~~~~~~~~~~");
		System.out.println("Welcome to the Login Portal");
		System.out.println("~~~~~~~~~~");
		System.out.println();

		while (accountFound != true) {
			System.out.println("Enter your Username");
			String username = scan.nextLine();
			System.out.println("Enter your password");
			String password = scan.nextLine();
			if (au.login(username, password) != null) {
				accountFound = true;
				displayMenu();
				break;
			} else {
				System.out.println("Account not found, please re-enter username and password");
			}
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayRegisterMenu() {

		AuthService au = new AuthService();

		System.out.println("~~~~~~~~~~~");
		System.out.println("Welcome to the Registration Portal");
		System.out.println("~~~~~~~~~~");
		System.out.println();

		User userToBeRegistered = new User();
		System.out.println("Please enter your username");
		String username = scan.nextLine();
		System.out.println("Please enter a password");
		String password = scan.nextLine();
		userToBeRegistered.setUsername(username);
		userToBeRegistered.setPassword(password);
		userToBeRegistered.setRole(Role.Employee);
		au.register(userToBeRegistered);
		displayLoginMenu();

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void processReimbursement(User manager) {
		boolean processPortal = true;
		System.out.println("-------------------------------------------------------");
		System.out.println("Welcome to the Manager Processing Portal, " + manager.getUsername());
		System.out.println("-------------------------------------------------------");
		System.out.println();

		while (processPortal) {
			List<Reimbursement> reimbursements = rService.getPendingReimbursements();

			if (reimbursements.isEmpty()) {
				System.out.println("There are no reimbursement to process.");
				System.out.println("Returning to previous menu...");
				return;
			}

			int[] ids = new int[reimbursements.size()];
			for (int i = 0; i < reimbursements.size(); i++) {
				Reimbursement r = reimbursements.get(i);
				User author = userService.getUserById(r.getAuthor());
				System.out.println(r.getId() + " -> " + author.getUsername() + " : $" + r.getAmount());
				ids[i] = r.getId();
			}

			System.out.println("Please enter the ID of the Reimbursement you wish to process.");

			int selection = promptSelection(ids);
			Reimbursement reimbursementToBeProcessed = rService.getReimbursementById(selection);
			System.out.println("Processing reimbursement #" + reimbursementToBeProcessed.getId());
			System.out.println(
					"Details\nAuthor: " + userService.getUserById(reimbursementToBeProcessed.getAuthor()).getUsername()
							+ "\nAmount: " + reimbursementToBeProcessed.getAmount() + "\nDescription: "
							+ reimbursementToBeProcessed.getDescription());

			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Approve");
			System.out.println("2 -> Deny");

			int decision = promptSelection(1, 2);
			Status status = (decision == 1) ? Status.Approved : Status.Denied;
			rService.update(reimbursementToBeProcessed, manager.getId(), status);

			System.out.println("Would you like to process another reimbursement?");
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Yes");
			System.out.println("2 -> No");

			int lastChoice = promptSelection(1, 2);

			if (lastChoice == 2) {
				processPortal = false;

			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void handlePortal(Role role) {
		List<User> users = userService.getUserByRole(role);

		int[] ids = new int[users.size() + 1];
		ids[users.size()] = 0;
		for (int i = 0; i < users.size(); i++) {
			ids[i] = users.get(i).getId();
		}

		System.out.println("------------------------------------------------------");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");

		for (User u : users) {
			System.out.println(u.getId() + " -> " + u.getUsername());
		}
		System.out.println("0 -> Return to Main Menu");
		System.out.println();

		int userChoice = promptSelection(ids);

		if (userChoice == 0) {
			return;

		}
		User employee = userService.getUserById(userChoice);

		if (role == Role.Manager) {
			System.out.println("Opening Manager Portal for " + employee.getUsername());
			displayFinanceManagerMenu(employee);
		} else {
			System.out.println("Opening Employee Portal for " + employee.getUsername());
			displayEmployeeMenu(employee);
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayPendingReimbursements() {
		List<Reimbursement> pendingReimbursements = rService.getPendingReimbursements();

		if (pendingReimbursements.isEmpty()) {
			System.out.println("No Pending Requests...");
			System.out.println("Returning to Previous Menu...");
		}
		for (Reimbursement r : pendingReimbursements) {
			System.out.println(r);
		}
	}

	public void displayResolvedReimbursements() {
		List<Reimbursement> resolvedReimbursements = rService.getResolvedReimbursements();

		if (resolvedReimbursements.isEmpty()) {
			System.out.println("No Resolved Requests...");
			System.out.println("Returning to Previous Menu...");
		}
		for (Reimbursement r : resolvedReimbursements) {
			System.out.println(r);
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayPreviousRequests(User employee) {
		List<Reimbursement> reimbursements = rService.getReimbursementsByAuthor(employee.getId());

		if (reimbursements.isEmpty()) {
			System.out.println("No Previous Requests...");
			System.out.println("Returning to Previous Menu...");
		}
		for (Reimbursement r : reimbursements) {
			System.out.println(r);
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayMenu() {
		boolean menuOptions = true;

		System.out.println("---------------------------------------------------------");
		System.out.println("Welcome to the Revature Reimbursement System");
		System.out.println("----------------------------------------------------------");
		System.out.println();

		while (menuOptions) {
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Employee Portal");
			System.out.println("2 -> Finance Manager Portal");
			System.out.println("0 -> Exit Application");

			int firstChoice = promptSelection(1, 2, 0);

			switch (firstChoice) {

			case 1:
				handlePortal(Role.Employee);
				break;
			case 2:
				handlePortal(Role.Manager);
				break;
			case 0:
				System.out.println("\nHave a great day! Goodbye.");
				menuOptions = false;
				break;

			}
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayFinanceManagerMenu(User manager) {
		boolean managerPortal = true;

		System.out.println("-----------------------------------------------");
		System.out.println("Welcome to the Manager Portal, " + manager.getUsername());
		System.out.println();

		while (managerPortal) {
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> View ALL Pending Reimbursements");
			System.out.println("2 -> View ALL Resolved Reimbursements");
			System.out.println("3 -> Process a Reimbursement");
			System.out.println("0 -> Return to Main Menu");

			int firstChoice = promptSelection(1, 2, 3, 3, 0);

			switch (firstChoice) {
			case 1:
				displayPendingReimbursements();
				break;
			case 2:
				displayResolvedReimbursements();
			case 3:
				processReimbursement(manager);
				break;
			case 0:
				System.out.println("Returning to Main Menue...");
				managerPortal = false;
				break;
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void displayEmployeeMenu(User employee) {
		boolean employeePortal = true;

		System.out.println("----------------------------------------------------------");
		System.out.println("Welcome to the Employee Portal, " + employee.getUsername());
		System.out.println("-----------------------------------------------------------");
		System.out.println();

		while (employeePortal) {

			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> View Previous Requests");
			System.out.println("2 -> Submit a Reimbursement");
			System.out.println("0 -> Return to Main Menu");

			int firstChoice = promptSelection(1, 2, 0);

			switch (firstChoice) {
			case 1:
				displayPreviousRequests(employee);
				break;
			case 2:
				submitReimbursement(employee);
				break;
			case 0:
				System.out.println("Returning to Main Menu...");
				employeePortal = false;
				break;

			}
		}
	}
}