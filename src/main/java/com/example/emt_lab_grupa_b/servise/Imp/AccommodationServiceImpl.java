package com.example.emt_lab_grupa_b.servise.Imp;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import com.example.emt_lab_grupa_b.enums.AccommodationCondition;
import com.example.emt_lab_grupa_b.listener.AccommodationRentedEvent;
import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.model.domain.Host;
import com.example.emt_lab_grupa_b.model.dto.AccommodationCreateUpdateDto;
import com.example.emt_lab_grupa_b.model.dto.AccommodationDetailsDto;
import com.example.emt_lab_grupa_b.model.dto.AccommodationShortDto;
import com.example.emt_lab_grupa_b.repository.AccommodationRepository;
import com.example.emt_lab_grupa_b.repository.HostRepository;
import com.example.emt_lab_grupa_b.repository.specification.AccommodationSpecification;
import com.example.emt_lab_grupa_b.servise.AccommodationService;
import com.example.emt_lab_grupa_b.servise.filter.AccommodationFilter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    HostRepository hostRepository, ApplicationEventPublisher eventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<AccommodationShortDto> findAll() {
        return accommodationRepository.findAll()
                .stream()
                .map(this::toShortDto)
                .toList();
    }

    @Override
    public Optional<AccommodationDetailsDto> findById(Long id) {
        return accommodationRepository.findWithHostAndCountryById(id)
                .map(this::toDetailsDto);
    }

    @Override
    public Optional<AccommodationDetailsDto> save(AccommodationCreateUpdateDto accommodationDto) {
        Host host = hostRepository.findById(accommodationDto.getHostId()).orElse(null);

        if (host == null) {
            return Optional.empty();
        }

        Accommodation accommodation = new Accommodation();
        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setCondition(accommodationDto.getNumRooms() > 0
                ? AccommodationCondition.GOOD
                : AccommodationCondition.BAD);

        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        return Optional.of(toDetailsDto(savedAccommodation));
    }

    @Override
    public Optional<AccommodationDetailsDto> update(Long id, AccommodationCreateUpdateDto accommodationDto) {
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
        accommodation.setCondition(accommodationDto.getNumRooms() > 0
                ? AccommodationCondition.GOOD
                : AccommodationCondition.BAD);

        Accommodation updatedAccommodation = accommodationRepository.save(accommodation);
        return Optional.of(toDetailsDto(updatedAccommodation));
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<AccommodationDetailsDto> markAsRented(Long id) {
        Optional<Accommodation> optionalAccommodation = accommodationRepository.findById(id);

        if (optionalAccommodation.isEmpty()) {
            return Optional.empty();
        }

        Accommodation accommodation = optionalAccommodation.get();

        if (accommodation.getNumRooms() <= 0) {
            return Optional.empty();
        }

        accommodation.setNumRooms(accommodation.getNumRooms() - 1);

        if (accommodation.getNumRooms() == 0) {
            accommodation.setCondition(AccommodationCondition.BAD);
        } else {
            accommodation.setCondition(AccommodationCondition.GOOD);
        }

        Accommodation savedAccommodation = accommodationRepository.save(accommodation);

        eventPublisher.publishEvent(new AccommodationRentedEvent(
                savedAccommodation.getId(),
                savedAccommodation.getName(),
                savedAccommodation.getNumRooms(),
                java.time.LocalDateTime.now()
        ));

        return Optional.of(toDetailsDto(savedAccommodation));
    }

    private AccommodationShortDto toShortDto(Accommodation accommodation) {
        return new AccommodationShortDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getNumRooms()
        );
    }

    private AccommodationDetailsDto toDetailsDto(Accommodation accommodation) {
        return new AccommodationDetailsDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getNumRooms(),
                accommodation.getHost().getName() + " " + accommodation.getHost().getSurname(),
                accommodation.getHost().getCountry().getName()
        );
    }

    @Override
    public Page<AccommodationShortDto> findAllPaged(
            AccommodationCategory category,
            Long hostId,
            String hostCountry,
            Integer minRooms,
            Boolean availableRooms,
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        if (!sortBy.equals("name") && !sortBy.equals("createdAt")) {
            sortBy = "name";
        }

        Sort sort = Sort.by(
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC,
                sortBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        AccommodationFilter filter = new AccommodationFilter(
                category,
                hostId,
                hostCountry,
                minRooms,
                availableRooms
        );

        return accommodationRepository.findAll(
                AccommodationSpecification.filter(filter),
                pageable
        ).map(this::toShortDto);
    }
}