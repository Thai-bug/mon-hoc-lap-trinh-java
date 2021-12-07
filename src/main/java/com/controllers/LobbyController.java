package com.controllers;

import com.pojos.Employee;
import com.pojos.Lobby;
import com.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        int total = lobbyService.countLobby(kw);

        model.addAttribute("lobbies", lobbies);
        model.addAttribute("total", total);
        return "lobbies";
    }

    @RequestMapping("/lobby/{id}")
    public String detailLobby(Model model, @PathVariable(value = "id") int id) {
        Lobby lobby = lobbyService.getLobbyById(id);

        model.addAttribute("lobby", lobby);
        return "lobbyDetail";
    }

    @PostMapping("/lobby/update/{id}")
    public String updateEmployee(Model model,
                                 @ModelAttribute(value = "lobby") Lobby lobby) {

        boolean updateLobby = lobbyService.updateLobby(lobby);
        model.addAttribute("lobby", lobby);
        if (updateLobby) {
            return "lobbyDetail";
        }
        return "redirect:" + lobby.getId();
    }

    @RequestMapping("/lobby/update/{id}")
    public String updatePage(Model model,
                                 @PathVariable(value = "id") int id) {
        Lobby lobby = lobbyService.getLobbyById(id);
        model.addAttribute("lobby", lobby);
        return "lobbyUpdate";
    }

    @RequestMapping("/lobby/create")
    public String create(Model model) {
        model.addAttribute("lobby", new Lobby());
        return "lobbyCreate";
    }

    @PostMapping("/lobby/create")
    public String createLobby(
            Model model,
            @ModelAttribute(value = "lobby") Lobby lobby
    ) {
        boolean updateLobby = lobbyService.createLobby(lobby);
        model.addAttribute("lobby", lobby);
        if (updateLobby) {
            model.addAttribute("lobby", lobby);
            return "redirect:/admin/lobbies";
        }
        return "redirect:";
    }
}
