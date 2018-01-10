package com.kek.kekMovie.Controllers;


import com.kek.kekMovie.DTO.User;
import com.kek.kekMovie.DTO.VerificationToken;
import com.kek.kekMovie.Services.Exceptions.EmailExistsException;
import com.kek.kekMovie.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/registration")
public class Registration {

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView getRegistrationForm(){
        return new ModelAndView("registration");
    }


    @PostMapping
    public ModelAndView registerUserAccount(@RequestParam Map<String, String> params){
        System.out.println("PostMapping /registration");
        System.out.println(params);
        if (params.get("username") == null || params.get("username") == "" ||
                params.get("email") == null || params.get("email") == "" ||
                params.get("password") == null || params.get("password") == "" ||
                params.get("password_confirm") == null || params.get("password_confirm") == "")
            return null;
        if (!params.get("password").equals(params.get("password_confirm")))
            return null;
        try {
            VerificationToken token = userService.registerUserAccount(
                    params.get("username"),
                    params.get("password"),
                    params.get("email"));
            System.out.print("Token: ");
            System.out.println(token);
            Map<String, String> model = new HashMap<>();
            model.put("username", token.getUser().getUsername());
            model.put("email", token.getUser().getEmail());
            model.put("token", token.getToken());
            model.put("link", "http://127.0.0.1:5000/registration/confirm_account?token=" + token.getToken());
            return new ModelAndView("registration_confirm", model);
        } catch (EmailExistsException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/confirm_account")
    public User confirmAccount(@ModelAttribute(value = "token") String token){
        return userService.confirmUserAccount(token);
    }
}
