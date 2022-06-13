package com.springbooth.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springbooth.model.dto.UserDTO;
import com.springbooth.model.entity.User;
import com.springbooth.service.UserService;

import java.util.logging.Logger;

@Controller
public class LoginController {
    private final Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDTO", new UserDTO());
        modelAndView.setViewName("registration");
        logger.info("This is registration page");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByEmail(userDTO.getEmail());
        if (user == null) {

            userService.saveUser(userDTO);
            modelAndView.addObject("user", new UserDTO());
            modelAndView.setViewName("login");
        } else {
            logger.warning("This eamil already exist.");
            modelAndView.addObject("successMessage", "This eamil already exist...!");
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "user/info", method = RequestMethod.GET)
    public ModelAndView userProfile() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user = userService.findUserByEmail(auth.getName());

        modelAndView.addObject("user", user);
        modelAndView.addObject("userName", user.getFirstName() + " " + user.getLastName());
        modelAndView.setViewName("user-profile");
        return modelAndView;
    }

    @RequestMapping(value = "/change/password", method = RequestMethod.GET)
    public ModelAndView changePassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDTO", new UserDTO());
        modelAndView.setViewName("password-update");
        return modelAndView;
    }

    @RequestMapping(value = "/new/password", method = RequestMethod.POST)
    public ModelAndView newPassword(UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (userDTO.getNewPass().equals(userDTO.getConfirmPass())) {

            User user = userService.findUserByEmail(auth.getName());
            Boolean status = userService.isPasswordValid(userDTO.getPassword(), user.getPassword());
            if (status == true) {

                userService.changePasswor(user, userDTO);
                modelAndView.setViewName("login");
            } else {
                logger.warning("Invalid Password");
                modelAndView.addObject("wrongPass", "Current possword was wrong..!");
                modelAndView.setViewName("password-update");
            }

        } else {
            logger.warning("Password should be same ");
            modelAndView.addObject("passMatched", "Password doesn't matched..!");
            modelAndView.setViewName("password-update");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        try {
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            String name = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            System.out.println(name);
            modelAndView.addObject("userName",
                    "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
            modelAndView.setViewName("admin/home");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Should be valid All authentication Criteria ");
        }
        return null;
    }

}
