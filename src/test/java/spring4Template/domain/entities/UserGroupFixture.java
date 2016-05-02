package spring4Template.domain.entities;

import java.util.List;

public abstract class UserGroupFixture {

    public static UserGroup createDefaultUserGroup(){
        return builder().build();
    }

    public static UserGroupBuilder builder(){
        return new UserGroupBuilder();
    }

    public static class UserGroupBuilder {
        private String name = "UserGroupName";
        private List<UserAuthority> authorities;

        public UserGroupBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserGroupBuilder setAuthorities(List<UserAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserGroup build() {
            UserGroup userGroup = new UserGroup(name);
            userGroup.setAuthorities(authorities);
            return userGroup;
        }
    }

}