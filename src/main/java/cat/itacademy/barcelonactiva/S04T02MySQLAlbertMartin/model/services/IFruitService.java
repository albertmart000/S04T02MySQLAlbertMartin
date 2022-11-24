package cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.services;


import cat.itacademy.barcelonactiva.S04T02MySQLAlbertMartin.model.domain.Fruit;
import java.util.List;
import java.util.Optional;



public interface IFruitService {
	
    Fruit addFruit(Fruit fruit);

    Fruit updateFruitById(Long id, Fruit fruitToUpdate);

    Boolean deleteFruit(Long id);

    Optional<Fruit> getFruit(Long id);

    List<Fruit> getAllFruits();

}
