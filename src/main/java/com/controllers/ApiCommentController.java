package com.controllers;

import com.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/comments")
public class ApiCommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Map<String, Object>> addComment(@RequestBody Map<String, Object> json) {
        String content = json.get("content") == null ? "" : json.get("content").toString();
        String code = json.get("code") == null ? "" : json.get("code").toString();
        int type = json.get("type") == null ? 0 :  Integer.parseInt(json.get("type").toString());
        if(code == "" || type == 0)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        boolean addComment = commentService.addComment(type, code, content);
        if(addComment)
            return new ResponseEntity<>(null, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> listComments(
            @RequestParam(required = false) Map<String, String> params
    ){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
