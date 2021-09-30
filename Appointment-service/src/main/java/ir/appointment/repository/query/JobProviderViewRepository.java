package ir.appointment.repository.query;

import ir.appointment.domain.query.JobProviderView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JobProviderViewRepository extends MongoRepository<JobProviderView, String> {

    Optional<JobProviderView> findByJobProviderId(Long jobProviderId);

    Optional<List<JobProviderView>> findAllByAccountId(String accountId, Pageable pageable);
}
