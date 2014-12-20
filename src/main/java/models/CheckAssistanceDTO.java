package models;

/**
 * Created by vlad on 12/20/14.
 */
public class CheckAssistanceDTO {
    public String login;
    public Double lat;
    public Double lng;

    public CheckAssistanceDTO() {
    }

    public CheckAssistanceDTO(String login, Double lat, Double lng) {

        this.login = login;
        this.lat = lat;
        this.lng = lng;
    }
}
