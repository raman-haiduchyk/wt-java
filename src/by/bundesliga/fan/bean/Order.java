package by.bundesliga.fan.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private User customer;
    private Map<Item, Integer> items;
    private Date orderDate;
    private String paymentType;
    private String comment;

    public Order(){}

    public Order(int id, User customer, Map<Item, Integer> items, Date orderDate, String paymentType, String comment){
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.orderDate = orderDate;
        this.paymentType = paymentType;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public String getComment() {
        return comment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public User getCustomer() {
        return customer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(items, order.items) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(paymentType, order.paymentType) &&
                Objects.equals(comment, order.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, items, orderDate, paymentType, comment);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", items=" + items +
                ", orderDate=" + orderDate +
                ", paymentType='" + paymentType + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
