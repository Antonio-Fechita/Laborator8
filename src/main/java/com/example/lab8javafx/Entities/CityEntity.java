package com.example.lab8javafx.Entities;

import com.example.lab8javafx.graphtheory.Node;

import java.util.ArrayList;
import java.util.Objects;

public class CityEntity implements Node {
    CountryEntity country;
    String name;
    boolean capital;
    double latitude;
    double longitude;
    ArrayList<CityEntity> sisters = new ArrayList<>();

    public CityEntity(CountryEntity country, String name, boolean capital, double latitude, double longitude) {
        this.country = country;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "country=" + country.getName() +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return capital == that.capital && Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Objects.equals(country, that.country) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, name, capital, latitude, longitude);
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String getNodeIdentifier() {
        return name;
    }

    @Override
    public ArrayList<Node> getNeighbours()
    {
        ArrayList<Node> nodes = new ArrayList<>();

        for (CityEntity cityEntity: sisters)
        {
            nodes.add(cityEntity);
        }

        return nodes;
    }

    public void addSister(CityEntity newSister)
    {
        sisters.add(newSister);
    }
}
