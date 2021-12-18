package com.learn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

/*
    @JsonInclude(Include.NON_EMPTY) is used at serialization of POJO to JSON and it says, skip POJO properties
    that are: null or what is considered empty are not to be included. Definition of emptiness is data type-specific.
*/
 
public class AccountTransactionDTO {

    private Long statementId;

    private BigDecimal accountNumber;

    private String txnReference;

    private Double debit;

    private Double credit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date txnDate;
}
