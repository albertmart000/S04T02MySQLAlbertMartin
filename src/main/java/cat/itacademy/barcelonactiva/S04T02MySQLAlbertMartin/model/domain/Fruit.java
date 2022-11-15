package cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "fruit")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String name;
    @Column(name = "quilos")
    private double numberOfKilos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumberOfKilos() {
        return numberOfKilos;
    }

    public void setNumberOfKilos(double numberOfKilos) {
        this.numberOfKilos = numberOfKilos;
    }
}
