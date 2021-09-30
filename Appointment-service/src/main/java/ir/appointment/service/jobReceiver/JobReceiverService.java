package ir.appointment.service.jobReceiver;

import ir.appointment.controller.dto.jobReceiver.JobReceiverCreateDTO;
import ir.appointment.domain.command.JobReceiver;

public interface JobReceiverService {

    void create(JobReceiverCreateDTO dto);

    JobReceiver find(String username);
}
