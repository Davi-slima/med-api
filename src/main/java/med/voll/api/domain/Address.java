package med.voll.api.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String street;
    private String district;
    private String postalCode;
    private String city;
    private String uf;
    private String number;
    private String adjunct;

    public Address(String street, String district, String postalCode, String city, String uf, String number, String adjunct) {
        this.street = street;
        this.district = district;
        this.postalCode = postalCode;
        this.city = city;
        this.uf = uf;
        this.number = number;
        this.adjunct = adjunct;
    }

}
