package com.spring.controller;

import com.spring.exception.ResourceNotFoundException;
import com.spring.model.Appointment;
import com.spring.request.AppointmentUpdateRequest;
import com.spring.request.BookAppointmentRequest;
import com.spring.response.ApiResponse;
import com.spring.service.appointment.AppointmentService;
import com.spring.utils.FeedBackMessage;
import com.spring.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlMapping.APPOINTMENTS)
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping(UrlMapping.ALL_APPOINTMENT)
    public ResponseEntity<ApiResponse> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.status(FOUND).body(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, appointments));

        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping(UrlMapping.BOOK_APPOINTMENT)
    public ResponseEntity<ApiResponse> bookAppointment(
            @RequestBody BookAppointmentRequest request,
            @RequestParam Long senderId,
            @RequestParam Long recipientId) {
        try {
            Appointment theAppointment = appointmentService.createAppointment(request, senderId, recipientId);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.CREATE_SUCCESS, theAppointment));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping(UrlMapping.GET_APPOINTMENT_BY_ID)
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.status(FOUND).body(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, appointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_APPOINTMENT_BY_NO)
    public ResponseEntity<ApiResponse> getAppointmentByNo(@PathVariable String appointmentNo) {
        try {
            Appointment appointment = appointmentService.getAppointmentByNo(appointmentNo);
            return ResponseEntity.status(FOUND).body(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, appointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping(UrlMapping.DELETE_APPOINTMENT)
    public ResponseEntity<ApiResponse> deleteAppointmentById(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping(UrlMapping.UPDATE_APPOINTMENT)
    public ResponseEntity<ApiResponse> updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentUpdateRequest request) {
        try {
            Appointment appointment = appointmentService.updateAppointment(id, request);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, appointment));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(NOT_ACCEPTABLE).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
