package com.example.emt_lab_grupa_b.servise.Imp;

import com.example.emt_lab_grupa_b.model.domain.Country;
import com.example.emt_lab_grupa_b.repository.CountryRepository;
import com.example.emt_lab_grupa_b.servise.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }
}