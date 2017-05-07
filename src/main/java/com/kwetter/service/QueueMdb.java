package com.kwetter.service;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Niek on 6-5-2017.
 */

@Named
@MessageDriven(mappedName = "jms/KwetterGo", activationConfig = {@ActivationConfigProperty(propertyName = "acknowledgeMode",
        propertyValue = "Auto-acknowledge"), @ActivationConfigProperty(propertyName = "destinationType",
        propertyValue = "javax.jms.Queue")})
public class QueueMdb implements Serializable, MessageListener{

    private static final Logger LOG = Logger.getLogger(QueueMdb.class.getName());

    @Resource
    private MessageDrivenContext mdc;

    @Inject
    KweetService kweetService;

    public QueueMdb() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage txtMessage = (TextMessage) message;
            String newMessage = txtMessage.getText();
            LOG.info(newMessage);
            String username = newMessage.split(" : ")[0];
            String kweetText = newMessage.split(" : ")[1];

            User user = kweetService.findByUserName(username);
            if (user == null) {
                System.out.println("User " + username + " doesn't seem to exist.");
                return;
            }
            Kweet k = new Kweet(kweetText,user);
            kweetService.placeKweet(k);

            System.out.println("User " + username + " has created an Kweet.");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error in message: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }


}
