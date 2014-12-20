package controllers;

/**
 * Created by vlad on 12/19/14.
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.AccidentDao;
import dao.UserDao;
import jdk.internal.util.xml.impl.ReaderUTF8;
import logic.DistanceCalculator;
import models.*;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.cache.NinjaCache;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class NotificationController {

    private static final String GOOGLE_MAP_API = "http://maps.googleapis.com/maps/api/distancematrix/";
    private static final Double RANGE = 1d;

    @Inject
    NinjaCache ninjaCache;
    @Inject
    AccidentDao accidentDao;
    @Inject
    UserDao userDao;
    public Result receiveAssistance(Accident accident) {
        if (accident!=null && validateAccident(accident)){
            accidentDao.createAccident(accident);
            ninjaCache.add(accident.accidentId.toString(), accident , "30mn");
            return Results.status(201);

        }
        return Results.status(200);

    }

    public Result checkAssistence(CheckAssistanceDTO dto) throws IOException {
        List<Accident> list = new DistanceCalculator().getNearAccidents(dto.lat,dto.lng, RANGE);
      return Results.json().render(collectAccidents(list));
    }

    private AccidentDTO collectAccidents(List<Accident> accidents){
        AccidentDTO accidentDTO = new AccidentDTO();
        accidentDTO.list =  accidents.stream().map(a->new Coords(a.lat,a.lng)).collect(Collectors.toList());
        return accidentDTO;
    }
    private boolean validateAccident(Accident accident) {
        return true;
    }




}
