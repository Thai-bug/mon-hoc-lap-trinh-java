package com.services.impl;

import com.pojos.Lobby;
import com.repositories.LobbyRepository;
import com.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyServiceImpl implements LobbyService {
    @Autowired
    private LobbyRepository lobbyRepository;

    @Override
    public List<Lobby> getLobbies(String kw, int page) {
        return lobbyRepository.getLobbies(kw, page);
    }

    @Override
    public int countLobby(String kw) {
        return lobbyRepository.countLobby(kw);
    }

    @Override
    public Lobby getLobbyById(int id) {
        return lobbyRepository.getLobbyById(id);
    }

    @Override
    public boolean updateLobby(Lobby lobby) {
        return lobbyRepository.updateLobby(lobby);
    }

    @Override
    public boolean createLobby(Lobby lobby) {
        return lobbyRepository.createLobby(lobby);
    }
}
