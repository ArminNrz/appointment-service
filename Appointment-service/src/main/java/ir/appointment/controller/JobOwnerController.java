package ir.appointment.controller;

import ir.appointment.controller.dto.jobOwner.JobOwnerCreateDTO;
import ir.appointment.controller.dto.jobOwner.JobOwnerDTO;
import ir.appointment.controller.model.AppointmentModel;
import ir.appointment.service.appoitments.AppointmentService;
import ir.appointment.service.jobOwner.JobOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job-owner")
@Slf4j
@RequiredArgsConstructor
public class JobOwnerController {

    private final JobOwnerService jobOwnerService;
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody JobOwnerCreateDTO createDTO) {
        log.info("Request to create job owner, createJobOwnerDTO: {}", createDTO);
        JobOwnerDTO dto = new JobOwnerDTO(createDTO);

        jobOwnerService.create(dto);

        return ResponseEntity.created(URI.create("job-owner")).build();
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentModel>> getAppointments(@RequestParam("accountId") String accountId, @RequestParam("date") String date) {

        log.info("Request to get all jobOwner appointments of accountId: {}, in date: {}", accountId, date);

        return ResponseEntity.ok(appointmentService.getAllByAccountId(accountId, LocalDate.parse(date)));
    }

    @GetMapping("/appointments/{providerId}")
    public ResponseEntity<List<AppointmentModel>> getAppointments(@RequestParam("accountId") String accountId, @RequestParam("date") String date, @PathVariable("providerId") String providerId) {

        log.info("Request to get all jobOwner appointments of accountId: {}, in date: {}, for providerId: {}", accountId, date, providerId);

        return ResponseEntity.ok(appointmentService.getAllByProviderId(accountId, LocalDate.parse(date), providerId));
    }
}
