package com.controllers;

import com.SubClass;
import com.pojos.Bill;
import com.pojos.Food;
import com.pojos.Lobby;
import com.services.FoodService;
import com.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ApiLobbyController {
    @Autowired
    private LobbyService lobbyService;

    @GetMapping("/admin/lobby/select2/lobby-by-name")
    public ResponseEntity<Set<Lobby>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        Date beginDate = params.get("beginDate") == null ? new Date() : new Date(Long.parseLong(params.get("beginDate")));
        Date endDate = params.get("endDate") == null ? new Date() : new Date(Long.parseLong(params.get("endDate")));
        int id = params.get("currentId") == null ? 0 : Integer.parseInt(params.get("currentId"));
        int seats = params.get("seats") == null ? 0 : Integer.parseInt(params.get("seats"));
        Set<Lobby> lobbies = lobbyService.getByNameWithDate(name, beginDate, endDate, page, id, seats);
        return new ResponseEntity<Set<Lobby>>(
                lobbies,
                HttpStatus.OK);
    }

    @PostMapping(value = "/admin/lobby/get_all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Map<String, Object>> findLobby(@RequestBody Map<String, Object> json){
        String type = (String) json.get("type");
        int start = json.get("start") == null ? 1 : Integer.parseInt(json.get("start").toString()) + 1;
        int length = json.get("length") == null ? 0 : Integer.parseInt(json.get("length").toString());
        Map<String, String> searchObj= (Map<String, String>) json.get("search");
        Set<Lobby> data = lobbyService.getLobbies(searchObj.get("value"), start, length);
        int total = lobbyService.countLobby(searchObj.get("value"));
        Map<String, Object> result = new HashMap<>() ;
        result.put("data", data);
        result.put("recordsFiltered", total);
        result.put("recordsTotal", total);
        result.put("draw", json.get("draw"));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/lobbies")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getLobbiesForClient(
            @RequestParam(required = false) Map<String, String> params
    ){
        try{
            int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
            int limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit"));
            String kw = params.get("kw") == null ? "" : params.get("kw").toLowerCase(Locale.ROOT);
            Map<String, Object> result = lobbyService.getLobbiesForClient(page, limit, kw);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }

    }

    @GetMapping(value = "/lobbies/{code}")
    public @ResponseBody
    ResponseEntity<Map<String, Lobby>> getLobbyForClient(
            @PathVariable(value = "code") String code
    ){
        try{
            return new ResponseEntity<>(lobbyService.getClientLobbyByCode(code), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }

    }
}
