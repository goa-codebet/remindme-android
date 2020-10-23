package se.codebet.remindme.data.models;

public class UserData {

    private String email;
    private String password;

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
