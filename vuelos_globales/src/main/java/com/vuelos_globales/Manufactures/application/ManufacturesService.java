package com.vuelos_globales.Manufactures.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Manufactures.domain.Manufactures;
import com.vuelos_globales.Manufactures.infrastructure.ManufacturesRepository;
public class ManufacturesService {
    private final ManufacturesRepository manufacturesRepository;

    public ManufacturesService(ManufacturesRepository manufacturesRepository){
        this.manufacturesRepository = manufacturesRepository;
    }

    public void createManufacturer(Manufactures manufacture){
        manufacturesRepository.save(manufacture);
    }

    public void updateManufacturer(Manufactures manufacture){
        manufacturesRepository.update(manufacture);
    }

    public Optional<Manufactures> getManufacturerMyId(String id){
        return manufacturesRepository.findById(id);
    }

    public void deleteManufacturer(String id){
        manufacturesRepository.delete(id);
    }

    public List<Manufactures> getAllManufactures(){
        return manufacturesRepository.findAll();
    }
}
