package nl.novi.cardemo.controllers;

import nl.novi.cardemo.dtos.CarRegistrationCreateDTO;
import nl.novi.cardemo.dtos.CarRegistrationResponseDTO;
import nl.novi.cardemo.dtos.CarRegistrationUpdateDTO;
import nl.novi.cardemo.services.CarRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars/{carId}/carregistrations")
public class CarRegistrationController {

    private final CarRegistrationService carRegistrationService;

    public CarRegistrationController(CarRegistrationService carRegistrationService) {
        this.carRegistrationService = carRegistrationService;
    }

    // Endpoint om een nieuwe CarRegistration aan te maken voor een specifieke Car
    @PostMapping
    public ResponseEntity<CarRegistrationResponseDTO> createCarRegistration(
            @PathVariable Long carId,
            @RequestBody CarRegistrationCreateDTO carRegistrationCreateDTO) {

        // Roep de service aan om de registratie aan te maken
        CarRegistrationResponseDTO responseDTO = carRegistrationService.createCarRegistration(carId, carRegistrationCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint om een specifieke CarRegistration op te halen
    @GetMapping("/{registrationId}")
    public ResponseEntity<CarRegistrationResponseDTO> getCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId) {

        // Roep de service aan om de registratie op te halen
        CarRegistrationResponseDTO responseDTO = carRegistrationService.getCarRegistration(carId, registrationId);

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint om een CarRegistration bij te werken, gekoppeld aan een specifieke Car
    @PutMapping("/{registrationId}")
    public ResponseEntity<CarRegistrationResponseDTO> updateCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId,
            @RequestBody CarRegistrationUpdateDTO carRegistrationUpdateDTO) {

        // Roep de service aan om de registratie bij te werken
        CarRegistrationResponseDTO responseDTO = carRegistrationService.updateCarRegistration(carId, registrationId, carRegistrationUpdateDTO);

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint om een CarRegistration te verwijderen
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId) {

        // Roep de service aan om de registratie te verwijderen
        boolean isDeleted = carRegistrationService.deleteCarRegistration(carId, registrationId);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Succesvol verwijderd
        } else {
            return ResponseEntity.notFound().build(); // Niet gevonden
        }
    }
}