package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "fruits")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "tree")
    private String tree;

    @Setter
    @Column(name = "eatable")
    private boolean eatable;


    public Fruit(String name, String tree, boolean eatable) {
        this.name = name;
        this.tree = tree;
        this.eatable = eatable;
    }

    public Fruit() {

    }

    @Override
    public String toString() {
        return "Fruit [id=" + id + ", name=" + name + ", tree=" + tree + ", eatable?=" + eatable + "]";
    }

}