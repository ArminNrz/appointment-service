package ir.appointment.controller;

import ir.appointment.controller.dto.jobReceiver.AppointmentRegisterDTO;
import ir.appointment.controller.dto.jobReceiver.JobReceiverCreateDTO;
import ir.appointment.controller.model.AppointmentModel;
import ir.appointment.service.appoitments.AppointmentService;
import ir.appointment.service.jobReceiver.JobReceiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-receiver")
public class JobReceiverController {

    private final JobReceiverService jobReceiverService;
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody JobReceiverCreateDTO dto) {

        log.info("Request to create job receiver, dto: {}", dto);

        jobReceiverService.create(dto);

        return ResponseEntity.created(URI.create("/job-receiver")).build();
    }

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentModel> register(@Valid @RequestBody AppointmentRegisterDTO dto) {

        log.info("Request to register appointment, dto: {}", dto);

        return ResponseEntity.ok(appointmentService.register(dto));
    }

    @GetMapping("/appointment")
    public ResponseEntity<List<AppointmentModel>> getAll(@RequestParam("username") String username, @RequestParam("date") String date) {

        log.info("Request to get all appointments for username: {}, in date: {}", username, date);

        return ResponseEntity.ok(appointmentService.getAllByUsername(username, LocalDate.parse(date)));
    }
}
