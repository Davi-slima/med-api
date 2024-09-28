package med.voll.api.infra.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String district;
    private String postalCode;
    private String city;
    private String uf;
    private String number;
    private String adjunct;

    public AddressEntity() {}

    public AddressEntity(String street, String district, String postalCode, String city, String uf, String number, String adjunct) {
        this.street = street;
        this.district = district;
        this.postalCode = postalCode;
        this.city = city;
        this.uf = uf;
        this.number = number;
        this.adjunct = adjunct;
    }

}
