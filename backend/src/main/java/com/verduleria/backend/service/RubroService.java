package com.verduleria.backend.service;

import com.verduleria.backend.entity.Rubro;
import com.verduleria.backend.repository.RubroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroService {

  private final RubroRepository rubroRepository;

  public RubroService(RubroRepository rubroRepository) {
    this.rubroRepository = rubroRepository;
  }

  public List<Rubro> getAllRubros() {
    return rubroRepository.findAll();
  }

  public Rubro getRubroById(Long id) {
    return rubroRepository.findById(id).orElse(null);
  }

  public Rubro createRubro(Rubro rubro) {
    return rubroRepository.save(rubro);
  }

  public Rubro updateRubro(Long id, Rubro rubroActualizado) {
    return rubroRepository.findById(id)
        .map(rubro -> {
          rubro.setNombre(rubroActualizado.getNombre());
          return rubroRepository.save(rubro);
        })
        .orElse(null);
  }

  public void deleteRubro(Long id) {
    rubroRepository.deleteById(id);
  }
}
