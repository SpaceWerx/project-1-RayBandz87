package Models;

public class Reimbursement {
	public int ID;
    public int Author;
    public ReimbursementType Type;
    public Status Status;
    public double Amount;
    public int Resolver;
    public String Description;


public Reimbursement() {
    super();
}



    public Reimbursement(int id, int author, int resolver, String description, ReimbursementType type, Status status, double amount) {
        super();
        this.ID = id;
        Author = author;
        Resolver = resolver;
        Description = description;
        this.Type = type;
        this.Status = status;
        Amount = amount;
    }

    public int getId() {
        return ID;
        // TODO Auto-generated method stub
    }

    public void setId(int id) {
        this.ID = id;
    }

    public int getAuthor() {
        return Author;
    }

    public void setAuthor(int author) {
        Author = author;
    }

    public int getResolver() {
        return Resolver;
    }

    public void setResolver(int resolver) {
        Resolver = resolver;
    }

    public void setDescription(String description) {
        Description = description;
    }
    public String getDescription() {
        return Description;
    }

    public void setStatus(Status status) {
        this.Status = status;
    }

    public Models.Status getStatus() {
        return Status;
    }

    public double getAmount() {
        return Amount;

    }

    public void setType(ReimbursementType type) {
        this.Type = type;

    }

    public ReimbursementType getType() {
        return Type;
    }

    public void setAmount(double d) {
        Amount = d;

    }	                                 

}
