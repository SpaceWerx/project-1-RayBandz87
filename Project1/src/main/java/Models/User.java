package Models;

public class User {
	private int Id;
    private String user;
    private String password;
    private Role role;

    public User() {
        super();
    }

    public User(int id, String user, String password, Role role ) {
        super();
        this.Id = id;
        this.user = user;
        this.password = password;
        this.role = role;
    }
    public User( String user, String password, Role role ) {
        super();
        this.user = user;
        this.password = password;
        this.role = role;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }
    public Role getRole() {

        return role;
    }
    public String getUsername() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setPassword(String password) {
        this.password = password;

    }
    public void setUsername(String username) {
        this.user = username;

    }
	
	
	
	

}
