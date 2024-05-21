package edu.badpals.repository;

import edu.badpals.domain.Orden;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
public class OrdenRepo implements PanacheRepository<Orden> {
    public List<Orden> findByUserName(String name) {
        List<Orden> ordenes = this.listAll()
                .stream()
                .filter(o -> o.getUser().getNombre().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        return ordenes.isEmpty()? List.of(): ordenes;
    }
}
