package by.bundesliga.fan.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private String state;
    private String address;
    private String phone;
    private Map<Item, Integer> items;
    private Date orderDate;
    private String comment;

    public Order(){}

    public Order(int id, String address, String phone, Map<Item, Integer> items, Date orderDate, String state, String comment){
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.state = state;
        this.items = items;
        this.orderDate = orderDate;
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

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", items=" + items +
                ", orderDate=" + orderDate +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(state, order.state) &&
                Objects.equals(address, order.address) &&
                Objects.equals(phone, order.phone) &&
                Objects.equals(items, order.items) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(comment, order.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, address, phone, items, orderDate, comment);
    }
}
