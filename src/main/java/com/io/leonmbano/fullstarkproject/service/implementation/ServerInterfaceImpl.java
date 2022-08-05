package com.io.leonmbano.fullstarkproject.service.implementation;

import com.io.leonmbano.fullstarkproject.domain.Server;
import com.io.leonmbano.fullstarkproject.enumaration.Status;
import com.io.leonmbano.fullstarkproject.repository.ServerRepository;
import com.io.leonmbano.fullstarkproject.service.ServerInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerInterfaceImpl implements ServerInterface {

    private final ServerRepository serverRepository;
    @Override
    public Server creat(Server server) {
        log.info("Saving new server : {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging server ip: {}",ipAddress);
        Server server =serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? Status.SERVER_UP: Status.SERVER_DOWN);

        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers ");

        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching  server by id : {} ",id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server : {}",server.getName());
              return serverRepository.save(server);
     }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id : {} ",id);
        serverRepository.deleteById(id);
        return  Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames={"server1.jpg","server2.jpg","server3.jpg","server4.jpg"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/"+ imageNames[new Random()
                        .nextInt(4)])
                .toUriString();
    }

}
