package com.controllers;

import com.pojos.Lobby;
import com.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map;

@Controller
@RequestMapping("")
public class LobbyController {
    @Autowired
    private LobbyService lobbyService;

    @RequestMapping("/admin/lobbies")
    public String getLobby(
            Model model,
            @RequestParam(required = false) Map<String, String> params,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
    ) {
        return "lobbies";
    }

    @RequestMapping("/admin/lobby/{code}")
    public String detailLobby(Model model, @PathVariable(value = "code") String code) {
        Lobby lobby = lobbyService.getLobbyByCode(code);

        model.addAttribute("lobby", lobby);
        return "lobbyDetail";
    }

    @PostMapping("/admin/lobby/update/{code}")
    public String updateEmployee(Model model,
                                 @ModelAttribute(value = "lobby") Lobby lobby) {
        return "redirect:" + lobby.getId();
    }

    @RequestMapping("/admin/lobby/update/{code}")
    public String updatePage(Model model,
                                 @PathVariable(value = "code") String code) {
        return "lobbyUpdate";
    }

    @RequestMapping("/admin/lobby/create")
    public String create(Model model) {
        return "lobbyCreate";
    }

    @GetMapping("/lobbies")
    public  String indexClientLobbies(){
        return "client-lobby";
    }

    @GetMapping("/lobbies/{code}")
    public  String clientLobbyDetail(){
        return "client-lobby-detail";
    }
}
