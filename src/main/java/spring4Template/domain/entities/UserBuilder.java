package spring4Template.domain.entities;

public class UserBuilder {
    private String name;
    private String password;
    private UserGroup group;
    private String firstName;
    private String lastName;
    private Boolean systemUser;

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setGroup(UserGroup group) {
        this.group = group;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setSystemUser(Boolean systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public User createUser() {
        return new User(name, password, group);
    }
}