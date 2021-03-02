package pizzahub_project.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Base64;
import java.util.Objects;

@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pizza_id")
    private int pizzaId;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private byte[] picture;

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPicture() {
        return Base64.getEncoder().encodeToString(picture);
    }

    public byte[] getPictureAsByte() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;
        Pizza pizza = (Pizza) o;
        return getPizzaId() == pizza.getPizzaId();
    }

    public int hashCode() {
        return Objects.hash(getPizzaId());
    }

    @Override
    public String toString() {
        return  "{" +
                "\"pizzaId\":\"" + getPizzaId() + "\", " +
                "\"name\":\"" + getName() + "\", " +
                "\"description\":\"" + getDescription() + "\"" +
//                "\"picture\":\"" + getPicture() + "\"" +
                "}";
    }
}
