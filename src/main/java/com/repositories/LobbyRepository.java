package com.repositories;

import com.pojos.Lobby;

import java.util.Date;
import java.util.List;

public interface LobbyRepository {
    List<Lobby> getLobbies(String kw, int page);

    int countLobby(String kw);

    Lobby getLobbyById(int id);

    boolean updateLobby(Lobby lobby);

    boolean createLobby(Lobby lobby);

    List<Lobby> getByNameWithDate(String name, Date beginDate, Date endDate, int page);
}
