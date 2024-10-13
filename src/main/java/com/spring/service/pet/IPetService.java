package com.spring.service.pet;

import com.spring.model.Pet;

import java.util.List;

public interface IPetService {
    List<Pet> savePetsForAppointment(List<Pet> pets);
    Pet updatePet(Pet pet, Long petId);
    void deletePet(Long petId);
    Pet getPetById(Long petId);
}
