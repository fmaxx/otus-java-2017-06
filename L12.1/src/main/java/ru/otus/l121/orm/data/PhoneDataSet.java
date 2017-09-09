package ru.otus.l121.orm.data;


import ru.otus.l121.orm.ORMConfig;

import javax.persistence.*;

@Entity
@Table(name = ORMConfig.Tables.PHONES)
public class PhoneDataSet extends DataSet{

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDataSet user;

    public PhoneDataSet() {

    }

    public PhoneDataSet(String number, UserDataSet user) {
        this.number = number;
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                "user='" + (user == null ? "null" : user.getName()) + '\'' +
                '}';
    }


    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }
}
