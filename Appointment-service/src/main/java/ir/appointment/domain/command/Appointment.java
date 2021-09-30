package ir.appointment.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "appointment", indexes = {@Index(name = "externalId_idx", columnList = "externalId")})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_receiver_id")
    @ToString.Exclude
    private JobReceiver jobReceiver;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "prepare_job_id")
    @ToString.Exclude
    private PrepareJob prepareJob;

    private LocalTime startTime;

    private LocalTime endTime;

    public Appointment(JobReceiver jobReceiver, PrepareJob prepareJob, LocalTime startTime, LocalTime endTime) {
        this.externalId = UUID.randomUUID().toString();
        this.jobReceiver = jobReceiver;
        this.prepareJob = prepareJob;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Appointment that = (Appointment) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 558524322;
    }
}
