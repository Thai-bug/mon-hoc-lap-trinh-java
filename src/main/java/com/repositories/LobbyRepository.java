package com.repositories;

import com.pojos.Lobby;

import java.util.Date;
import java.util.Set;

public interface LobbyRepository {
    Set<Lobby> getLobbies(String kw, int page);

    int countLobby(String kw);

    Lobby getLobbyById(int id);

    boolean updateLobby(Lobby lobby);

    boolean createLobby(Lobby lobby);

    Set<Lobby> getByNameWithDate(String name, Date beginDate, Date endDate, int page, int id);
}
