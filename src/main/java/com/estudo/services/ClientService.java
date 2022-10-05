package com.estudo.services;

import com.estudo.dto.ClientDTO;
import com.estudo.entities.ClientEntity;
import com.estudo.exceptions.NotFoundException;
import com.estudo.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ClientDTO> getClients() {
        List<ClientEntity> clients = clientRepository.findAll();

        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "client", key = "#id")
    public ClientDTO findClient(Long id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client Not Found"));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Transactional
    @CachePut(value = "client", key = "#client.id")
    public ClientDTO persistClient(ClientDTO client) {
        ClientEntity clientEntity = modelMapper.map(client, ClientEntity.class);
        clientRepository.save(clientEntity);

        return modelMapper.map(clientEntity, ClientDTO.class);
    }

    @CacheEvict(value = "client", key = "#id")
    public boolean deleteClient(Long id) {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
