package ir.appointment.controller;

import ir.appointment.controller.dto.jobProvider.JobProviderCreateDTO;
import ir.appointment.controller.dto.jobProvider.JobProviderUpdateDTO;
import ir.appointment.controller.model.JobProviderModel;
import ir.appointment.domain.query.JobProviderView;
import ir.appointment.controller.dto.jobProvider.JobProviderDTO;
import ir.appointment.service.jobProvider.JobProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job-provider")
@Slf4j
@RequiredArgsConstructor
public class JobProviderController {

    private final JobProviderService jobProviderService;

    @PostMapping
    public ResponseEntity<JobProviderModel> create(@Valid @RequestBody JobProviderCreateDTO createDTO) {

        log.info("Request to create jobProvider, createDTO: {}", createDTO);

        JobProviderDTO dto = new JobProviderDTO(createDTO);
        JobProviderView view = jobProviderService.create(dto);

        JobProviderModel model = new JobProviderModel(view);

        log.info("create job provider: {}", model);

        return ResponseEntity.created(URI.create("job-provider")).body(model);
    }

    @PutMapping
    public ResponseEntity<JobProviderModel> update(@Valid @RequestBody JobProviderUpdateDTO updateDTO) {

        log.info("Request to update jobProvider, updateDTO: {}", updateDTO);

        JobProviderDTO dto = new JobProviderDTO(updateDTO);
        JobProviderView view = jobProviderService.update(dto);

        JobProviderModel model = new JobProviderModel(view);

        log.info("update job provider: {}", model);

        return ResponseEntity.ok(model);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<JobProviderModel>> getAll(@PathVariable("accountId") String accountId, @RequestParam("page") int page, @RequestParam("size") int size) {

        log.info("Request to getAll jobProvider, by accountId: {}", accountId);

        return ResponseEntity.ok(jobProviderService.findByAccountId(accountId, page, size));
    }
}
