package com.leksilab.leksipizzeria.persistence.repository;

import com.leksilab.leksipizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> { }
