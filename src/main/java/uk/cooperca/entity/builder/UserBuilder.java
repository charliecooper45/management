package uk.cooperca.entity.builder;

import uk.cooperca.entity.User;

/**
 * Builder for the {@link User} class.
 *
 * @author Charlie Cooper
 */
public class UserBuilder extends AbstractBuilder<User> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public UserBuilder() {}

    public UserBuilder(User user) {
        super(user);
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

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

    @Override
    public User build() {
        return new User(id, username, password, firstName, lastName);
    }
}