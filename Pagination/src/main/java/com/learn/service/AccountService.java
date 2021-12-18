package com.learn.service;

import com.learn.dto.AccountTransactionDTO;
import com.learn.entity.AccountStatement;
import com.learn.repository.AccountStatementRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("accountService")
public class AccountService {

    private AccountStatementRepository accountStatementRepository;

    @Autowired
    private void accountStatementRepository(AccountStatementRepository accountStatementRepository) {
        this.accountStatementRepository = accountStatementRepository;
    }

    @PostConstruct
    public void init() {
        List<AccountStatement> accountStatementList = IntStream.range(1, 31).mapToObj(i -> {
            AccountStatement accountStatement = null;
            try {
                if (i % 2 == 0) {
                    accountStatement = new AccountStatement(new BigDecimal("011078960008"), "F12TY100" + i, 5900d + i * 848 * 1.1, null, new SimpleDateFormat("dd/MM/yyyy").parse(i + "/12/2021"));
                } else {
                    accountStatement = new AccountStatement(new BigDecimal("011078960009"), "F12TY100" + i, null, 7800d + i * 878 * 1.1, new SimpleDateFormat("dd/MM/yyyy").parse(i + "/12/2021"));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return accountStatement;
        }).collect(Collectors.toList());

        accountStatementList.stream().forEach(i -> {
            if (i != null) accountStatementRepository.save(i);
        });
    }

    public String doAccountTransaction(BigDecimal account_no, AccountTransactionDTO accountTransactionDTO) {

        accountTransactionDTO.setAccountNumber(account_no);
        AccountStatement accountStatement = new AccountStatement();
        BeanUtils.copyProperties(accountTransactionDTO, accountStatement);

        AccountStatement savedData = accountStatementRepository.save(accountStatement);

        if (savedData != null) {
            return "Your transaction is successful " + savedData.getTxnReference();
        } else {
            return "Your transaction is failed";
        }
    }

    public List<AccountStatement> getLatestTransactions(BigDecimal account_no) {
        return accountStatementRepository.findByAccountNumber(account_no);
    }

    public List<AccountStatement> getLatestTransactionWithSorting(BigDecimal account_no, String field) {
        return accountStatementRepository.findByAccountNumber(account_no, Sort.by(Sort.Direction.DESC, field));
    }

    public Page<AccountStatement> getLatestTransactionByPagination(BigDecimal account_no, int pageNumber, int pageSize) {
        return accountStatementRepository.findByAccountNumber(account_no, PageRequest.of(pageNumber, pageSize));
    }

}
