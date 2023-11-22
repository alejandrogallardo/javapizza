package com.leksilab.leksipizzeria.service;

import com.leksilab.leksipizzeria.persistence.entity.PizzaEntity;
import com.leksilab.leksipizzeria.persistence.repository.PizzaPagSortRepository;
import com.leksilab.leksipizzeria.persistence.repository.PizzaRepository;
import com.leksilab.leksipizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    //public List<PizzaEntity> getAll() {
    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
//        return this.pizzaRepository.findAll();
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

//    public List<PizzaEntity> getAvailable() {
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        System.out.println(this.pizzaRepository.countByVeganTrue());
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
//        Pageable pageRequest = PageRequest.of(page, elements, Sort.by(sortBy));
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        //return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name) {
        //return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
//        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElse(null);
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzaRepository.updatePrice(dto);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }
}
