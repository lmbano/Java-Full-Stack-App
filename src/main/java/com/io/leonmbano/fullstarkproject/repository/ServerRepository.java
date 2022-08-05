package com.io.leonmbano.fullstarkproject.repository;

import com.io.leonmbano.fullstarkproject.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server,Long> {

    Server findByIpAddress(String ipAddress);
}
