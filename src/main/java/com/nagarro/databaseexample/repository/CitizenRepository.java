package com.nagarro.databaseexample.repository;

import com.nagarro.databaseexample.model.Citizen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CitizenRepository implements PanacheRepository<Citizen> {
}
