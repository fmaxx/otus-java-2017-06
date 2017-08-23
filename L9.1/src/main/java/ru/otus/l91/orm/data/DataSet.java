package ru.otus.l91.orm.data;

import ru.otus.l91.orm.Config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Config.TABLE_NAME)
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
