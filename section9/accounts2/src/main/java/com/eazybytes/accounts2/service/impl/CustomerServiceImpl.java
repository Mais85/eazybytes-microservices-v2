package com.eazybytes.accounts2.service.impl;

import com.eazybytes.accounts2.dto.AccountsDto;
import com.eazybytes.accounts2.dto.CardsDto;
import com.eazybytes.accounts2.dto.CustomerDetailsDto;
import com.eazybytes.accounts2.dto.LoansDto;
import com.eazybytes.accounts2.entity.Accounts;
import com.eazybytes.accounts2.entity.Customer;
import com.eazybytes.accounts2.exception.ResourceNotFoundException;
import com.eazybytes.accounts2.mapper.AccountsMapper;
import com.eazybytes.accounts2.mapper.CustomerMapper;
import com.eazybytes.accounts2.repository.AccountRepository;
import com.eazybytes.accounts2.repository.CustomerRepository;
import com.eazybytes.accounts2.service.CustomerService;
import com.eazybytes.accounts2.service.client.CardsFeignClient;
import com.eazybytes.accounts2.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber , String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));
        Accounts accounts = accountRepository. findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        return customerDetailsDto;
    }
}
