package com.example.phobos.roomtest;

import java.util.Objects;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "people")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String avatar;
    private String planet;
    private int mass;

    public Person(String name, String avatar, String planet, int mass) {
        this(0, name, avatar, planet, mass);
    }

    @Ignore
    public Person(long id, String name, String avatar, String planet, int mass) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.planet = planet;
        this.mass = mass;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPlanet() {
        return planet;
    }

    public int getMass() {
        return mass;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return mass == person.mass &&
                Objects.equals(name, person.name) &&
                Objects.equals(avatar, person.avatar) &&
                Objects.equals(planet, person.planet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, avatar, planet, mass);
    }
}
