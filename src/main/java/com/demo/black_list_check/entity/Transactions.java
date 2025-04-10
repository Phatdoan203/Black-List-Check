package com.demo.black_list_check.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id // Đánh dấu id là primary key
    private String id;
    private BigDecimal amount;
    private String fromAccount;
    private String toAccount;
    private Date timestamp; // Thay đổi từ String sang Date
    private String description;
    private String status;

    // Getter, Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getFromAccount() { return fromAccount; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }
    public String getToAccount() { return toAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
