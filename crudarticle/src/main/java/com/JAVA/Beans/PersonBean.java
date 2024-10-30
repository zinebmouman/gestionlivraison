package com.JAVA.Beans;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PersonBean
 */
@WebServlet("/PersonBean")
public class PersonBean extends HttpServlet {
	private int id;
    private String nom;
    private int prix;

    // Constructeur de la classe Article
    public void setId(int id) {
        this.id = id;
    }

    // Getters
    public int getId() {
        return id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }


    public int getPrix() {
        return prix;
    }

}
