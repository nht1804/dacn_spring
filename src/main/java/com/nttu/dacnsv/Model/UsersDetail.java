package com.nttu.dacnsv.Model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;

public class UsersDetail {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private String number;

    public enum Gender {
        MALE, FEMALE, NONE
    }

    public UsersDetail(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String address, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.number = number;
    }

    public UsersDetail() {
        this.lastName = null;
        this.dateOfBirth = LocalDate.now();
        this.gender = Gender.NONE;
        this.address = null;
        this.number = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}