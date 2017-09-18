package ru.otus.l131.orm.data;
import javax.persistence.*;

@MappedSuperclass
public abstract class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
