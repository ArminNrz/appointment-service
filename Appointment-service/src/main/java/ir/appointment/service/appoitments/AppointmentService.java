package ir.appointment.service.appoitments;

import ir.appointment.controller.dto.jobReceiver.AppointmentRegisterDTO;
import ir.appointment.controller.model.AppointmentModel;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    AppointmentModel register(AppointmentRegisterDTO dto);

    List<AppointmentModel> getAllByUsername(String username, LocalDate date);

    List<AppointmentModel> getAllByAccountId(String accountId, LocalDate date);

    List<AppointmentModel> getAllByProviderId(String accountId, LocalDate date, String providerId);
}
