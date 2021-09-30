package ir.appointment.controller.dto.jobOwner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOwnerCreateDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String accountId;
}
