package ir.appointment.repository.query;

import ir.appointment.domain.query.PrepareJobView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrepareJobViewRepository extends MongoRepository<PrepareJobView, String> {

    List<PrepareJobView> findAllByOwnerId(String ownerId, Pageable pageable);
}
