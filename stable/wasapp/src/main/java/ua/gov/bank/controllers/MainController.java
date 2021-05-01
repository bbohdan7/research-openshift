package ua.gov.bank.controllers;

import ua.gov.bank.services.AccountService;
import ua.gov.bank.services.PaymentService;
import ua.gov.bank.services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Deprecated
@Startup
@Singleton
public class MainController {

    @Inject
    private UserService userSvc;

    @Inject
    private AccountService accountSvc;

    @Inject
    private PaymentService paymentSvc;

    @PostConstruct
    public void init() {

    }
}
