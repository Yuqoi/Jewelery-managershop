package org.yuqoi.managerapp.models.Invoice;


public class Invoice {

    private int id;
    private String customer_name;
    private double total_amount;
    private Payment payment;

    public Invoice(int id, String customer_name, double total_amount, Payment payment) {
        this.id = id;
        this.customer_name = customer_name;
        this.total_amount = total_amount;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public Payment getPayment() {
        return payment;
    }
}
