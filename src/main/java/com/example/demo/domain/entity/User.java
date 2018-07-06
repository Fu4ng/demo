package com.example.demo.domain.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {

    private Long id;

    private String address;

    private String phone;
    @NotEmpty
    @Size(min=5,max=16,message="{userName.size}")
    private String userName;

    @NotEmpty
    @Size(min=4,max=25,message="{password.size}")
    private String password;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //
    @Override
    public boolean equals(Object that) {
        // TODO Auto-generated method stub
        return EqualsBuilder.reflectionEquals(this, that, "userName","password");
    }
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return HashCodeBuilder.reflectionHashCode(this,"userName","password");
    }
}
