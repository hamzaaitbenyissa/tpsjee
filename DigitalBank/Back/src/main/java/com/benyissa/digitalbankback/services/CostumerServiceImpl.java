package com.benyissa.digitalbankback.services;

import com.benyissa.digitalbankback.DTOs.CustomerDTO;
import com.benyissa.digitalbankback.entities.*;
import com.benyissa.digitalbankback.exceptions.CustomerNotFoundException;
import com.benyissa.digitalbankback.mappers.BankAccountMapperImpl;
import com.benyissa.digitalbankback.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
/* Slf4j and log4j are used for logging configuration,*/
@Slf4j
public class CostumerServiceImpl implements CostumerService {

    private CustomerRepository customerRepository;
    private BankAccountMapperImpl bankAccountMapper;


    //get all costumers
    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> bankAccountMapper.fromCustomer(customer))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        return customerRepository.findCustomerByFirstNameContainsOrLastNameContains(keyword,keyword).stream()
                .map(customer -> bankAccountMapper.fromCustomer(customer))
                .collect(Collectors.toList());
    }

    //    save a new costumer
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("saving new customer");
        Customer customer = bankAccountMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return bankAccountMapper.fromCustomer(savedCustomer);
    }


    //return a costumer using id
    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) throw new CustomerNotFoundException("Customer not found");
        return bankAccountMapper.fromCustomer(customer);
    }

    //updating a costumer
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info("update customer");
        Customer customer = customerRepository.findById(customerDTO.getId()).orElse(null);
        if (customer == null) throw new CustomerNotFoundException("Customer not found");
        customer = bankAccountMapper.fromCustomerDTO(customerDTO);
        return bankAccountMapper.fromCustomer(customerRepository.save(customer));
    }


    //delete  a costumer using id
    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        log.info("delete customer");
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) throw new CustomerNotFoundException("Customer not found");
        customerRepository.deleteById(customerId);
    }


}
