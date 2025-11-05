package com.verduleria.backend.service;

import com.verduleria.backend.entity.RubroParticular;
import com.verduleria.backend.repository.RubroParticularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroParticularService {

  private final RubroParticularRepository rubroParticularRepository;

  public RubroParticularService(RubroParticularRepository rubroParticularRepository) {
    this.rubroParticularRepository = rubroParticularRepository;
  }

  public List<RubroParticular> getAllRubrosParticulares() {
    return rubroParticularRepository.findAll();
  }

  public RubroParticular getRubroParticularById(Long id) {
    return rubroParticularRepository.findById(id).orElse(null);
  }

  public RubroParticular createRubroParticular(RubroParticular rubro) {
    return rubroParticularRepository.save(rubro);
  }

  public RubroParticular updateRubroParticular(Long id, RubroParticular actualizado) {
    return rubroParticularRepository.findById(id)
        .map(rp -> {
          rp.setNombre(actualizado.getNombre());
          return rubroParticularRepository.save(rp);
        })
        .orElse(null);
  }

  public void deleteRubroParticular(Long id) {
    rubroParticularRepository.deleteById(id);
  }

  public List<RubroParticular> getRubrosParticularesByUsuario(Long usuarioId) {
    return rubroParticularRepository.findByUsuarioId(usuarioId);
  }
}
