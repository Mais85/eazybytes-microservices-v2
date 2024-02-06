package com.eazybytes.accounts2.service;

import com.eazybytes.accounts2.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto  - CustomerDto ob
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
