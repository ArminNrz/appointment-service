package ir.appointment.controller.dto.jobProvider;

import ir.appointment.controller.dto.jobProvider.JobProviderCreateDTO;
import ir.appointment.controller.dto.jobProvider.JobProviderUpdateDTO;
import ir.appointment.domain.enumaration.JobProviderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobProviderDTO {
    private String id;
    private String name;
    private JobProviderStatus status;
    private String accountId;

    public JobProviderDTO(JobProviderCreateDTO createDTO) {
        this.name = createDTO.getName();
        this.status = createDTO.getStatus();
        this.accountId = createDTO.getAccountId();
    }

    public JobProviderDTO(JobProviderUpdateDTO updateDTO) {
        this.id = updateDTO.getId();
        this.name = updateDTO.getName();
        this.status = updateDTO.getStatus();
        this.accountId = updateDTO.getAccountId();
    }
}
