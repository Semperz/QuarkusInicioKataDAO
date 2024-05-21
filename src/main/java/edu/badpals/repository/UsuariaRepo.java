package edu.badpals.repository;

import edu.badpals.domain.Usuaria;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuariaRepo implements PanacheRepositoryBase<Usuaria, String> {
}
