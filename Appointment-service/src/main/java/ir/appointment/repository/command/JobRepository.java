package ir.appointment.repository.command;

import ir.appointment.domain.command.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface JobRepository extends PagingAndSortingRepository<Job, Long> {

    Optional<Job> findByExternalId(String externalId);
}
