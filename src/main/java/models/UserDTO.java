package models;

/**
 * Created by vlad on 12/19/14.
 */
public class UserDTO {
    public String login;
    public Long password;
    public Long deviceId;
    public Double lat;
    public Double lng;

    public UserDTO(String login, Long password, Long deviceId, Double lat, Double lng) {
        this.login = login;
        this.password = password;
        this.deviceId = deviceId;
        this.lat = lat;
        this.lng = lng;
    }
}
