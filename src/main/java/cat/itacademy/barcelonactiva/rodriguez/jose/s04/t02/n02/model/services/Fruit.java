package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.services;

import jakarta.persistence.*;

@Entity
@Table(name = "fruits")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tree")
    private String tree;

    @Column(name= "eatable")
    private boolean eatable;

    public Fruit() {

    }

    public Fruit(String title, String tree, boolean eatable) {
        this.name = title;
        this.tree = tree;
        this.eatable = eatable;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String description) {
        this.tree = description;
    }

    public boolean isEatable() {
        return eatable;
    }

    public void setEatable(boolean isEatable) {
        this.eatable = isEatable;
    }

    @Override
    public String toString() {
        return "Fruit [id=" + id + ", title=" + name + ", desc=" + tree + ", eatable?=" + eatable + "]";
    }

}