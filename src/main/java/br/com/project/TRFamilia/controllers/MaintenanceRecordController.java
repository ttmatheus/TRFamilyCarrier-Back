package br.com.project.TRFamilia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.project.TRFamilia.dto.CreateMaintenanceRecordDTO;
import br.com.project.TRFamilia.models.MaintenanceRecord;
import br.com.project.TRFamilia.services.MaintenanceRecordService;

@RestController
@RequestMapping("/maintenance-records")
public class MaintenanceRecordController {

    @Autowired private MaintenanceRecordService service;

    @PostMapping
    public ResponseEntity<MaintenanceRecord> create(@RequestBody CreateMaintenanceRecordDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceRecord> update(@PathVariable Long id, @RequestBody CreateMaintenanceRecordDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceRecord>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
