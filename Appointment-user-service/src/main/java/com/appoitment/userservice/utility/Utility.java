package com.appoitment.userservice.utility;

import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.service.entity.UserService;
import com.appoitment.userservice.service.higLevel.data.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Utility {

    private final UserService userService;

    public UserContext detectUserContext(AppUser user) {

        String creatorAccountId = user.getUsername();
        UserContext userContext = new UserContext();
        userContext.setCreatorAccountId(creatorAccountId);
        AppUser ownerUser;

        if (user.getSuperUsername() == null) {
            userContext.setOwnerAccountId(creatorAccountId);
        } else {
            ownerUser = userService.get(user.getSuperUsername());
            userContext.setOwnerAccountId(ownerUser.getUsername());
        }

        log.debug("UserContext: {}", userContext);
        return userContext;
    }
}
