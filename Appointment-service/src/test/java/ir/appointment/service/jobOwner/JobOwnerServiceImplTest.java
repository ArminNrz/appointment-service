package ir.appointment.service.jobOwner;

import ir.appointment.domain.command.JobOwner;
import ir.appointment.controller.dto.jobOwner.JobOwnerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class JobOwnerServiceImplTest {

    @Autowired
    private JobOwnerService jobOwnerService;

    @Test
    void createAndUpdateTest() {

        JobOwnerDTO dto = new JobOwnerDTO();
        dto.setName("owner1");
        dto.setAccountId(UUID.randomUUID().toString());

        JobOwner actualSavedOwner = jobOwnerService.create(dto);
        Assertions.assertNotNull(actualSavedOwner);
        Assertions.assertEquals("owner1", actualSavedOwner.getName());
        Assertions.assertNotNull(actualSavedOwner.getId());

        dto.setName("Owner-name-1");
        dto.setAccountId(actualSavedOwner.getAccountId());

        JobOwner actualUpdatedOwner = jobOwnerService.update(dto);
        Assertions.assertNotNull(actualUpdatedOwner);
        Assertions.assertEquals("Owner-name-1", actualUpdatedOwner.getName());
        Assertions.assertNotNull(actualUpdatedOwner.getId());
    }
}