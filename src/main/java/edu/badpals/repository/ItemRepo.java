package edu.badpals.repository;

import edu.badpals.domain.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepo implements PanacheRepositoryBase<Item, String> {
}
