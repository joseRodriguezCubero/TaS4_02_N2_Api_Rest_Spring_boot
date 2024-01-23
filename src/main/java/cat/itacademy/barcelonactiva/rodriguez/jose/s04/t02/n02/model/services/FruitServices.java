package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.services;

import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.entity.Fruit;
import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.exceptions.FruitNotFoundException;

import java.util.List;

public interface FruitServices {
    List<Fruit> getAllFruits();

    Fruit getFruitById(Long id);

    List<Fruit> getFruitContaining(String name);

    Fruit createFruit(Fruit fruitDto);

    Fruit updateFruit(Long id, Fruit updatedFruit);

    void deleteFruitById(Long id) throws FruitNotFoundException;
}

