package ir.appointment.controller.dto.job;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JobCreateDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String accountId;
}
