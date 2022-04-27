package com.example.lab8javafx.Entities;

import java.util.Objects;

public class CountryEntity {
    private String code;
    private String name;
    private ContinentEntity continent;


    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent=" + continent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity country = (CountryEntity) o;
        return Objects.equals(code, country.code) && Objects.equals(name, country.name) && Objects.equals(continent, country.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, continent);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContinentEntity getContinent() {
        return continent;
    }

    public void setContinent(ContinentEntity continent) {
        this.continent = continent;
    }

    public CountryEntity(String code, String name, ContinentEntity continent) {
        this.code = code;
        this.name = name;
        this.continent = continent;
    }
}
