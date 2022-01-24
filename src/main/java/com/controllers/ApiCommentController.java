package com.controllers;

import com.pojos.Comment;
import com.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/comments")
public class ApiCommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Map<String, Object>> addComment(@RequestBody Map<String, Object> json) {
        String content = json.get("content") == null ? "" : json.get("content").toString();
        String code = json.get("code") == null ? "" : json.get("code").toString();
        int type = json.get("type") == null ? 0 : Integer.parseInt(json.get("type").toString());
        if (code == "" || type == 0)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        boolean addComment = commentService.addComment(type, code, content);
        if (addComment)
            return new ResponseEntity<>(null, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> listComments(
            @RequestParam(required = false) Map<String, String> params
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        int limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit"));
        int codeType = params.get("type") == null ? 1 : Integer.parseInt(params.get("type"));
        String code = params.get("code") == null ? "" : params.get("code");

        Set<Comment> comments = commentService.listComments(1, "", page, limit, codeType, code);
        int total = commentService.countComments(1, "", codeType, code);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", comments);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
