package spring4Template.users.domain.entities;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserAuthorities {

    public static final String ROLE_DEFAULT = "ROLE_DEFAULT";
    public static final String ROLE_USER_EDIT = "ROLE_USER_EDIT";
    public static final String ROLE_USER_VIEW = "ROLE_USER_VIEW";
    public static final String ROLE_USER_DELETE = "ROLE_USER_DELETE";
    public static final String ROLE_CONFIGURATION_VIEW = "ROLE_CONFIGURATION_VIEW";

}
