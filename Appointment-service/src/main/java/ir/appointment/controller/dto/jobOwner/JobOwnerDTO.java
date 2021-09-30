package ir.appointment.controller.dto.jobOwner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOwnerDTO {

    private String name;
    private String accountId;

    public JobOwnerDTO(JobOwnerCreateDTO createDTO) {
        this.name = createDTO.getName();
        this.accountId = createDTO.getAccountId();
    }
}
