package de.hftstuttgart.vs.task03;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.hftstuttgart.vs.task03.openapi.api.PetApiDelegate;
import de.hftstuttgart.vs.task03.openapi.model.Pet;

@Service
public class PetApiDelegateImpl implements PetApiDelegate {

    @Override
    public ResponseEntity<Pet> getPetById(final Long petId) {
        final var pet = new Pet()
                .name("pet");

        return ResponseEntity.ok(pet);
    }

}
