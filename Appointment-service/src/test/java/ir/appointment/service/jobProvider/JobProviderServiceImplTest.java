package ir.appointment.service.jobProvider;

import ir.appointment.domain.enumaration.JobProviderStatus;
import ir.appointment.domain.query.JobProviderView;
import ir.appointment.controller.dto.jobProvider.JobProviderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobProviderServiceImplTest {

    @Autowired
    JobProviderService jobProviderService;

    @Test
    void createAndUpdateTest() {

        JobProviderDTO dto = new JobProviderDTO();
        dto.setName("provider1");
        dto.setStatus(JobProviderStatus.ACTIVE);
        dto.setAccountId("8175314c-f0f1-47cc-ab8e-ebf668e6354b");

        JobProviderView actualJobProvider =  jobProviderService.create(dto);
        Assertions.assertNotNull(actualJobProvider);
        Assertions.assertNotNull(actualJobProvider.getId());
    }
}