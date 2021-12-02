package com.repositories;

import com.pojos.Lobby;

import java.util.List;

public interface LobbyRepository {
    List<Lobby> getLobbies(String kw, int page);

    int countLobby(String kw);

    Lobby getLobbyById(int id);
}
