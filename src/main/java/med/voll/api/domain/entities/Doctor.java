package med.voll.api.domain.entities;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.Address;
import med.voll.api.enums.Specialty;

@Getter
@Setter
public class Doctor {

    private String name;
    private String email;
    private String crm;
    private Specialty specialty;
    private Address address;

    public Doctor(String name, String email, String crm, Specialty specialty, Address address) {
        this.name = name;
        this.email = email;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
    }

}