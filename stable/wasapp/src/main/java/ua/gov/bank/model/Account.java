package ua.gov.bank.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "accounts")
@NamedQueries({
        @NamedQuery(name = "Account.findByUserName", query = "SELECT a FROM Account a WHERE a.user.firstname LIKE :firstname AND a.user.lastname LIKE :lastname"),
        @NamedQuery(name = "Account.findByUser", query = "SELECT a FROM Account a WHERE a.user.id = :user")
})
public class Account implements Serializable {

    @Id
    @GeneratedValue(generator = "ACCOUNTS", strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "balance")
    private Float balance;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
