package ir.appointment.repository.command;

import ir.appointment.domain.command.JobOwner;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface JobOwnerRepository extends PagingAndSortingRepository<JobOwner, Long> {

    Optional<JobOwner> findByAccountId(String accountId);
}
