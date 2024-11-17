package med.voll.api.infra.persistence.patient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.infra.persistence.AddressEntity;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private  boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public PatientEntity() { }

    public PatientEntity(String name, String email, String phoneNumber, String cpf, AddressEntity address, boolean active) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.address = address;
        this.active = active;
    }

}
