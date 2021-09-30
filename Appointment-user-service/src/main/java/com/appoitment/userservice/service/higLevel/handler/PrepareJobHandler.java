package com.appoitment.userservice.service.higLevel.handler;

import com.appoitment.userservice.controller.appointment.dto.CreatePrepareJobDTO;
import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.model.PrepareJobModel;
import com.appoitment.userservice.service.entity.UserService;
import com.appoitment.userservice.service.higLevel.data.UserContext;
import com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob.PrepareJobService;
import com.appoitment.userservice.service.thirdparty.appointmentService.prepareJob.dto.PrepareJobCreateDTO;
import com.appoitment.userservice.utility.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrepareJobHandler {

    private final PrepareJobService prepareJobService;
    private final UserService userService;
    private final Utility utility;

    public PrepareJobModel registerPrepareJob(CreatePrepareJobDTO dto, AppUser user) {

        log.debug("register prepareJob, dto: {}, user: {}", dto, user);

        UserContext userContext = utility.detectUserContext(user);
        PrepareJobCreateDTO prepareJobCreateDTO = new PrepareJobCreateDTO(dto, userContext.getCreatorAccountId(), userContext.getOwnerAccountId());

        return prepareJobService.create(prepareJobCreateDTO);
    }

    public List<PrepareJobModel> getAll(AppUser user, int page, int size) {

        log.debug("getAll, user: {}, page: {}, size: {}", user, page, size);

        UserContext userContext = utility.detectUserContext(user);

        return prepareJobService.getAll(userContext.getOwnerAccountId(), page, size);
    }
}
