package spring4Template.users.ws;

public final class UserValidationConstants {

    //Fields
    public static final String USER_NAME_FIELD = "userName";
    public static final String USER_FIRST_NAME_FIELD = "firstName";
    public static final String USER_LAST_NAME_FIELD = "lastName";
    public static final String USER_GROUP_FIELD = "userGroupId";
    public static final String PASSWORD_FIELD = "password";

    //Messages
    public static final String USER_NAME_IS_EMPTY_CODE = "user.empty-name";
    public static final String USER_NAME_IS_EMPTY_MESSAGE = "You have to specify user name";
    public static final String FIRST_NAME_IS_EMPTY_CODE = "user.empty-first-name";
    public static final String FIRST_NAME_IS_EMPTY_MESSAGE = "You have to specify first name";
    public static final String LAST_NAME_IS_EMPTY_CODE = "user.empty-last-name";
    public static final String LAST_NAME_IS_EMPTY_MESSAGE = "You have to specify last name";
    public static final String USER_GROUP_IS_EMPTY_CODE = "user.empty-user-group";
    public static final String USER_GROUP_IS_EMPTY_MESSAGE = "You have to choose user group";
    public static final String PASSWORD_IS_EMPTY_CODE = "user.empty-password-name";
    public static final String PASSWORD_IS_EMPTY_MESSAGE = "You have to specify password";
    public static final String PASSWORD_IS_WEAK_CODE = "user.weak-password";
    public static final String PASSWORD_IS_WEAK_MESSAGE = "Password is too short";

    private UserValidationConstants() {

    }

}
