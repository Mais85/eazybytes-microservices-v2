package com.eazybytes.accounts2.service;

import com.eazybytes.accounts2.dto.CustomerDetailsDto;

public interface CustomerService {

    /**
     *
     * @param mobileNumber
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
