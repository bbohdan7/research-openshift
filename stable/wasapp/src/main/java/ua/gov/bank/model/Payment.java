package ua.gov.bank.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "payments")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(generator = "PAYMENTS", strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate = new Date();

    @OneToOne
    @JoinColumn(name = "source_user_id", referencedColumnName = "id")
    private User payer;

    @OneToOne
    @JoinColumn(name = "destination_user_id", referencedColumnName = "id")
    private User payee;

    @OneToOne
    @JoinColumn(name = "source_account_id", referencedColumnName = "id")
    private Account payerAccount;

    @OneToOne
    @JoinColumn(name = "destination_account_id", referencedColumnName = "id")
    private Account payeeAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public Account getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(Account payerAccount) {
        this.payerAccount = payerAccount;
    }

    public Account getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(Account payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", payer=" + payer +
                ", payee=" + payee +
                ", payerAccount=" + payerAccount +
                ", payeeAccount=" + payeeAccount +
                '}';
    }
}
