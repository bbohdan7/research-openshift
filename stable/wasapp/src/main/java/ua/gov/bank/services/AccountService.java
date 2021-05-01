package ua.gov.bank.services;

import ua.gov.bank.model.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountService extends Service<Account> {

    @PersistenceContext(unitName = "PaymentsPU")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public AccountService() {
        super(Account.class);
    }

    public List<Account> findByUserName(String firstname, String lastname) {
        return getEntityManager().createNamedQuery("Account.findByUserName", Account.class)
                .setParameter("firstname", String.format("%%%s%%", firstname))
                .setParameter("lastname", String.format("%%%s%%", lastname))
                .getResultList();
    }

    public List<Account> findByUser(Integer id) {
        return getEntityManager().createNamedQuery("Account.findByUser", Account.class)
                .setParameter("user", id).getResultList();
    }

}
