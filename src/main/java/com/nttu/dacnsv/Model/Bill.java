package com.nttu.dacnsv.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "Bills")
public class Bill {
    @Id
    private String id;
        private String customerID;
    private String carID;
    private BillStatus status;
    private LocalDateTime createDate;
    private LocalDateTime acceptDate;
    private LocalDateTime refuseDate;
    private LocalDateTime transactionDate;
    private int total;
    private String note;

    public enum BillStatus {
        CANCELED, WAITING, PAID, PROCESSING
    }

    public Bill(String customerID, String carID, BillStatus status, LocalDateTime createDate, LocalDateTime acceptDate, LocalDateTime refuseDate, LocalDateTime transactionDate, int total, String note) {
        this.customerID = customerID;
        this.carID = carID;
        this.status = status;
        this.createDate = createDate;
        this.acceptDate = acceptDate;
        this.refuseDate = refuseDate;
        this.transactionDate = transactionDate;
        this.total = total;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(LocalDateTime acceptDate) {
        this.acceptDate = acceptDate;
    }

    public LocalDateTime getRefuseDate() {
        return refuseDate;
    }

    public void setRefuseDate(LocalDateTime refuseDate) {
        this.refuseDate = refuseDate;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
