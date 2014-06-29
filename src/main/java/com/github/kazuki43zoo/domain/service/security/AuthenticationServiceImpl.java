package com.github.kazuki43zoo.domain.service.security;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.DateFactory;

import com.github.kazuki43zoo.domain.model.Account;
import com.github.kazuki43zoo.domain.model.AccountAuthenticationHistory;
import com.github.kazuki43zoo.domain.model.AuthenticationType;
import com.github.kazuki43zoo.domain.repository.account.AccountRepository;

@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    DateFactory dateFactory;

    @Inject
    AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean isLogin(Account account) {
        AccountAuthenticationHistory lastSuccessAuthenticationHistory = accountRepository
                .findOneLastSuccessAuthenticationHistoryByAccountUuid(account.getAccountUuid());
        if (lastSuccessAuthenticationHistory == null) {
            return false;
        }
        return lastSuccessAuthenticationHistory.getAuthenticationType() == AuthenticationType.login;
    }

    @Override
    public void createAuthenticationFailureHistory(String failedAccountId,
            AccountAuthenticationHistory authenticationHistory, AuthenticationType type,
            String failureReason) {
        Account failedAccount = accountRepository.findOneByAccountId(failedAccountId);
        if (failedAccount == null) {
            return;
        }
        authenticationHistory.setFailureReason(failureReason);
        createAuthenticationHistory(failedAccount, authenticationHistory, AuthenticationType.login,
                false);
    }

    @Override
    public void createAuthenticationSuccessHistory(Account account,
            AccountAuthenticationHistory authenticationHistory, AuthenticationType type) {
        createAuthenticationHistory(account, authenticationHistory, type, true);
    }

    private void createAuthenticationHistory(Account account,
            AccountAuthenticationHistory authenticationHistory, AuthenticationType type,
            boolean result) {
        DateTime currentDateTime = dateFactory.newDateTime();

        authenticationHistory.setAccountUuid(account.getAccountUuid());
        authenticationHistory.setAuthenticationType(type);
        authenticationHistory.setAuthenticationResult(result);
        authenticationHistory.setCreatedAt(currentDateTime);

        accountRepository.createAuthenticationHistory(authenticationHistory);
    }
}