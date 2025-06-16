package br.com.project.TRFamilia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.dto.FreightBillDTO;
import br.com.project.TRFamilia.services.FreightBillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("freightbill")
public class FreightBillController {
	@Autowired private FreightBillService freightBillService;

	@PostMapping("/create")
	@JustAdmin
	public ResponseEntity<?> createFreightBill(@RequestBody @Valid CreateFreightBillDTO createFreightBillDTO) {
		return freightBillService.saveFreightBill(createFreightBillDTO);
	}

	@GetMapping
	public List<FreightBillDTO> getTrip(
		@RequestParam(name = "user_id", required = true) Long userId) {
		return freightBillService.getFreightBillByUserId(userId);
	}
}
