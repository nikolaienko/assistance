package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.UserDao;
import models.LoginDTO;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.Results;
/**
 * Created by vlad on 12/19/14.
 */

@Singleton
public class RegistrationController {
    @Inject
    UserDao userDao;

    public Result parseUser(User person) {
        if (!validate(person) && !userDao.createUser(person))
            return Results.json().render(new LoginDTO(person.login,false));
        return Results.json().render(new LoginDTO(person.login,true));

    }

    private boolean validate(User user){
        return true;
    }
}
