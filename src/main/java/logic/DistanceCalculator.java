package logic;

import com.google.inject.Inject;
import dao.AccidentDao;
import models.Accident;
import models.CheckAssistanceDTO;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by vlad on 12/20/14.
 */
public final class DistanceCalculator {
    @Inject
    AccidentDao accidentDao;

    public final List<Accident> getNearAccidents(Double lat, Double lng, Double distance){
        List<Accident> list = accidentDao.getAllAccidentsWithStatus(false);
        return list.stream().filter(a -> getDistanceFromLatLonInKm(a.lat, a.lng, lat, lng) <= distance).collect(Collectors.toList());
    }

    private Double getDistanceFromLatLonInKm(Double lat1,Double lng1,Double lat2, Double lon2) {
        int R = 6371; // Radius of the earth in km
        Double dLat = deg2rad(lat2-lat1);  // deg2rad below
        Double dLon = deg2rad(lon2-lng1);
        Double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double d = R * c; // Distance in km
        return d;
    }

    private Double deg2rad(Double deg) {
        return deg * (Math.PI/180);
    }

    private String getGoogleApiUrl(CheckAssistanceDTO dto){
        List<Accident> accidentList = accidentDao.getAllAccidentsWithStatus(false);
        StringBuilder builder = new StringBuilder();
        builder.append("json?");
        builder.append("origins=");
        builder.append(dto.lat);
        builder.append(",");
        builder.append(dto.lng);
        builder.append("&destinations=");
        String dest =
                accidentList.stream().map(a -> a.lat + "," + a.lng).collect(Collectors.joining("|")).toString();
        builder.append(dest);
        builder.append("sensor=false");
        return builder.toString();
    }
}
