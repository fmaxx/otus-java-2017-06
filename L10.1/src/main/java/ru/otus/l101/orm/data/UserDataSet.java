package ru.otus.l101.orm.data;

import ru.otus.l101.orm.ORMConfig;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = ORMConfig.Tables.USERS)
public class UserDataSet {

    @Id
    @Column(name = "id")
    private long id;



    @Column(name = "name")
    private String name;

    @Column(name = "age")
    int age;

//    @OneToOne(cascade = CascadeType.ALL)
//    private AddressDataSet address;

   /* @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneDataSet> phones;*/

    public UserDataSet() {
    }

    public UserDataSet(String name, int age, List<PhoneDataSet> phones) {
//        this.phones = phones;
        this.setId(-1);
        this.name = name;
        this.age = age;
    }

    // for load operation
    public UserDataSet(Long id, String name, Integer age, List<PhoneDataSet> phones) {
//        this.phones = phones;
        this.setId(id);
        this.name = name;
        this.age = age;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet (id: " + getId() + ", name: " + getName() + ", age: " + getAge() + ")";
    }

   /* public AddressDataSet getAddress() {
        return null;
//        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }*/

//    public List<PhoneDataSet> getPhones() {
//        return phones;
//    }
}
