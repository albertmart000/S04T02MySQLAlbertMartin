package cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.controllers;


import cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.domain.Fruit;
import cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FruitRestController {

    @Autowired
    private FruitService fruitService;

    @PostMapping("/fruits")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit fruit) {
        try {
            fruitService.addFruit(fruit);
            return new ResponseEntity<>(fruit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/fruits/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable("id") long id, @RequestBody Fruit fruit) {
        Fruit fruitToUpdate = fruitService.updateFruitById(id, fruit);
        if (fruitToUpdate != null) {
            return new ResponseEntity<>(fruitToUpdate, HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap fruita amb el id: " + id);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/fruits/{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable("id") long id) {
        try {
            fruitService.deleteFruit(id);
            return new ResponseEntity<>("S'ha esborrat la fruita amb el id: " + id,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No s'ha trobat cap fruita amb el id: " + id,
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fruits/{id}")
    public ResponseEntity<Fruit> getFruit(@PathVariable("id") long id) {
        Optional<Fruit> optionalFruit = this.fruitService.getFruit(id);
        if (optionalFruit.isPresent()) {
            return new ResponseEntity<>(optionalFruit.get(), HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap fruita amb el id: " + id);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fruits")
    public ResponseEntity<List<Fruit>> getAllFruits() {
        try {
            List<Fruit> fruitList = fruitService.getAllFruits();
            if (fruitList.isEmpty()){
                HttpHeaders headers = new HttpHeaders();
                headers.add("Error", "No n'hi ha cap fruita a la llista");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fruitList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
