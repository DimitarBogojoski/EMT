package com.example.emt_lab_grupa_b.servise.Imp;

import com.example.emt_lab_grupa_b.model.domain.Host;
import com.example.emt_lab_grupa_b.repository.HostRepository;
import com.example.emt_lab_grupa_b.servise.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }
}