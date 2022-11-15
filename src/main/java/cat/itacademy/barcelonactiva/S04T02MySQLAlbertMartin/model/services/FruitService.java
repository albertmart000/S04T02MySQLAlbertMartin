package cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.services;


import cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.domain.Fruit;
import cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FruitService {

    @Autowired
    FruitRepository fruitRepository;

    public Fruit addFruit(Fruit fruit) {
        return this.fruitRepository.save(fruit);
    }

    public Fruit updateFruitById(Long id, Fruit fruitToUpdate) {
        Optional<Fruit> fruit = this.fruitRepository.findById(id);
        if (fruit.isPresent()) {
            Fruit fruitUpdated = fruit.get();
            fruitUpdated.setName(fruitToUpdate.getName());
            fruitUpdated.setNumberOfKilos(fruitToUpdate.getNumberOfKilos());
            return addFruit(fruitUpdated);
        }
        return null;
    }

    public Boolean deleteFruit(Long id) {
        fruitRepository.deleteById(id);
        return true;
    }

    public Optional<Fruit> getFruit(Long id) {
        return fruitRepository.findById(id);
    }

    public List<Fruit> getAllFruits() {
        List<Fruit> fruitList = new ArrayList<>();
        for (Fruit fruit : fruitRepository.findAll()) {
            fruitList.add(fruit);
        }
        return fruitList;
    }
}
