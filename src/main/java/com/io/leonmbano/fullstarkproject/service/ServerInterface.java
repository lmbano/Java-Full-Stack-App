package com.io.leonmbano.fullstarkproject.service;

import com.io.leonmbano.fullstarkproject.domain.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerInterface {
    Server creat(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id) ;

}
