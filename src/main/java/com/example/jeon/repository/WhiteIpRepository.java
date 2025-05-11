package com.example.jeon.repository;

import com.example.jeon.Entity.WhiteIp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WhiteIpRepository extends CrudRepository<WhiteIp, Long> {
    Optional<WhiteIp> findByAccessIp(String ip);
}

