package ma.jee.luxedriveBackend.controller;

import ma.jee.luxedriveBackend.dto.request.RentalRequest;
import ma.jee.luxedriveBackend.dto.response.RentalResponse;
import ma.jee.luxedriveBackend.exception.EntityNotFoundException;
import ma.jee.luxedriveBackend.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalResponse>> getAllRentals() {
        try {
            List<RentalResponse> rentals = rentalService.getAllRentals();
            return ResponseEntity.ok(rentals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable Long rentalId) {
        try {
            RentalResponse rental = rentalService.getRentalById(rentalId);
            return ResponseEntity.ok(rental);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<RentalResponse> createRental(@RequestBody RentalRequest rentalRequest) {
        try {
            RentalResponse createdRental = rentalService.createRental(rentalRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<RentalResponse> updateRental(
            @PathVariable Long rentalId,
            @RequestBody RentalRequest rentalRequest) {
        try {
            RentalResponse updatedRental = rentalService.updateRental(rentalId, rentalRequest);
            return ResponseEntity.ok(updatedRental);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{rentalId}")
    public ResponseEntity<String> deleteRental(@PathVariable Long rentalId) {
        try {
            return ResponseEntity.ok(rentalService.deleteRental(rentalId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

