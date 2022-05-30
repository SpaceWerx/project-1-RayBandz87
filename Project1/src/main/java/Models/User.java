package Models;

import java.util.Objects;

public class User {

	private int role_id;
	private int user_id;
	private String Password;
	private Role role;
	private String username;
	private String user;

	public User(int i, String string, String string2, Models.Role role) {

		super();
		this.user_id = i;
		Password = string2;
		this.role = role;
		this.username = string;

		// TODO Auto-generated constructor stub
	}

	public User(int user_id, String password, Role role, String username) {
		super();
		this.user_id = user_id;
		Password = password;
		this.role = role;
		this.username = username;
	}

	public User(int role_id, int user_id, String password, Role role, String username, String user) {
		super();
		this.role_id = role_id;
		this.user_id = user_id;
		Password = password;
		this.role = role;
		this.username = username;
		this.user = user;
	}

	public User(String password, String username) {
		super();
		Password = password;
		this.username = username;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Password, role, role_id, user, user_id, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(Password, other.Password) && role == other.role && role_id == other.role_id
				&& Objects.equals(user, other.user) && user_id == other.user_id
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [role_id=" + role_id + ", user_id=" + user_id + ", Password=" + Password + ", role=" + role
				+ ", username=" + username + ", user=" + user + ", getRole_id()=" + getRole_id() + ", getUser_id()="
				+ getUser_id() + ", getPassword()=" + getPassword() + ", getRole()=" + getRole() + ", getUsername()="
				+ getUsername() + ", getUser()=" + getUser() + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}

}
