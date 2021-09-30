package ir.appointment.repository.query;

import ir.appointment.domain.query.AppointmentView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentViewRepository extends MongoRepository<AppointmentView, String> {

    Optional<List<AppointmentView>> findAllByJobProviderIdAndDate(String jobProviderId, LocalDate date);
    Optional<List<AppointmentView>> findAllByUsernameAndDate(String username, LocalDate date);
    Optional<List<AppointmentView>> findAllByOwnerIdAndDate(String ownerId, LocalDate date);
    Optional<List<AppointmentView>> findAllByOwnerIdAndJobProviderIdAndDate(String ownerId, String providerId, LocalDate date);
}
