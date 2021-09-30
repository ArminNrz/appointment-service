package ir.appointment.service.appoitments.query;

import ir.appointment.domain.query.AppointmentView;
import ir.appointment.repository.query.AppointmentViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentQueryHandler {

    private final AppointmentViewRepository appointmentViewRepository;

    public List<AppointmentView> findByProviderAndDate(String jobProviderId, LocalDate date) {

        log.debug("Try to find appointments by jobProviderId: {}, in date: {}", jobProviderId, date);

        Optional<List<AppointmentView>> appointmentViewOptional = appointmentViewRepository.findAllByJobProviderIdAndDate(jobProviderId, date);
        if (appointmentViewOptional.isEmpty()) {
            log.debug("No appointment set for providerId: {}, in date: {}", jobProviderId, date);
            return null;
        }

        List<AppointmentView> appointmentViews = appointmentViewOptional.get();
        log.debug("appointments: {}, is set for provider: {}, in date: {}", appointmentViews, jobProviderId, date);
        return appointmentViews;
    }

    public List<AppointmentView> findAllByUsername(String username, LocalDate date) {

        log.debug("Try to find appointments by username: {}, in date: {}", username, date);

        Optional<List<AppointmentView>> appointmentViewsOptional = appointmentViewRepository.findAllByUsernameAndDate(username, date);
        if (appointmentViewsOptional.isEmpty()) {
            log.warn("No optional set for username: {}, in date: {}", username, date);
            throw Problem.valueOf(Status.NO_CONTENT, "No appointment set for this user");
        }

        List<AppointmentView> views = appointmentViewsOptional.get();
        log.debug("appointments: {}, is find for username: {}, in date: {}", views, username, date);
        return views;
    }

    public List<AppointmentView> findAllByAccountId(String accountId, LocalDate date) {

        log.debug("findAllByAccountId, accountId: {}, date: {}", accountId, date);

        Optional<List<AppointmentView>> appointmentViewsOptional = appointmentViewRepository.findAllByOwnerIdAndDate(accountId, date);
        return getAppointmentViews(accountId, date, appointmentViewsOptional);
    }

    public List<AppointmentView> findAllByAccountId(String accountId, LocalDate date, String providerId) {

        log.debug("findAllByAccountId, accountId: {}, date: {}, providerId: {}", accountId, date, providerId);

        Optional<List<AppointmentView>> appointmentViewsOptional = appointmentViewRepository.findAllByOwnerIdAndJobProviderIdAndDate(accountId, providerId, date);
        return getAppointmentViews(accountId, date, appointmentViewsOptional);
    }

    private List<AppointmentView> getAppointmentViews(String accountId, LocalDate date, Optional<List<AppointmentView>> appointmentViewsOptional) {
        if (appointmentViewsOptional.isEmpty()) {
            log.warn("No optional set for accountId: {}, in date: {}", accountId, date);
            throw Problem.valueOf(Status.NO_CONTENT, "No appointment set for this accountId");
        }

        List<AppointmentView> views = appointmentViewsOptional.get();
        log.debug("appointments: {}, is find for accountId: {}, in date: {}", views, accountId, date);
        return views;
    }
}
