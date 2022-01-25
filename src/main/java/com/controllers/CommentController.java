package com.controllers;

import com.services.CommentService;
import com.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/admin/comments")
    public String showCommentsPage(
            @RequestParam(required = false) Map<String, String> params
    ) {
        return "comments";
    }

    @RequestMapping("/admin/comments/detail/{code}")
    public String showDetailPage(
            @PathVariable(value = "code") String code)  {
        return "commentDetail";
    }
}
