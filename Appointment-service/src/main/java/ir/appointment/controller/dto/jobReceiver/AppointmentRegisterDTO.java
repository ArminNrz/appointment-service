package ir.appointment.controller.dto.jobReceiver;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRegisterDTO {
    @NotEmpty
    private String jobReceiverUsername;
    @NotEmpty
    private String prepareJobId;

    private LocalDate date;
    private LocalTime startTime;
}
