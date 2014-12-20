/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import models.Device;
import models.LoginDTO;
import models.UserDTO;
import net.spy.memcached.MemcachedClient;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.cache.NinjaCache;
import ninja.params.Param;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.UserDao;

@Singleton
public class LoginLogoutController {
    @Inject
    NinjaCache ninjaCache;
    @Inject
    UserDao userDao;
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Login
    ///////////////////////////////////////////////////////////////////////////
    public Result login(Context context) {
        return Results.html();

    }

    public Result loginPost(UserDTO user, Context context) {

        boolean isUserNameAndPasswordValid = userDao.isUserAndPasswordValid(user.login, user.password);
        
        
        if (isUserNameAndPasswordValid) {
            context.getSessionCookie().put("username", user.login);
            context.getFlashCookie().success("login.loginSuccessful");
            if (!userDao.isDeviceExist(user.login,user.deviceId))
                userDao.pairDevice(user.login ,parceToDevice(user));
            return Results.json().render(new LoginDTO(user.login,true));
            
        } else {
            // something is wrong with the input or password not found.
            context.getFlashCookie().put("username", user.login);
            context.getFlashCookie().error("login.errorLogin");
            return Results.json().render(new LoginDTO(user.login,false));
            
        }
        
    }

    private Device parceToDevice(UserDTO dto){
        Device device = new Device();
        device.lat = dto.lat;
        device.lng = dto.lng;
        return device;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout
    ///////////////////////////////////////////////////////////////////////////
    public Result logout(Context context) {

        // remove any user dependent information
        context.getSessionCookie().clear();
        context.getFlashCookie().success("login.logoutSuccessful");

        return Results.redirect("/");

    }

}
