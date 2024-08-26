package med.voll.api.infra.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.enums.Specialty;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private Specialty specialty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public DoctorEntity(String name, String email, String crm, Specialty specialty, AddressEntity address) {
        this.name = name;
        this.email = email;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
    }
}
