package ir.appointment.domain.command;

import ir.appointment.controller.dto.prepareJob.PrepareJobDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "prepare_job", indexes = {@Index(name = "external_id_idx", columnList = "external_id")})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PrepareJob implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    @ToString.Exclude
    private Job job;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "job_provider_id")
    @ToString.Exclude
    private JobProvider jobProvider;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Double duration;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String creatorAccountId;

    @Column(name = "external_id")
    private String externalId;

    @OneToMany(mappedBy = "prepareJob")
    @ToString.Exclude
    private Set<Appointment> appointments;

    public PrepareJob(Job job, JobProvider jobProvider, PrepareJobDTO prepareJobDTO) {
        this.job = job;
        this.jobProvider = jobProvider;
        this.date = prepareJobDTO.getDate();
        this.startTime = prepareJobDTO.getStartTime();
        this.endTime = prepareJobDTO.getEndTime();
        this.duration = prepareJobDTO.getDuration();
        this.accountId = prepareJobDTO.getServiceOwnerAccountId();
        this.creatorAccountId = prepareJobDTO.getCreatorAccountId();
        this.externalId = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PrepareJob that = (PrepareJob) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1452984714;
    }
}
