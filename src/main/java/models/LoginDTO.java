package models;

/**
 * Created by vlad on 12/19/14.
 */
public class LoginDTO {
    public String login;
    public boolean result;
    public LoginDTO() {
    }

    public LoginDTO(String login, boolean result) {
        this.login = login;
        this.result = result;
    }
}
