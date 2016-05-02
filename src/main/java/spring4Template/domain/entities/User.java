package spring4Template.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_user")
public class User extends Identifiable {

    @Transient public static final String OLD_PASSWORD_MASK = "******";

    private String firstName;
    private String lastName;
    private String name;
    private String password;
    private Boolean systemUser = false;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_user_user_group"))
    private UserGroup group;

    public User(String name, String password, UserGroup group) {
        this.name = name;
        this.password = password;
        this.group = group;
    }

    public User(String firstName, String lastName, String name, String password, Boolean systemUser, UserGroup group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.systemUser = systemUser;
        this.group = group;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
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

    public boolean isSystemUser() {
        return systemUser;
    }

    public void setSystemUser(boolean systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public List<Identifiable> getRelations() {
        List<Identifiable> relations = new ArrayList<>();
        relations.add(this.getGroup());
        return relations;
    }
}
