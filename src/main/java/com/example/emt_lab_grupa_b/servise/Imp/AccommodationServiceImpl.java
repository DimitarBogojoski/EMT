package com.example.emt_lab_grupa_b.servise.Imp;

import com.example.emt_lab_grupa_b.enums.AccommodationCondition;
import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.model.domain.Host;
import com.example.emt_lab_grupa_b.model.dto.AccommodationDto;
import com.example.emt_lab_grupa_b.repository.AccommodationRepository;
import com.example.emt_lab_grupa_b.repository.HostRepository;
import com.example.emt_lab_grupa_b.servise.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodationDto) {
        Host host = hostRepository.findById(accommodationDto.getHostId()).orElse(null);

        if (host == null) {
            return Optional.empty();
        }

        Accommodation accommodation = new Accommodation();
        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setCondition(AccommodationCondition.GOOD);

        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodationDto) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);
        Host host = hostRepository.findById(accommodationDto.getHostId()).orElse(null);

        if (optionalAccommodation.isEmpty() || host == null) {
            return Optional.empty();
        }

        Accommodation accommodation = optionalAccommodation.get();
        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());

        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);

        if (optionalAccommodation.isEmpty()) {
            return Optional.empty();
        }

        Accommodation accommodation = optionalAccommodation.get();
        accommodation.setCondition(AccommodationCondition.BAD);

        return Optional.of(accommodationRepository.save(accommodation));
    }
}