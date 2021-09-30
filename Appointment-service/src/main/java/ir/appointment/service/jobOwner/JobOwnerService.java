package ir.appointment.service.jobOwner;

import ir.appointment.controller.dto.jobOwner.JobOwnerDTO;
import ir.appointment.domain.command.JobOwner;

public interface JobOwnerService {

    JobOwner create(JobOwnerDTO dto);

    JobOwner update(JobOwnerDTO dto);

    JobOwner find(String accountId);
}
