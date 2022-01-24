package com.services.impl;

import com.pojos.Comment;
import com.pojos.Lobby;
import com.repositories.CommentRepository;
import com.repositories.LobbyRepository;
import com.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LobbyRepository lobbyRepository;

    @Override
    public boolean addComment(int type, String code, String content) {
        Comment newComment = new Comment();
        switch (type) {
            case 1: //lobby
                Lobby lobby = lobbyRepository.getClientLobbyByCode(code);
                newComment.setLobby(lobby);
                newComment.setContent(content);
                newComment.setCode(content);
                newComment.setCode(UUID.randomUUID().toString());
                break;
        }

        return commentRepository.addComment(newComment);
    }
}
