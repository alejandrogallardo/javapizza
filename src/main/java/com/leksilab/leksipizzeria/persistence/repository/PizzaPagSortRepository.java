package com.leksilab.leksipizzeria.persistence.repository;

import com.leksilab.leksipizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
}
