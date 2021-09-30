package ir.appointment.domain.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Document("appointment")
@Data
@NoArgsConstructor
public class AppointmentView {

    @Id
    private String id;
    private Long appointmentId;
    private String jobProviderId;
    private String jobProviderName;
    private String jobName;
    private String username;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String ownerId;
    private String ownerName;
}
