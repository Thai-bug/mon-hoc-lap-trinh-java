package com.controllers;

import com.pojos.Employee;
import com.pojos.Lobby;
import com.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LobbyController {
    @Autowired
    private LobbyService lobbyService;

    @RequestMapping("/lobbies")
    public String getLobby(
            Model model,
            @RequestParam(required = false) Map<String, String> params,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
    ) {
        int page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        String kw = params.get("kw") == null ? "" : params.get("kw");

        List<Lobby> lobbies = lobbyService.getLobbies(kw, page);
        System.out.println(lobbies.size());
        int total = lobbyService.countLobby(kw);
        model.addAttribute("lobbies", lobbies);
        model.addAttribute("total", total);
        return "lobbies";
    }

    @RequestMapping("/lobby/${id}")
    public String detailLobby(Model model, @PathVariable(value = "id") int id){
        Lobby lobby = lobbyService.getLobbyById(id);
        model.addAttribute("lobby", lobby);
        return "";
    }

    @PostMapping("/lobby/update")
    public String updateLobby(Model model, @PathVariable(value = "id") int id){
        Lobby lobby = lobbyService.getLobbyById(id);
        model.addAttribute("lobby", lobby);
        return "";
    }
}
