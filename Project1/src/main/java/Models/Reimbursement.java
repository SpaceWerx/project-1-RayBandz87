package Models;

import java.util.Objects;

public class Reimbursement {

	public int id;
	public int author;
	public int resolver;
	public String description;
	public ReimbursementType type;
	public Status Status;
	public double amount;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReimbursementType getType() {
		return type;
	}

	public Reimbursement(int author, String description, ReimbursementType type, Models.Status status, double amount) {
		super();
		this.author = author;
		this.description = description;
		this.type = type;
		Status = status;
		this.amount = amount;
	}

	public Reimbursement(int id, int author, int resolver, String description, ReimbursementType type,
			Models.Status status, double amount) {
		super();
		this.id = id;
		this.author = author;
		this.resolver = resolver;
		this.description = description;
		this.type = type;
		Status = status;
		this.amount = amount;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Status, amount, author, description, id, resolver, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Status == other.Status && Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& author == other.author && Objects.equals(description, other.description) && id == other.id
				&& resolver == other.resolver && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", author=" + author + ", resolver=" + resolver + ", description="
				+ description + ", type=" + type + ", Status=" + Status + ", amount=" + amount + ", getId()=" + getId()
				+ ", getAuthor()=" + getAuthor() + ", getResolver()=" + getResolver() + ", getDescription()="
				+ getDescription() + ", getType()=" + getType() + ", getStatus()=" + getStatus() + ", getAmount()="
				+ getAmount() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

}
