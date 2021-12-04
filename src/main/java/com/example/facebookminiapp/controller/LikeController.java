package com.example.facebookminiapp.controller;

import com.example.facebookminiapp.model.User;
import com.example.facebookminiapp.service.serviceImplementation.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class LikeController {
    @Autowired
    LikeServiceImpl likeService;

    /**
     * Post request to process like made by users
     * redirects user if not in session
     * saves or deletes like in or from the database, or perhaps fails
     * redirect back to home page
     * */
    @RequestMapping(value = "/processLike", method = RequestMethod.POST)
    public @ResponseBody String likePost(HttpServletRequest request, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if(user == null) return "redirect:/";

        Long postId = Long.parseLong(request.getParameter("postId"));
        String action = request.getParameter("action");

        if(likeService.likePost(user, postId, action)){
            return "successful";
        }else{
            session.setAttribute("message", "server error");
        }

        return "";
    }
}
