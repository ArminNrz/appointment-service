package ir.appointment.service.appoitments.command;

import ir.appointment.domain.command.Appointment;
import ir.appointment.domain.command.Job;
import ir.appointment.domain.command.JobProvider;
import ir.appointment.domain.query.AppointmentView;
import ir.appointment.repository.command.AppointmentRepository;
import ir.appointment.repository.query.AppointmentViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentCommandHandler {

    private final AppointmentViewRepository appointmentViewRepository;
    private final AppointmentRepository appointmentRepository;

    public AppointmentView register(Appointment appointment) {

        log.debug("register, appointment: {}", appointment);

        appointmentRepository.save(appointment);
        log.info("Saved appointment: {}, in command DB", appointment);

        JobProvider jobProvider = appointment.getPrepareJob().getJobProvider();
        Job job = appointment.getPrepareJob().getJob();

        AppointmentView view = new AppointmentView();
        view.setId(appointment.getExternalId());
        view.setAppointmentId(appointment.getId());
        view.setJobProviderId(jobProvider.getExternalId());
        view.setJobProviderName(jobProvider.getName());
        view.setJobName(job.getName());
        view.setUsername(appointment.getJobReceiver().getUsername());
        view.setDate(appointment.getPrepareJob().getDate());
        view.setStartTime(appointment.getStartTime());
        view.setEndTime(appointment.getEndTime());
        view.setOwnerId(jobProvider.getJobOwner().getAccountId());
        view.setOwnerName(jobProvider.getJobOwner().getName());

        appointmentViewRepository.save(view);
        log.info("Saved appointmentView: {}, in query DB", view);
        return view;
    }
}
