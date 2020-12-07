package by.bundesliga.fan.bean;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {

    private int id;
    private String imageUrl;
    private String itemCategory;
    private String team;
    private String name;
    private String size;
    private String manufacturer;
    private double price;

    public Item(){}

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSize() {
        return size;
    }

    public String getTeam() {
        return team;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Double.compare(item.price, price) == 0 &&
                Objects.equals(imageUrl, item.imageUrl) &&
                Objects.equals(itemCategory, item.itemCategory) &&
                Objects.equals(team, item.team) &&
                Objects.equals(name, item.name) &&
                Objects.equals(size, item.size) &&
                Objects.equals(manufacturer, item.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, itemCategory, team, name, size, manufacturer, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", team='" + team + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                '}';
    }
}
