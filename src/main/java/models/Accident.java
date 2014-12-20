package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.StyledEditorKit;

/**
 * Created by vlad on 12/19/14.
 */
@Entity
public class Accident {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long accidentId;
    public Long deviceId;
    public Long accidentTypeId;
    public boolean resolveStatus;
    public Double lat;
    public Double lng;
    public boolean status;

    public Accident(Long deviceId, Long accidentTypeId, boolean resolveStatus, Double lat, Double lng) {
        this.deviceId = deviceId;
        this.accidentTypeId = accidentTypeId;
        this.resolveStatus = resolveStatus;
        this.lat = lat;
        this.lng = lng;
    }

    public Accident() {
    }
}
