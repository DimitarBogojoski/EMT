package com.example.emt_lab_grupa_b.repository;

import com.example.emt_lab_grupa_b.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
