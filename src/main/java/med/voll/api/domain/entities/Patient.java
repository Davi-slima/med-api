package med.voll.api.domain.entities;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.Address;

@Getter
@Setter
public class Patient {

    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private boolean active;
    private Address address;

    public Patient(String name, String email, String phoneNumber, String cpf, boolean active, Address address) {
        this.cpfValidate(cpf);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.active = active;
        this.address = address;
    }

    private void cpfValidate(String cpf) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new IllegalArgumentException("CPF fora do padrão!");
        }
    }

}
