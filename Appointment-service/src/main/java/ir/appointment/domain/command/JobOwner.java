package ir.appointment.domain.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "job_owner", indexes = {@Index(name = "account_idx", columnList = "account_id")})
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JobOwner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "account_id", unique = true)
    private String accountId;

    @OneToMany(mappedBy = "jobOwner")
    @ToString.Exclude
    private Set<JobProvider> jobProviderIds;

    @OneToMany(mappedBy = "jobOwner")
    @ToString.Exclude
    private Set<Job> jobs;

    public JobOwner(String name, String accountId) {
        this.name = name;
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobOwner jobOwner = (JobOwner) o;

        return Objects.equals(id, jobOwner.id);
    }

    @Override
    public int hashCode() {
        return 1530826636;
    }
}
