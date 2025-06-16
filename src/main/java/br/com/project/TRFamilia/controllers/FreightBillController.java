package br.com.project.TRFamilia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.dto.FreightBillDTO;
import br.com.project.TRFamilia.models.FreightBill;
import br.com.project.TRFamilia.services.FreightBillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("freightbill")
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

	@GetMapping
	public List<FreightBillDTO> getTrip(
		@RequestParam(name = "user_id", required = true) Long userId) {
		return freightBillService.getFreightBillByUserId(userId);
	}
}
