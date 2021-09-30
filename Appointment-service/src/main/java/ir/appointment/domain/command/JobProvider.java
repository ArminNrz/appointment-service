package ir.appointment.domain.command;

import ir.appointment.controller.dto.jobProvider.JobProviderDTO;
import ir.appointment.domain.enumaration.JobProviderStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "job_provider", indexes = {@Index(name = "external_id_idx", columnList = "external_id")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobProvider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private JobProviderStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_owner_id")
    @ToString.Exclude
    private JobOwner jobOwner;

    @OneToMany(mappedBy = "jobProvider")
    @ToString.Exclude
    private Set<PrepareJob> prepareJobs;

    @Column(name = "external_id")
    private String externalId;

    public JobProvider(JobProviderDTO dto, JobOwner jobOwner) {
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
        JobProvider that = (JobProvider) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1266027933;
    }
}
