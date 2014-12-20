package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by vlad on 12/19/14.
 */
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long deviceId;
    public Long userId;
    public Double lat;
    public Double lng;
    public boolean isAlive;

    public Device() {
    }

    public Device(Long userId, Double lat, Double lng, boolean isAlive) {
        this.userId = userId;
        this.lat = lat;
        this.lng = lng;
        this.isAlive = isAlive;
    }
}
