package cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.controllers;


import java.util.List;


import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.entity.Fruit;
import cat.itacademy.barcelonactiva.rodriguez.jose.s04.t02.n02.model.services.FruitServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FruitController {

    private final FruitServices fruitServices;


    @GetMapping(path = "/fruita/getAll*")
    public ResponseEntity<List<Fruit>> getAllFruits() {
        return ResponseEntity.ok().body(fruitServices.getAllFruits());
    }


    @GetMapping(path = "/fruita/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable Long id) {
        return ResponseEntity.ok().body(fruitServices.getFruitById(id));
    }


    @GetMapping(path = "/fruita/getOne/{name}")
    public ResponseEntity<List<Fruit>> getFruitsByNameContaining(@PathVariable String name) {
        return ResponseEntity.ok().body(fruitServices.getFruitContaining(name));
    }


    @PostMapping(path = "/fruita/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fruit> saveFruit(@RequestBody Fruit fruitDto){
        Fruit newFruit = fruitServices.createFruit(fruitDto);
        return new ResponseEntity<>(newFruit, HttpStatus.CREATED);
    }


    @PutMapping(path = "/fruita/update{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable(value = "id") Long id,
                                             @RequestBody Fruit fruitDto) {
        return ResponseEntity.ok().body(fruitServices.updateFruit(id,fruitDto));
    }


    @DeleteMapping(value = "/fruita/delete/{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable Long id) {
        fruitServices.deleteFruitById(id);
        return new ResponseEntity<>(("Fruit deleted successfully- Fruit ID:" + id), HttpStatus.OK);
    }
}