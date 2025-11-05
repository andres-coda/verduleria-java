package com.verduleria.backend.controller;

import com.verduleria.backend.entity.RubroParticular;
import com.verduleria.backend.service.RubroParticularService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rubros-particulares")
public class RubroParticularController {

  private final RubroParticularService rubroParticularService;

  public RubroParticularController(RubroParticularService rubroParticularService) {
    this.rubroParticularService = rubroParticularService;
  }

  @GetMapping
  public List<RubroParticular> getAllRubrosParticulares() {
    return rubroParticularService.getAllRubrosParticulares();
  }

  @GetMapping("/{id}")
  public RubroParticular getRubroParticularById(@PathVariable Long id) {
    return rubroParticularService.getRubroParticularById(id);
  }

  @PostMapping
  public RubroParticular createRubroParticular(@RequestBody RubroParticular rubroParticular) {
    return rubroParticularService.createRubroParticular(rubroParticular);
  }

  @PutMapping("/{id}")
  public RubroParticular updateRubroParticular(@PathVariable Long id, @RequestBody RubroParticular rubroParticular) {
    return rubroParticularService.updateRubroParticular(id, rubroParticular);
  }

  @DeleteMapping("/{id}")
  public void deleteRubroParticular(@PathVariable Long id) {
    rubroParticularService.deleteRubroParticular(id);
  }

  @GetMapping("/usuario/{usuarioId}")
  public List<RubroParticular> getRubrosParticularesByUsuario(@PathVariable Long usuarioId) {
    return rubroParticularService.getRubrosParticularesByUsuario(usuarioId);
  }
}
