package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = ("".equals(maxDate)) ? today : LocalDate.parse(maxDate, dtf);
		LocalDate min = ("".equals(minDate)) ? max.minusYears(1L) : LocalDate.parse(minDate, dtf);
		return repository.searchReport(min, max, name, pageable);
	}

	public List<SaleMinSummaryDTO> getSummary(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = ("".equals(maxDate)) ? today : LocalDate.parse(maxDate, dtf);
		LocalDate min = ("".equals(minDate)) ? max.minusYears(1L) : LocalDate.parse(minDate, dtf);
		return repository.searchSummary(min, max);
	}

}
