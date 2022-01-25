package com.repositories;

import com.pojos.Lobby;

import javax.persistence.Lob;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface LobbyRepository {
    Set<Lobby> getLobbies(String kw, int page, int length);

    int countLobby(String kw);

    Lobby getLobbyByCode(String code);

    boolean updateLobby(Lobby lobby);

    boolean createLobby(Lobby lobby);

    Set<Lobby> getByNameWithDate(String name, Date beginDate, Date endDate, int page, int id, int seats);

    Set<Lobby> getLobbiesForClient(int page, int limit, String kw);

    int countLobbyClient(String kw);

    Lobby getClientLobbyByCode(String code);
}
