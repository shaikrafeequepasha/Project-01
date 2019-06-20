package com.persistent.searchengine.auth.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.persistent.searchengine.auth.model.User;
import com.persistent.searchengine.auth.service.SecurityService;
import com.persistent.searchengine.auth.service.UserService;
import com.persistent.searchengine.auth.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
    
    
    @RequestMapping(value = "/tags/get_tag_list",  method = RequestMethod.GET)
    public @ResponseBody List<User> getTagList(@RequestParam("username") String username) {
    	
    	List<User> userList =  userService.findByUsernameContaining(username);
    	
    	System.out.println("userList::::"+userList);
    	//userList.clear();
        return userList;
        
    }
    
    @GetMapping({"/searchForm"})
    public String search(Model model) {
        return "search";
    }

}
