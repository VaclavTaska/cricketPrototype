package com.taska.java.crickets.cricketfarm.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taska.java.crickets.cricketfarm.model.Customer;
import com.taska.java.crickets.cricketfarm.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}
	
	@PostMapping
	public Customer createCustomer(@RequestBody final Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable("id") Long id) {
		return customerRepository.getOne(id);
	}
	
	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody final Customer customer, @PathVariable("id") Long id) {
		Customer existingCustomer = customerRepository.getOne(id);
		BeanUtils.copyProperties(customer, existingCustomer, "id");
		return customerRepository.saveAndFlush(existingCustomer);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
	}

}
