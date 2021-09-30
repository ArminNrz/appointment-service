package ir.appointment.controller.dto.jobProvider;

import ir.appointment.domain.enumaration.JobProviderStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JobProviderUpdateDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    private JobProviderStatus status;
    private String accountId;
}
