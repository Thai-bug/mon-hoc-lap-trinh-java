package com.services;

import com.pojos.Bill;
import com.pojos.Lobby;

import javax.persistence.Lob;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface LobbyService {
    Set<Lobby> getLobbies(String kw, int page, int length);

    int countLobby(String kw);

    Lobby getLobbyByCode(String code);

    boolean updateLobby(Map<String, Object> lobby);

    boolean createLobby(Map<String, Object> json);

    Set<Lobby> getByNameWithDate(String name, Date beginDate, Date endDate, int page, int id, int seats);

    Map<String, Object> getLobbiesForClient(int page, int limit, String kw);

    Map<String, Lobby> getClientLobbyByCode(String code);
}
