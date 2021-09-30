package ir.appointment.service.job;

import ir.appointment.controller.dto.job.JobDTO;
import ir.appointment.controller.model.JobModel;
import ir.appointment.domain.command.Job;
import ir.appointment.domain.command.JobOwner;
import ir.appointment.domain.query.JobView;
import ir.appointment.service.job.command.JobCommandHandler;
import ir.appointment.service.job.query.JobQueryHandler;
import ir.appointment.service.jobOwner.JobOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobCommandHandler jobCommandHandler;
    private final JobQueryHandler jobQueryHandler;
    private final JobOwnerService jobOwnerService;

    @Override
    public JobView create(JobDTO dto) {

        log.debug("create jobDTO: {}", dto);

        JobOwner jobOwner = jobOwnerService.find(dto.getAccountId());
        Job job = new Job(dto, jobOwner);

        return jobCommandHandler.save(job, dto.getAccountId());
    }

    @Override
    public JobView update(JobDTO dto) {

        log.debug("update, jobDTO: {}", dto);

        JobOwner jobOwner = jobOwnerService.find(dto.getAccountId());

        return jobCommandHandler.update(jobOwner, dto);
    }

    @Override
    public Job findByExternalId(String id) {

        log.debug("findByExternalId: {}", id);

        return jobQueryHandler.findByExternalId(id);
    }

    @Override
    public JobView find(String id) {

        log.debug("find, id: {}", id);

        return jobQueryHandler.findById(id);
    }

    @Override
    public List<JobModel> findByAccountId(String accountId, int page, int size) {

        log.debug("findJobByAccountId, accountId: {}, page: {}, size: {}", accountId, page, size);

        List<JobModel> returnValue = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        List<JobView> jobViews = jobQueryHandler.findByAccountId(accountId, pageable);

        jobViews.forEach(jobView -> {
            JobModel model = new JobModel(jobView);
            returnValue.add(model);
        });

        log.debug("foundModels: {}", returnValue);
        return returnValue;
    }
}
