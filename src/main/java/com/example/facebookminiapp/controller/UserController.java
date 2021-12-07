package com.example.facebookminiapp.controller;

import com.example.facebookminiapp.otherModel.LoginDetails;
import com.example.facebookminiapp.otherModel.PostDetails;
import com.example.facebookminiapp.model.Comment;
import com.example.facebookminiapp.model.Post;
import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.service.serviceImplementation.UserServiceImpl;
import com.example.facebookminiapp.service.serviceImplementation.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PostServiceImpl postService;

    /**
     * Get request to get the login and sign up page
     * destroys message attribute that appears as the results of redirection
     * due to unauthorized access to this page
     * maps objects to receive data from the forms
     * renders the page
     * */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("message");

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", new User());
        mav.addObject("login", new LoginDetails());

        return mav;
    }

    /**
     * Get request to process logging out to the index page
     * destroy every attributes saved in session
     * redirect to index page
     * */
    @RequestMapping(value = "/processLogout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

    /**
     * Get request to get the facebook home page
     * maps objects to receive data from the forms
     * renders the home page
     * */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(HttpServletRequest request, Model model) {

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        if(user == null) {
//            ModelAndView mav = new ModelAndView("index");
            model.addAttribute("user1", new User());
            model.addAttribute("login", new LoginDetails());
            httpSession.setAttribute("messagee", "!!!Please Login");
            return "index";
        }

        List<PostDetails> post = postService.getPost(user);
//        ModelAndView mav = new ModelAndView("home");

        model.addAttribute("user", user);
        model.addAttribute("posts", post);

        model.addAttribute("post", new Post());
        model.addAttribute("commentData", new Comment());

        return "home";
    }

    /**
     * Post request to process user registration
     * create a session to hold success or error message
     * redirect to the index page
     * */
    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, @ModelAttribute("user") User user) {

        HttpSession httpSession = request.getSession();
        //data check

        if (user.getFirstname().length() < 2) {
            httpSession.setAttribute("messagee", " firstname cannot be less than 2 character long");
            return "redirect:/";
        }

        if (user.getLastname().length() < 2) {
            httpSession.setAttribute("messagee", "lastname cannot be less than 2 character long");
            return "redirect:/";
        }


        if (user.getEmail() == null) {
            httpSession.setAttribute("messagee", "email cannot be null");
            return "redirect:/";
        } else if(!isValidEmail(user.getEmail())){
            httpSession.setAttribute("messagee", "email is not valid");
            return "redirect:/";
        }
        if (user.getPassword().length() < 7) {
            httpSession.setAttribute("messagee", "password cannot be less than 6 character long");
            return "redirect:/";
        }

        if(userService.createUser(user)){
            httpSession.setAttribute("messagee", "Successfully registered!!!");
        }else{
            httpSession.setAttribute("messagee", "Failed to register or email already exist");
        }

        return "redirect:/";
    }

    /**
     * Get request to get the login page
     * maps objects to receive data from the forms
     * */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("login", new LoginDetails());

        return mav;
    }

    /**
     * Post request to process user login
     * create a session to hold success or error message
     * redirect to the index page or home page
     * */
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public String loginProcess(HttpServletRequest request, @ModelAttribute("login") LoginDetails loginDetails) {

        User user = userService.getUser(loginDetails.getEmail(), loginDetails.getPassword());

        HttpSession httpSession = request.getSession();

        if (user != null) {
            httpSession.setAttribute("user", user);
            return "redirect:/home";
        } else {
            httpSession.setAttribute("messagee", "Email or Password is wrong!!!");
            return "redirect:/";
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
