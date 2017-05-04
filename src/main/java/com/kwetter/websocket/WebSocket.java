//package com.kwetter.websocket;
//
//import com.kwetter.model.User;
//import com.kwetter.service.KweetService;
//
//import javax.inject.Inject;
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.util.List;
//
///**
// * Created by Niek on 1-5-2017.
// */
//
//@ServerEndpoint("/socket/{username}")
//public class WebSocket {
//    private KweetService kweetService;
//
//    @Inject
//    public WebSocket(KweetService ks) {
//        kweetService = ks;
//    }
//
//    @OnOpen
//    public void onOpen(Session session, @PathParam("username") String username) {
//        SessionLister.getInstance().getSessionMap().put(username, session);
//    }
//
//    @OnClose
//    public void onClose(Session session, @PathParam("username") String username) {
//        SessionLister.getInstance().getSessionMap().remove(username);
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session, @PathParam("username") String username) {
//        List<String> usernames = SessionLister.getInstance().getActiveUsers();
//
//        User user = kweetService.findByUserName(username);
//
//        for (User follower : user.getFollowers()) {
//            if (usernames.contains(follower.getUserName())) {
//                SessionLister.getInstance().getSessionMap().get(follower.getUserName()).getAsyncRemote().sendText(message);
//            }
//        }
//        SessionLister.getInstance().getSessionMap().get(user.getUserName()).getAsyncRemote().sendText(message);
//    }
//}
