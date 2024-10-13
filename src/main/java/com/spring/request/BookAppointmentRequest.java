package com.spring.request;

import com.spring.model.Appointment;
import com.spring.model.Pet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookAppointmentRequest {
    private Appointment appointment;
    private List<Pet> pets;
}
