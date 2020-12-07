package by.bundesliga.fan.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNum;
    private String favTeam;
    private String userRole;

    public User(){}

    public User(int id, String login, String firstName, String lastName, String email, String phoneNum, String address, String favTeam, String userRole) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
        this.favTeam = favTeam;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getFavTeam() {
        return favTeam;
    }

    public String getAddress() {
        return address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFavTeam(String favTeam) {
        this.favTeam = favTeam;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setUsername(String username) {
        this.login = username;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(address, user.address) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNum, user.phoneNum) &&
                Objects.equals(favTeam, user.favTeam) &&
                Objects.equals(userRole, user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, firstName, lastName, address, email, phoneNum, favTeam, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", favTeam='" + favTeam + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}


