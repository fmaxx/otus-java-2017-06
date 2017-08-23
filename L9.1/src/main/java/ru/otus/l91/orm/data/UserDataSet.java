package ru.otus.l91.orm.data;

import ru.otus.l91.orm.Config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Config.TABLE_NAME)
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    int age;

    public UserDataSet(String name, int age) {
        this.setId(-1);
        this.name = name;
        this.age = age;
    }

    // for load operation
    public UserDataSet(Long id, String name, Integer age) {
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

    @Override
    public String toString() {
        return "UserDataSet (id: " + getId() + ", name: " + getName() + ", age: " + getAge() + ")";
    }
}
