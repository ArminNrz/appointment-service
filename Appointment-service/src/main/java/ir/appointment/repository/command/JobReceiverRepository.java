package ir.appointment.repository.command;

import ir.appointment.domain.command.JobReceiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobReceiverRepository extends JpaRepository<JobReceiver, Long> {

    Optional<JobReceiver> findByUsername(String username);
}
