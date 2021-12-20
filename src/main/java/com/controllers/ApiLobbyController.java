package com.controllers;

import com.pojos.Bill;
import com.pojos.Food;
import com.pojos.Lobby;
import com.services.FoodService;
import com.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/lobby")
public class ApiLobbyController {
    @Autowired
    private LobbyService lobbyService;

    @GetMapping("/select2/lobby-by-name")
    public ResponseEntity<List<Lobby>> getFoodsByName(
            @RequestParam(required = false) Map<String, String> params
    ) {
        String name = params.get("name") == null ? "" : params.get("name");
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        Date beginDate = params.get("beginDate") == null ? new Date() : new Date(Long.parseLong(params.get("beginDate")));
        Date endDate = params.get("endDate") == null ? new Date() : new Date(Long.parseLong(params.get("endDate")));
        List<Lobby> lobbies = lobbyService.getByNameWithDate(name, beginDate, endDate, page);
        return new ResponseEntity<List<Lobby>>(
                lobbies,
                HttpStatus.OK);
    }
}