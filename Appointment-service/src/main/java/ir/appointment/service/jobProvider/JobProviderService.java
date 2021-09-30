package ir.appointment.service.jobProvider;

import ir.appointment.controller.dto.jobProvider.JobProviderDTO;
import ir.appointment.controller.model.JobProviderModel;
import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.query.JobProviderView;

import java.util.List;

public interface JobProviderService {

    JobProviderView create(JobProviderDTO dto);

    JobProviderView update(JobProviderDTO dto);

    JobProvider findByExternalId(String externalId);

    JobProviderView find(String id);

    List<JobProviderModel> findByAccountId(String accountId, int page, int size);
}
