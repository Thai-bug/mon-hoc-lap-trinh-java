package com.services.impl;

import com.pojos.Bill;
import com.pojos.Lobby;
import com.repositories.LobbyRepository;
import com.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Lob;
import java.util.*;

@Service
public class LobbyServiceImpl implements LobbyService {
    @Autowired
    private LobbyRepository lobbyRepository;

    @Override
    public Set<Lobby> getLobbies(String kw, int page, int length) {
        return lobbyRepository.getLobbies(kw, page, length);
    }

    @Override
    public int countLobby(String kw) {
        return lobbyRepository.countLobby(kw);
    }

    @Override
    public Lobby getLobbyByCode(String code) {
        return lobbyRepository.getLobbyByCode(code);
    }

    @Override
    public boolean updateLobby(Map<String, Object> json) {
        Lobby lobby = lobbyRepository.getLobbyByCode((String) json.get("code"));
        lobby.setName((String) json.get("name").toString());
        lobby.setDescription((String) json.get("description").toString());
        lobby.setSeats(Integer.parseInt(json.get("seats").toString()));
        lobby.setMoney(Integer.parseInt(json.get("money").toString()));
        lobby.setStatus((boolean) json.get("status"));

        return lobbyRepository.updateLobby(lobby);
    }

    @Override
    public boolean createLobby(Map<String, Object> json) {
        Lobby lobby = new Lobby();
        lobby.setCode(UUID.randomUUID().toString());
        lobby.setName(json.get("name").toString());
        lobby.setDescription(json.get("description").toString());
        lobby.setSeats(Integer.parseInt(json.get("seats").toString()));
        lobby.setMoney(Integer.parseInt(json.get("money").toString()));
        lobby.setStatus(true);
        lobby.setCreatedAt(new Date());
        System.out.println(lobby.getCode());
        return lobbyRepository.createLobby(lobby);
    }

    @Override
    public Set<Lobby> getByNameWithDate(String name, Date beginDate, Date endDate, int page, int id, int seats) {
        return lobbyRepository.getByNameWithDate(name, beginDate, endDate, page, id, seats);
    }

    @Override
    public Map<String, Object> getLobbiesForClient(int page, int limit, String kw) {
        Set<Lobby> lobbies = lobbyRepository.getLobbiesForClient( page, limit, kw);

        int total = lobbyRepository.countLobbyClient(kw);

        Map<String, Object> result = new HashMap<>() ;

        result.put("total", total);
        result.put("data", lobbies);

        return result;
    }

    @Override
    public Map<String, Lobby> getClientLobbyByCode(String code) {
        Map<String, Lobby> data = new HashMap<>();
        data.put("result", lobbyRepository.getClientLobbyByCode(code));
        return data;
    }
}
