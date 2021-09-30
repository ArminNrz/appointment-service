package ir.appointment.repository.command;

import ir.appointment.domain.command.PrepareJob;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PrepareJobRepository extends PagingAndSortingRepository<PrepareJob, Long> {

    Optional<PrepareJob> findByExternalId(String externalId);
}
