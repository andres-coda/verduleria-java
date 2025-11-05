package com.verduleria.backend.controller;

import com.verduleria.backend.entity.Rubro;
import com.verduleria.backend.service.RubroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rubros")
public class RubroController {

  private final RubroService rubroService;

  public RubroController(RubroService rubroService) {
    this.rubroService = rubroService;
  }

  @GetMapping
  public List<Rubro> getAllRubros() {
    return rubroService.getAllRubros();
  }

  @GetMapping("/{id}")
  public Rubro getRubroById(@PathVariable Long id) {
    return rubroService.getRubroById(id);
  }

  @PostMapping
  public Rubro createRubro(@RequestBody Rubro rubro) {
    return rubroService.createRubro(rubro);
  }

  @PutMapping("/{id}")
  public Rubro updateRubro(@PathVariable Long id, @RequestBody Rubro rubro) {
    return rubroService.updateRubro(id, rubro);
  }

  @DeleteMapping("/{id}")
  public void deleteRubro(@PathVariable Long id) {
    rubroService.deleteRubro(id);
  }
}
