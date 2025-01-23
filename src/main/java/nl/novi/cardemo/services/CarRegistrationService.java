package nl.novi.cardemo.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.cardemo.dtos.CarRegistrationCreateDTO;
import nl.novi.cardemo.dtos.CarRegistrationResponseDTO;
import nl.novi.cardemo.dtos.CarRegistrationUpdateDTO;
import nl.novi.cardemo.models.Car;
import nl.novi.cardemo.models.CarRegistration;
import nl.novi.cardemo.repositories.CarRegistrationRepository;
import nl.novi.cardemo.repositories.CarRepository;
import nl.novi.cardemo.mappers.CarRegistrationMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRegistrationService {

    private final CarRegistrationRepository carRegistrationRepository;
    private final CarRepository carRepository;

    public CarRegistrationService(CarRegistrationRepository carRegistrationRepository, CarRepository carRepository) {
        this.carRegistrationRepository = carRegistrationRepository;
        this.carRepository = carRepository;
    }

    // Methode om een nieuwe CarRegistration aan te maken
    public CarRegistrationResponseDTO createCarRegistration(Long carId, CarRegistrationCreateDTO carRegistrationCreateDTO) {
        // Controleer of de Car bestaat
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + carId));

        // Zet DTO om naar entiteit en koppel de Car
        CarRegistration carRegistration = CarRegistrationMapper.toEntity(carRegistrationCreateDTO);
        carRegistration.setCar(car);

        // Sla de registratie op
        CarRegistration savedCarRegistration = carRegistrationRepository.save(carRegistration);

        // Zet de opgeslagen registratie om naar ResponseDTO
        return CarRegistrationMapper.toResponseDTO(savedCarRegistration);
    }

    // Methode om een bestaande CarRegistration bij te werken
    public CarRegistrationResponseDTO updateCarRegistration(Long carId, Long registrationId, CarRegistrationUpdateDTO carRegistrationUpdateDTO) {
        // Haal de bestaande registratie op
        CarRegistration carRegistration = carRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("CarRegistration not found with id " + registrationId));

        // Controleer of de Car bestaat
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + carId));

        // Update de registratie en koppel de juiste Car
        carRegistration.setPlateNumber(carRegistrationUpdateDTO.getPlateNumber());
        carRegistration.setRegistrationDate(carRegistrationUpdateDTO.getRegistrationDate());
        carRegistration.setCar(car);

        // Sla de bijgewerkte registratie op
        CarRegistration updatedCarRegistration = carRegistrationRepository.save(carRegistration);

        // Zet de registratie om naar ResponseDTO
        return CarRegistrationMapper.toResponseDTO(updatedCarRegistration);
    }

    // Methode om een CarRegistration op te halen
    public CarRegistrationResponseDTO getCarRegistration(Long carId, Long registrationId) {
        CarRegistration carRegistration = carRegistrationRepository.findByIdAndCarId( registrationId, carId)
                .orElseThrow(() -> new EntityNotFoundException("CarRegistration not found with id " + registrationId));

        return CarRegistrationMapper.toResponseDTO(carRegistration);
    }

    // Methode om een CarRegistration te verwijderen
    public boolean deleteCarRegistration(Long carId, Long registrationId) {
        if (carRegistrationRepository.existsById(registrationId)) {
            carRegistrationRepository.deleteById(registrationId);
            return true;
        }
        return false;
    }
}