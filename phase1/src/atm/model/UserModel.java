package atm.model;

import atm.User;

public class UserModel {
    private final long id;
    private String firstName;
    private String lastName;
    private String password;
    private long primaryAccountId;

    public UserModel(long id, String firstName, String lastName, String password, long primaryAccountId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
