package ir.appointment.domain.command;

import ir.appointment.controller.dto.job.JobDTO;
import ir.appointment.domain.enumaration.JobStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "job", indexes = {@Index(name = "external_id_idx", columnList = "external_id")})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_owner_id")
    @ToString.Exclude
    private JobOwner jobOwner;

    @OneToMany(mappedBy = "job")
    @ToString.Exclude
    private Set<PrepareJob> prepareJobs;

    @Column(name = "external_id")
    private String externalId;

    public Job(JobDTO dto, JobOwner jobOwner) {
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.jobOwner = jobOwner;
        this.prepareJobs = Set.of();
        this.externalId = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Job job = (Job) o;

        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return 615373742;
    }
}
