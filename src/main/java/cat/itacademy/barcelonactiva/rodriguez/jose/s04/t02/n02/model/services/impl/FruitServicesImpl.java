package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.services.impl;


import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.entity.Fruit;
import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.exceptions.FruitAlreadyExistException;
import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.exceptions.FruitNotFoundException;
import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.repository.FruitRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.services.FruitServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServicesImpl implements FruitServices {

    private final FruitRepository fruitRepository;

    public FruitServicesImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public List<Fruit> getAllFruits() {
        return fruitRepository.findAllByOrderById();
    }

    @Override
    public Fruit getFruitById(Long id) {
        return fruitRepository.findById(id).orElseThrow(() -> new FruitNotFoundException("Fruit Not Found with ID: " + id));
    }

    @Override
    public List<Fruit> getFruitContaining(String name) {
        return fruitRepository.findByNameContainingIgnoreCaseOrderById(name);
    }

    @Override
    public Fruit createFruit(Fruit fruit) {
        fruitRepository.findByNameIgnoreCase(fruit.getName())
                .ifPresent(fruits -> {
                    throw new FruitAlreadyExistException("Already exist fruit with given name:" + fruit.getName());
                });
        return fruitRepository.save(fruit);
    }

    @Override
    public Fruit updateFruit(Long id, Fruit fruit) {
        if (id == null) {
            throw new IllegalArgumentException("Fruit ID cannot be null");
        }
        Fruit existingFruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException("Fruit Not Found with ID: " + id));
        existingFruit.setName(fruit.getName());
        existingFruit.setTree(fruit.getTree());
        existingFruit.setEatable(fruit.isEatable());
        return fruitRepository.save(existingFruit);
    }

    @Override
    public void deleteFruitById(Long id) {
        Fruit existingFruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException("Fruit Not Found with ID: " + id));
        fruitRepository.deleteById(existingFruit.getId());

    }
}
