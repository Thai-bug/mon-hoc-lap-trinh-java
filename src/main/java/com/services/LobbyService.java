package com.services;

import com.pojos.Lobby;

import java.util.List;

public interface LobbyService {
    List<Lobby> getLobbies(String kw, int page);

    int countLobby(String kw);

    Lobby getLobbyById(int id);

    boolean updateLobby(Lobby lobby);

    boolean createLobby(Lobby lobby);
}
