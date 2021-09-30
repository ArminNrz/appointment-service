package ir.appointment.service.jobOwner;

import ir.appointment.controller.dto.jobOwner.JobOwnerDTO;
import ir.appointment.domain.command.JobOwner;
import ir.appointment.service.jobOwner.command.JobOwnerCommandHandler;
import ir.appointment.service.jobOwner.query.JobOwnerQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobOwnerServiceImpl implements JobOwnerService {

    private final JobOwnerCommandHandler jobOwnerCommandHandler;
    private final JobOwnerQueryHandler jobOwnerQueryHandler;

    @Override
    public JobOwner create(JobOwnerDTO dto) {

        log.debug("create, jobOwnerDTO: {}", dto);

        JobOwner jobOwner = new JobOwner(dto.getName(), dto.getAccountId());

        return jobOwnerCommandHandler.saveAndUpdate(jobOwner);
    }

    @Override
    public JobOwner update(JobOwnerDTO dto) {

        log.debug("update, jobOwnerDTO: {}", dto);

        JobOwner foundJobOwner = this.find(dto.getAccountId());
        foundJobOwner.setName(dto.getName());

        return jobOwnerCommandHandler.saveAndUpdate(foundJobOwner);
    }

    @Override
    public JobOwner find(String accountId) {

        log.debug("find, accountId: {}", accountId);

        return jobOwnerQueryHandler.findByAccountId(accountId);
    }
}
