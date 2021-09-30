package ir.appointment.repository.query;

import ir.appointment.domain.query.JobView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JobViewRepository extends MongoRepository<JobView, String> {

    Optional<JobView> findByJobId(Long jobId);

    Optional<List<JobView>> findAllByAccountId(String accountId, Pageable pageable);
}
