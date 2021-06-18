package com.example.retailrewards.data;


import com.example.retailrewards.models.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PurchasesRepository extends CrudRepository<Purchase, Integer> {

  public List<Purchase> findAllByCustomerId(int customerId);




}
