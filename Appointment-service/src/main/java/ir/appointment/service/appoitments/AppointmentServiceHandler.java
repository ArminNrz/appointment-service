package ir.appointment.service.appoitments;

import ir.appointment.controller.dto.jobReceiver.AppointmentRegisterDTO;
import ir.appointment.controller.model.AppointmentModel;
import ir.appointment.domain.command.Appointment;
import ir.appointment.domain.command.JobReceiver;
import ir.appointment.domain.command.PrepareJob;
import ir.appointment.domain.query.AppointmentView;
import ir.appointment.domain.query.PrepareJobView;
import ir.appointment.service.appoitments.command.AppointmentCommandHandler;
import ir.appointment.service.appoitments.query.AppointmentQueryHandler;
import ir.appointment.service.jobReceiver.JobReceiverService;
import ir.appointment.service.prepareJob.PrepareJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentServiceHandler {

    private final PrepareJobService prepareJobService;
    private final JobReceiverService jobReceiverService;
    private final AppointmentCommandHandler commandHandler;
    private final AppointmentQueryHandler queryHandler;

    public AppointmentModel processRegister(AppointmentRegisterDTO dto) {

        //
        AppointmentModel returnValue;

        JobReceiver jobReceiver = jobReceiverService.find(dto.getJobReceiverUsername());

        /*
        get prepare job
        */
        PrepareJobView prepareJobView = prepareJobService.findViewById(dto.getPrepareJobId());
        Double duration  = prepareJobView.getDuration();
        LocalTime endTime = dto.getStartTime().plus(duration.intValue(), ChronoUnit.MINUTES);

        /*
        is appointment time in prepare job times
         */
        matchAppointmentTimes(dto, prepareJobView, endTime);

        /*
        get appointments
         */
        List<AppointmentView> appointmentViews = queryHandler.findByProviderAndDate(prepareJobView.getJobProviderId(), dto.getDate());

        if (appointmentViews == null || appointmentViews.size() == 0) {
            returnValue = save(dto, jobReceiver);
            return returnValue;
        }

        /*
        is appointment time has conflict with others
         */
        checkTimeConflict(dto, endTime, appointmentViews);

        return save(dto, jobReceiver);
    }

    private void matchAppointmentTimes(AppointmentRegisterDTO dto, PrepareJobView prepareJobView, LocalTime endTime) {
        boolean match = dto.getStartTime().isAfter(prepareJobView.getStartTime()) && endTime.isBefore(prepareJobView.getEndTime());
        if (!match) {
            log.warn("Appointment is not in prepare job time");
            throw Problem.valueOf(Status.BAD_REQUEST, "Appointment time is not in prepare job times");
        }
    }

    private void checkTimeConflict(AppointmentRegisterDTO dto, LocalTime endTime, List<AppointmentView> appointmentViews) {


        boolean conflict = appointmentViews.stream().anyMatch(appointmentView ->
                dto.getStartTime().isAfter(appointmentView.getStartTime()) &&
                        dto.getStartTime().isBefore(appointmentView.getEndTime()));

        if (conflict) {
            log.warn("Start time has conflict");
            throw Problem.valueOf(Status.BAD_REQUEST, "Start time has conflict");
        }

        conflict = appointmentViews.stream().anyMatch(appointmentView ->
                endTime.isAfter(appointmentView.getStartTime()) &&
                        endTime.isBefore(appointmentView.getEndTime())
        );

        if (conflict) {
            log.warn("End time has conflict");
            throw Problem.valueOf(Status.BAD_REQUEST, "End time has conflict");
        }
    }

    private AppointmentModel save(AppointmentRegisterDTO dto, JobReceiver jobReceiver) {

        log.debug("save, dto: {}, jobReceiver: {}", dto, jobReceiver);

        PrepareJob foundPrepareJob = prepareJobService.findByExternalId(dto.getPrepareJobId());

        Appointment appointment = new Appointment(jobReceiver, foundPrepareJob, dto.getStartTime(), dto.getStartTime().plus(foundPrepareJob.getDuration().longValue(), ChronoUnit.MINUTES));

        AppointmentView view = commandHandler.register(appointment);
        AppointmentModel model = new AppointmentModel(view);
        log.debug("model: {}", model);
        return model;
    }
}
