package ir.appointment.controller;

import ir.appointment.controller.dto.prepareJob.PrepareJobCreateDTO;
import ir.appointment.controller.dto.prepareJob.PrepareJobDTO;
import ir.appointment.controller.dto.prepareJob.PrepareJobUpdateDTO;
import ir.appointment.controller.model.PrepareJobModel;
import ir.appointment.domain.query.PrepareJobView;
import ir.appointment.service.prepareJob.PrepareJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prepare-job")
@Slf4j
@RequiredArgsConstructor
public class PrepareJobController {

    private final PrepareJobService prepareJobService;

    @PostMapping
    public ResponseEntity<PrepareJobModel> create(@Valid @RequestBody PrepareJobCreateDTO createDTO) {

        log.info("Request to create prepareJob, createDTO: {}", createDTO);

        PrepareJobDTO dto = new PrepareJobDTO(createDTO);
        PrepareJobView prepareJobView = prepareJobService.create(dto);

        PrepareJobModel model = new PrepareJobModel(prepareJobView);

        return ResponseEntity.created(URI.create("prepare_job")).body(model);
    }

    @PutMapping
    public ResponseEntity<PrepareJobModel> update(@Valid @RequestBody PrepareJobUpdateDTO updateDTO) {

        log.info("Request to update prepareJob, updateDTO: {}", updateDTO);

        PrepareJobDTO dto = new PrepareJobDTO(updateDTO);
        PrepareJobView prepareJobView = prepareJobService.update(dto);

        PrepareJobModel model = new PrepareJobModel(prepareJobView);

        return ResponseEntity.ok(model);

    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<PrepareJobModel>> getAll(@PathVariable("accountId") String accountId, @RequestParam("page") int page, @RequestParam("size") int size) {

        log.info("Request to getAll prepareJob of accountId: {}", accountId);

        List<PrepareJobModel> returnValue = prepareJobService.getAll(accountId, page, size);

        return ResponseEntity.ok(returnValue);
    }
}
