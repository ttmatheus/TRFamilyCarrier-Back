package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.models.FreightBill;
import br.com.project.TRFamilia.services.FreightBillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/freight-bill")
public class FreightBillController {
    @Autowired private FreightBillService freightBillService;

    @PostMapping("/create")
    @JustAdmin
    public FreightBill createFreightBill(@RequestBody @Valid CreateFreightBillDTO dto) {
        return freightBillService.saveFreightBill(dto);
    }

    @PutMapping("/{id}")
    @JustAdmin
    public FreightBill updateFreightBill(@PathVariable Long id, @RequestBody @Valid CreateFreightBillDTO dto) {
        return freightBillService.updateFreightBill(id, dto);
    }

    @DeleteMapping("/{id}")
    @JustAdmin
    public void deleteFreightBill(@PathVariable Long id) {
        freightBillService.deleteFreightBill(id);
    }

    @GetMapping("/{id}")
    public FreightBill getFreightBillById(@PathVariable Long id) {
        return freightBillService.getFreightBillById(id);
    }
}
