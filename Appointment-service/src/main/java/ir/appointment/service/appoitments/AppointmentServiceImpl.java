package ir.appointment.service.appoitments;

import ir.appointment.controller.dto.jobReceiver.AppointmentRegisterDTO;
import ir.appointment.controller.model.AppointmentModel;
import ir.appointment.domain.query.AppointmentView;
import ir.appointment.service.appoitments.query.AppointmentQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentServiceHandler serviceHandler;
    private final AppointmentQueryHandler queryHandler;

    private final ReentrantLock mutex = new ReentrantLock();

    @Override
    public AppointmentModel register(AppointmentRegisterDTO dto) {

        log.debug("register, dto: {}", dto);

        try {
            mutex.lock();
            return serviceHandler.processRegister(dto);
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public List<AppointmentModel> getAllByUsername(String username, LocalDate date) {

        log.debug("getAll, getAllByUsername: {}, date: {}", username, date);

        List<AppointmentView> views = queryHandler.findAllByUsername(username, date);
        List<AppointmentModel> models = new ArrayList<>();

        views.forEach(view -> {
            AppointmentModel model = new AppointmentModel(view);
            models.add(model);
        });

        log.debug("Found appointments: {}, for username: {}", models, username);
        return models;
    }

    @Override
    public List<AppointmentModel> getAllByAccountId(String accountId, LocalDate date) {

        log.debug("getAllByAccountId, accountId: {}, date: {}", accountId, date);

        List<AppointmentView> views = queryHandler.findAllByAccountId(accountId, date);
        return getAppointmentModels(accountId, views);
    }

    @Override
    public List<AppointmentModel> getAllByProviderId(String accountId, LocalDate date, String providerId) {

        log.debug("getAllByProviderId, accountId: {}, date: {}, providerId: {}", accountId, date, providerId);

        List<AppointmentView> views = queryHandler.findAllByAccountId(accountId, date, providerId);
        return getAppointmentModels(accountId, views);
    }

    private List<AppointmentModel> getAppointmentModels(String accountId, List<AppointmentView> views) {
        List<AppointmentModel> models = new ArrayList<>();

        views.forEach(view -> {
            AppointmentModel model = new AppointmentModel(view);
            models.add(model);
        });

        log.debug("Found appointments: {}, for accountId: {}", models, accountId);
        return models;
    }


}
