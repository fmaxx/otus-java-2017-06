package ru.otus.l11.orm.data;


import ru.otus.l11.orm.ORMConfig;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = ORMConfig.Tables.USERS)
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneDataSet> phones;

    public UserDataSet() {

    }

    public UserDataSet(String name, int age, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "UserDataSet (id: " + getId() + ", name: " + getName() + ", age: " + getAge() +
                " address: " + address+ ", " +
                " phones: " + phones+ ")";
    }

    public AddressDataSet getAddress() {
        return null;
//        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }


    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }
}
