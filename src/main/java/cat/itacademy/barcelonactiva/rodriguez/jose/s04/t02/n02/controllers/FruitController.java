package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.controllers;

//TODO  http://localhost:8080/fruita/add
//TODO  http://localhost:8080/fruita/update
//TODO  http://localhost:8080/fruita/delete/{id}
//TODO  http://localhost:8080/fruita/getOne/{id}
//TODO  http://localhost:8080/fruita/getAll*/

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.repository.IFruitRepository;
import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3306")
@RestController
@RequestMapping("/api/v1")
public class FruitController {

    @Autowired
    IFruitRepository iFruitRepository;

    @GetMapping("/fruita/getAll*/")
    public ResponseEntity<List<Fruit>> getAllFruits(@RequestParam(required = false) String title) {
        try {
            List<Fruit> fruits = new ArrayList<>();

            if (title == null)
                fruits.addAll(iFruitRepository.findAll());
            else
                fruits.addAll(iFruitRepository.findByNameContaining(title));

            if (fruits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(fruits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fruita/getOne/{id}")
    public ResponseEntity<Fruit> getTutorialById(@PathVariable("id") long id) {
        Optional<Fruit> fruitData = iFruitRepository.findById(id);

        return fruitData.map(fruit -> new ResponseEntity<>(fruit, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/fruita/add")
    public ResponseEntity<Fruit> createFruit(@RequestBody Fruit fruit) {
        try {
            Fruit _fruit = iFruitRepository
                    .save(new Fruit(fruit.getName(), fruit.getTree(), false));
            return new ResponseEntity<>(_fruit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/fruita/update/{id}")
    public ResponseEntity<Fruit> updateFruits(@PathVariable("id") long id, @RequestBody Fruit fruit) {
        Optional<Fruit> fruitData = iFruitRepository.findById(id);

        if (fruitData.isPresent()) {
            Fruit _fruit = fruitData.get();
            _fruit.setName(fruit.getName());
            _fruit.setTree(fruit.getTree());
            _fruit.setEatable(fruit.isEatable());
            return new ResponseEntity<>(iFruitRepository.save(_fruit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/fruita/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFruit(@PathVariable("id") long id) {
        try {
            iFruitRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/fruites/delete/")
    public ResponseEntity<HttpStatus> deleteAllFruits() {
        try {
            iFruitRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/fruites/eatable")
    public ResponseEntity<List<Fruit>> findByEatable() {
        try {
            List<Fruit> fruits = iFruitRepository.findByEatable(true);

            if (fruits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fruits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}