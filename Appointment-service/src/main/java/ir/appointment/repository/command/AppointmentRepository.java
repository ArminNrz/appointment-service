package ir.appointment.repository.command;

import ir.appointment.domain.command.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
