package edu.badpals;

import edu.badpals.domain.Item;
import edu.badpals.domain.Orden;
import edu.badpals.domain.Usuaria;
import edu.badpals.repository.ItemRepo;
import edu.badpals.repository.OrdenRepo;
import edu.badpals.repository.UsuariaRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceOlli {
    @Inject
    OrdenRepo ordenRepo;
    @Inject
    ItemRepo itemRepo;
    @Inject
    UsuariaRepo usuariaRepo;
    public ServiceOlli() {
    }

    public Usuaria cargaUsuaria(String nombre) {
        Optional<Usuaria> usuario = usuariaRepo.findByIdOptional(nombre);
        return usuario.orElseGet(Usuaria::new);
    }

    public Item cargaItem(String objeto) {
        Optional<Item> item = itemRepo.findByIdOptional(objeto);
        return item.orElseGet(Item::new);
    }

    public List<Orden> cargaOrden(String usuaria_nombre) {
        return ordenRepo.findByUserName(usuaria_nombre);
    }
    @Transactional
    public Orden comanda(String usuaria, String objeto) {
        Orden orden = null;
        Optional<Usuaria> usuario = usuariaRepo.findByIdOptional(usuaria);
        Optional<Item> item = itemRepo.findByIdOptional(objeto);
        if (usuario.isPresent() && item.isPresent()
                && usuario.get().getDestreza() >= item.get().getQuality()
        ){
            orden = new Orden(usuario.get(), item.get());
            ordenRepo.persist(orden);
        }
        return orden;
    }

    public List<Orden> comandaMultiple(String usuaria, List<String> objetos) {
        Optional<Usuaria> usuario = usuariaRepo.findByIdOptional(usuaria);
        if (usuario.isEmpty()){
            return Collections.emptyList();
        }
        List<Orden> comandas = new ArrayList<>();
        Orden orden = null;
        for (String objeto: objetos){
            orden = this.comanda(usuaria,objeto);
            if (orden != null){
                comandas.add(orden);
            }
        }
        return comandas;
    }
}
