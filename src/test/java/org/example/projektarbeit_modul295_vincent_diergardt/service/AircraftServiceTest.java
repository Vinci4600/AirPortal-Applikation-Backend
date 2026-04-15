package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.AircraftDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.Aircraft;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.AircraftRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {
    private static final Long CREATE_AIRCRAFT_ID = 7L;
    private static final String CREATE_AIRCRAFT_MODEL = "Boeing 17";
    private static final String CREATE_AIRCRAFT_MANUFACTURE = "Boeing";
    private static final String CREATE_AIRCRAFT_GEWICHT = "29.7KG";
    private static final LocalDateTime CREATE_AIRCRAFT_DATE = LocalDateTime.now();

    @InjectMocks
    private AircraftService testee;

    @Mock
    private AircraftRepository aircraftRepository;


    private List<Aircraft> aircrafts = new ArrayList<>();

    @AfterEach
    void afterEight() {
        verifyNoMoreInteractions(
                aircraftRepository
        );
    }

    @Test
    void getAllAircrafts() {
        // arrange
        when(aircraftRepository.findAll()).thenReturn(aircrafts);
        // act
        List<AircraftDTO> actual = testee.getAllAircrafts();
        // assert
        assertEquals(0, actual.size());
        verify(aircraftRepository).findAll();
    }

    @Test
    void createAircraft() {
        // 1. Arrange
        AircraftDTO inputDTO = new AircraftDTO(
                CREATE_AIRCRAFT_ID,
                CREATE_AIRCRAFT_MODEL,
                CREATE_AIRCRAFT_MANUFACTURE,
                CREATE_AIRCRAFT_GEWICHT,
                CREATE_AIRCRAFT_DATE
        );

        Aircraft entity = new Aircraft();
        entity.setId(CREATE_AIRCRAFT_ID);
        entity.setModel(CREATE_AIRCRAFT_MODEL);
        entity.setManufacture(CREATE_AIRCRAFT_MANUFACTURE);
        entity.setGewicht(CREATE_AIRCRAFT_GEWICHT);
        entity.setCreateDate(CREATE_AIRCRAFT_DATE);

        Aircraft savedEntity = new Aircraft(); // Entity with an ID
        savedEntity.setId(CREATE_AIRCRAFT_ID);
        savedEntity.setModel(CREATE_AIRCRAFT_MODEL);
        savedEntity.setManufacture(CREATE_AIRCRAFT_MANUFACTURE);
        savedEntity.setGewicht(CREATE_AIRCRAFT_GEWICHT);
        savedEntity.setCreateDate(CREATE_AIRCRAFT_DATE);

        // Stub the repository to return our "saved" entity
        // Note: If convertToEntity creates a NEW instance, use any(Aircraft.class)
        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(savedEntity);

        // 2. Act
        AircraftDTO actual = testee.createAircraft(inputDTO);

        // 3. Assert
        assertNotNull(actual);
        assertEquals(inputDTO.id(), actual.id());
        assertEquals(inputDTO.model(),actual.model());
        assertEquals(inputDTO.manufacture(),actual.manufacture());
        assertEquals(inputDTO.gewicht(),actual.gewicht());
        assertEquals(inputDTO.createDate(),actual.createDate());
    }
}