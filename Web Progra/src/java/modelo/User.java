/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Kevin Trejos
 */
public class User {
    
    private String id;
    private String name;
    private String secondName;
    private String email;
    private String ubicacion;

    public User() {
    }

    public User(String id, String name, String secondName, String email, String ubicacion) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", secondName=" + secondName + ", email=" + email + ", ubicacion=" + ubicacion + '}';
    }
}
