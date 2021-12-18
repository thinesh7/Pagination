package com.learn.controller;

import com.learn.dto.AccountTransactionDTO;
import com.learn.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/account")
public class AppController {

    public static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/transaction")
    public ResponseEntity accountTransaction(@RequestBody AccountTransactionDTO accountTransactionDTO,
                                             @RequestParam("accNo") BigDecimal accountNumber) {

        logger.info("Started accountTransaction()");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Result", accountService.doAccountTransaction(accountNumber, accountTransactionDTO));
        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/statement")
    public ResponseEntity getLatestTransactions(@RequestParam("accNo") BigDecimal accountNumber) {

        logger.info("Started getLatestTransactions()");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Result", accountService.getLatestTransactions(accountNumber));
        return ResponseEntity.ok(resultMap);
    }

    // http://localhost:8080/account/statement/sort?accNo=0115987456&field=statementId
    @GetMapping("/statement/sort")
    public ResponseEntity getLatestTransactionsBySort(@RequestParam("accNo") BigDecimal accountNumber,
                                                      @RequestParam("field") String field) {

        logger.info("Started getLatestTransactionsBySort()");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Result", accountService.getLatestTransactionWithSorting(accountNumber, field));
        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/statement/pagination")
    public ResponseEntity getLatestTransactionsByPagination(@RequestParam("accNo") BigDecimal accountNumber,
                                                            @RequestParam("pageNo") int pageNumber,
                                                            @RequestParam("pageSize") int pageSize) {

        logger.info("Started getLatestTransactionsByPagination()");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Result", accountService.getLatestTransactionByPagination(accountNumber, pageNumber, pageSize));
        return ResponseEntity.ok(resultMap);
    }


}
