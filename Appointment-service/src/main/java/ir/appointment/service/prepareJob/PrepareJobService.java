package ir.appointment.service.prepareJob;

import ir.appointment.controller.dto.prepareJob.PrepareJobDTO;
import ir.appointment.controller.model.PrepareJobModel;
import ir.appointment.domain.command.PrepareJob;
import ir.appointment.domain.query.PrepareJobView;

import java.util.List;

public interface PrepareJobService {

    PrepareJobView create(PrepareJobDTO dto);

    PrepareJobView update(PrepareJobDTO dto);

    List<PrepareJobModel> getAll(String accountId, int page, int size);

    PrepareJobView findViewById(String id);
    
    PrepareJob findByExternalId(String id);
}
