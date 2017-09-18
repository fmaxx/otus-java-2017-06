package ru.otus.l131.orm.data;


import ru.otus.l131.orm.ORMConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = ORMConfig.Tables.ADDRESSES)
public class AddressDataSet extends DataSet{


    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    public AddressDataSet() {
    }

    public AddressDataSet(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "id='" + getId() + '\'' +
                "city='" + city + '\'' +
                "street='" + street + '\'' +
                '}';
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
