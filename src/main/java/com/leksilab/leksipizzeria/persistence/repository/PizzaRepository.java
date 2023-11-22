package com.leksilab.leksipizzeria.persistence.repository;

import com.leksilab.leksipizzeria.persistence.entity.PizzaEntity;
import com.leksilab.leksipizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    int countByVeganTrue();
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    /* ESTO ES UNA FORMA
    @Query(value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :idPizza", nativeQuery = true)
    void updatePrice(@Param("idPizza") int idPizza, @Param("newPrice") double newPrice);*/

    @Query(value = "UPDATE pizza SET price = :#{#newPizzaPrice.newPrice} WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);
}
