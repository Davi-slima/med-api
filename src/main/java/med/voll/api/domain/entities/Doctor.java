package med.voll.api.domain.entities;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.Address;
import med.voll.api.enums.Specialty;

@Getter
@Setter
public class Doctor {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String crm;
    private Specialty specialty;
    private Address address;
    private boolean active;

    public Doctor(Long id, String name, String email, String phoneNumber, String crm, Specialty specialty, Address address, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
        this.active = active;
    }

}