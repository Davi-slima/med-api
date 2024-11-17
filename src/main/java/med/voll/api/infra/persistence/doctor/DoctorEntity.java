package med.voll.api.infra.persistence.doctor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.enums.Specialty;
import med.voll.api.infra.persistence.AddressEntity;

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
    private String phoneNumber;
    private String crm;
    private Specialty specialty;
    private  boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public DoctorEntity() {}

    public DoctorEntity(String name, String email, String phoneNumber, String crm, Specialty specialty, AddressEntity address, boolean active) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
        this.active = active;
    }
}
