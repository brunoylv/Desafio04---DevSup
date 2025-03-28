package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    // updateShouldUpdateResourceWhenIdExists
    // updateShouldReturnNotFoundWhenIdDoesNotExist


    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setUrl(dto.getUrl());
            entity.setDate(dto.getDate());
            entity.setCity(new City(dto.getCityId(), null));
            entity = repository.save(entity);
            return new EventDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource Id wasn't found " + id);
        }
    }
}
