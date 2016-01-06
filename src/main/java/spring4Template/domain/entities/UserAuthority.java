package spring4Template.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_authority")
@Entity
public class UserAuthority extends Identifiable{

    @Column(nullable = false, unique = true)
    private String name;

    public UserAuthority() {
    }

    public UserAuthority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
