package atm.model;

public class UserModel {
    private final long id;
    private String firstName;
    private String lastName;
    private final String username;
    private String password;
    private long primaryAccountId;

    public UserModel(long id, String firstName, String lastName, String username, String password, long primaryAccountId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.primaryAccountId = primaryAccountId;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPrimaryAccountId() {
        return primaryAccountId;
    }

    public void setPrimaryAccountId(long primaryAccountId) {
        this.primaryAccountId = primaryAccountId;
    }
}
