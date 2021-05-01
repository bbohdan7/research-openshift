package ua.gov.bank.services;

import ua.gov.bank.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserService extends Service<User> {

    @PersistenceContext(unitName = "PaymentsPU")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public UserService() {
        super(User.class);
    }

    public List<User> findByFullName(String firstname, String lastname) {
        return getEntityManager().createNamedQuery("User.findByName", User.class)
                .setParameter("firstname", String.format("%%%s%%", firstname))
                .setParameter("lastname", String.format("%%%s%%", lastname))
                .getResultList();
    }

}
