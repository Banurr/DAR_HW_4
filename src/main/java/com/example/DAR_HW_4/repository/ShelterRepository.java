package com.example.DAR_HW_4.repository;

import com.example.DAR_HW_4.model.Shelter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends MongoRepository<Shelter, String>, QuerydslPredicateExecutor<Shelter> {
}
