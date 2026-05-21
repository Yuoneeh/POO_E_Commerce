package entities;

public class AdminUser extends User{

    public static String admin_username;
    public static String admin_password;

    public AdminUser(String username, String password, String email, String secretQuestion, String secretAnswer) {
        super(username, password, email, secretQuestion, secretAnswer);
        admin_username = "admin";
        admin_password = "1234";
        username = admin_username;
        password = admin_password;
    }

    public static String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public static String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

}
