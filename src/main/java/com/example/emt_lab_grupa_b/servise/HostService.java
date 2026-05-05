package com.example.emt_lab_grupa_b.servise;

import com.example.emt_lab_grupa_b.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
}