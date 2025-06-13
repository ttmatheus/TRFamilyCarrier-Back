package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.services.FreightBillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("freightbill")
public class FreightBillController {
	@Autowired private FreightBillService freightBillService;

	@PostMapping("/create")
	public ResponseEntity<?> createFreightBill(@RequestBody @Valid CreateFreightBillDTO createFreightBillDTO) {
		return freightBillService.saveFreightBill(createFreightBillDTO);
	}
}
