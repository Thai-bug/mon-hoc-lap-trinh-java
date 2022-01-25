package com.controllers;

import com.pojos.Comment;
import com.pojos.Drink;
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
        int stars = json.get("stars") == null ? 1 : Integer.parseInt(json.get("stars").toString());
        int type = json.get("type") == null ? 0 : Integer.parseInt(json.get("type").toString());
        if (code == "" || type == 0)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        boolean addComment = commentService.addComment(type, code, content, stars);
        if (addComment)
            return new ResponseEntity<>(null, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/admin/get_all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestBody Map<String, Object> json
    ) {
        int start = json.get("start") == null ? 1 : Integer.parseInt(json.get("start").toString());
        int length = json.get("length") == null ? 0 : Integer.parseInt(json.get("length").toString());
        Map<String, String> searchObj = (Map<String, String>) json.get("search");
        Set<Comment> comments = commentService.getComments(searchObj.get("value"), start, length);
        int total = commentService.getTotal(searchObj.get("value"));
        Map<String, Object> result = new HashMap<>();
        result.put("data", comments);
        result.put("recordsFiltered", total);
        result.put("recordsTotal", total);
        result.put("draw", json.get("draw"));
        return new ResponseEntity(
                result,
                HttpStatus.OK);
    }

    @GetMapping(value = "/admin/detail/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getComment(
            @PathVariable("code") String code
    ) {
        Map<String, Object> result = new HashMap<>();
        Comment comment = commentService.getCommentByCode(code);
        result.put("result", comment);
        return new ResponseEntity<>(result, HttpStatus.OK);
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

    @PutMapping(value = "/admin/active/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> activeComment(
            @PathVariable("code") String code
    ) {
        Map<String, Object> response = new HashMap<>();
        boolean result = commentService.updateCommentStatus(code, true);
        if (result) {
            response.put("result", "Cập nhật thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("result", "Cập nhật thất bại");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/admin/disabled/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> disableComment(
            @PathVariable("code") String code
    ) {
        Map<String, Object> response = new HashMap<>();
        boolean result = commentService.updateCommentStatus(code, false);
        if (result) {
            response.put("result", "Cập nhật thành công");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("result", "Cập nhật thất bại");
        return new ResponseEntity<>(response,    HttpStatus.BAD_REQUEST);
    }
}
