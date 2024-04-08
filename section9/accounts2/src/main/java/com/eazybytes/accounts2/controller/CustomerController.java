package com.eazybytes.accounts2.controller;


import com.eazybytes.accounts2.dto.CustomerDetailsDto;
import com.eazybytes.accounts2.dto.ErrorDto;
import com.eazybytes.accounts2.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(
        name = "REST APIs for Customers in EazyBank",
        description = "CRUD REST APIs in EazyBank to  FETCH Customer details"
)
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "FETCH Customer Details REST API",
            description = "REST API to fetch  Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails( @RequestHeader("eazybank-correlation-id") String correlationId,
                                                                    @RequestParam
                                                                   @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                   String mobileNumber){
        log.info("eazyBank-correlation-id found: {}", correlationId);
       return ResponseEntity.status(HttpStatus.OK).body(customerService.fetchCustomerDetails(mobileNumber, correlationId));
    }
}
