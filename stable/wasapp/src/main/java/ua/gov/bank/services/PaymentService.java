package ua.gov.bank.services;

import ua.gov.bank.model.Account;
import ua.gov.bank.model.Payment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PaymentService extends Service<Payment> {

    @Inject
    private AccountService accountService;

    @PersistenceContext(unitName = "PaymentsPU")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public PaymentService() {
        super(Payment.class);
    }

    @Override
    public void create(Payment entity) {
        Account payerAccount = accountService.find(entity.getPayerAccount().getId());
        Account payeeAccount = accountService.find(entity.getPayeeAccount().getId());

        float amountToPay = entity.getAmount();
        float balance = payerAccount.getBalance();

        //Payer cannot pay if their balance is less than the amount due to pay.
        if (balance < amountToPay) {
            return;
        }

        //If there's enough funds on the balance then we need to subtract amount from user's balance
        //and add it to the balance of the recipient.
        payerAccount.setBalance(balance - amountToPay);
        payeeAccount.setBalance(payeeAccount.getBalance() + amountToPay);

        //Persist those changes for the accounts.
        accountService.update(payerAccount);
        accountService.update(payeeAccount);

        //Finally we're able to consider the payment to be successful and we can create a new row.
        super.create(entity);
    }
}
