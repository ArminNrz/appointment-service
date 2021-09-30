package ir.appointment.controller.dto.prepareJob;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PrepareJobCreateDTO {
    @NotEmpty
    private String jobId;
    @NotEmpty
    private String jobProviderId;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private Double duration;
    @NotEmpty
    private String creatorAccountId;
    @NotEmpty
    private String serviceOwnerAccountId;
}
