package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.repository;

import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFruitRepository extends JpaRepository<Fruit, Long> {
    List<Fruit> findByEatable(boolean eatable);

    List<Fruit> findByNameContaining(String name);
}