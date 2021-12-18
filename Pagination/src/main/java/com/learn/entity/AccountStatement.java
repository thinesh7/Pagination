package com.learn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT_STMT")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
/*
    @JsonIgnoreProperties(ignoreUnknown=true) is applicable at deserialization of JSON to Java object (POJO) only.
    If your POJO does not contain certain properties that JSON does contain, they are ignored and no error is thrown
 */
public class AccountStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STMT_ID")
    private Long statementId;

    @Column(name = "ACCOUNT_NUMBER")
    private BigDecimal accountNumber;

    @Column(name = "TRANSACTION_REFERENCE")
    private String txnReference;

    @Column(name = "DEBIT")
    private Double debit;

    @Column(name = "CREDIT")
    private Double credit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "TRANSACTION_DATE")
    private Date txnDate;

    public AccountStatement(BigDecimal accountNumber, String txnReference, Double debit, Double credit, Date txnDate) {
        this.accountNumber = accountNumber;
        this.txnReference = txnReference;
        this.debit = debit;
        this.credit = credit;
        this.txnDate = txnDate;
    }
}
