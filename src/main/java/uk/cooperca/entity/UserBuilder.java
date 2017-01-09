package uk.cooperca.entity;

/**
 * Builder for the {@link User} class.
 */
public class UserBuilder {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User build() {
        return new User(username, password, firstName, lastName);
    }
}