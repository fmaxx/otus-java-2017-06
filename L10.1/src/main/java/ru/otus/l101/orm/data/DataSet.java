package ru.otus.l101.orm.data;

import ru.otus.l101.orm.ORMConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = ORMConfig.Tables.USERS)
public abstract class DataSet {

    @Id
    @Column(name = "id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
