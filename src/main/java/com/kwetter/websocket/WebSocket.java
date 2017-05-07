package com.kwetter.websocket;

import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Niek on 1-5-2017.
 */

@ServerEndpoint("/socket/{username}")
public class WebSocket {
    private KweetService kweetService;

    @Inject
    public WebSocket(KweetService ks) {
        kweetService = ks;
    }

    private static final Logger LOG = Logger.getLogger(WebSocket.class.getName());

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        LOG.info("Connection has been opened by ... " + session.getId());
        SessionLister.getInstance().getSessionMap().put(username, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        SessionLister.getInstance().getSessionMap().remove(username);
        LOG.info("Connection has been closed by ... " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        List<String> usernames = SessionLister.getInstance().getActiveUsers();


        LOG.info(message);
        User user = kweetService.findByUserName(username);

        for (User following : user.getFollowing()) {
            if (usernames.contains(following.getUserName())) {
                LOG.info("DIKKE BMW" + following.getUserName());
                SessionLister.getInstance().getSessionMap().get(following.getUserName()).getAsyncRemote().sendText("New Kweet: " + message + " placed by " + user.getUserName());
            }
        }
        SessionLister.getInstance().getSessionMap().get(user.getUserName()).getAsyncRemote().sendText("You placed the following kweet: " + message);
    }
}
