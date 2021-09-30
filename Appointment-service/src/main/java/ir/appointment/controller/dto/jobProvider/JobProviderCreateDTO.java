package ir.appointment.controller.dto.jobProvider;

import ir.appointment.domain.enumaration.JobProviderStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JobProviderCreateDTO {
    @NotEmpty
    private String name;
    private JobProviderStatus status;
    @NotEmpty
    private String accountId;
}
