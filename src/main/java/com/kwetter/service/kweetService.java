package com.kwetter.service;

import com.kwetter.dao.KweetDAO;
import com.kwetter.dao.UserDAO;

import javax.ejb.*;
import javax.inject.*;

/**
 * Created by Niek on 6-3-2017.
 */

@Stateless
@Named
public class kweetService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private KweetDAO kweetDAO;

}
