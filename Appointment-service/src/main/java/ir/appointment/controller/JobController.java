package ir.appointment.controller;

import ir.appointment.controller.dto.job.JobCreateDTO;
import ir.appointment.controller.dto.job.JobDTO;
import ir.appointment.controller.dto.job.JobUpdateDTO;
import ir.appointment.controller.model.JobModel;
import ir.appointment.domain.query.JobView;
import ir.appointment.service.job.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@Slf4j
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobModel> create(@Valid @RequestBody JobCreateDTO createDTO) {

        log.info("Request to create job: {}", createDTO);

        JobDTO dto = new JobDTO(createDTO);
        JobView view = jobService.create(dto);

        JobModel model = new JobModel(view);

        return ResponseEntity.created(URI.create("Job")).body(model);
    }

    @PutMapping
    public ResponseEntity<JobModel> update(@Valid @RequestBody JobUpdateDTO updateDTO) {

        log.info("Request to update job: {}", updateDTO);

        JobDTO dto = new JobDTO(updateDTO);
        JobView view = jobService.update(dto);

        JobModel model = new JobModel(view);

        return ResponseEntity.ok(model);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<JobModel>> getAll(@PathVariable("accountId") String accountId, @RequestParam("page") int page, @RequestParam("size") int size) {

        log.info("Request to getAll job, by accountId: {}", accountId);

        return ResponseEntity.ok(jobService.findByAccountId(accountId, page, size));
    }
}
