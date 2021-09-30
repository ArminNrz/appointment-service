package ir.appointment.repository.command;

import ir.appointment.domain.command.JobProvider;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface JobProviderRepository extends PagingAndSortingRepository<JobProvider, Long> {

    Optional<JobProvider> findByExternalId(String externalId);
}
