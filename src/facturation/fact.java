/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturation;

/**
 *
 * @author NATHAN
 */
public class fact {
    
    private int id;
    private String nom_produit;
    private int qte;
    private int prix_unitaire;
    private int prix_total;
    private String date;

    public fact(int id, String nom_produit, int qte, int prix_unitaire, int prix_total,String date) {
        this.id = id;
        this.nom_produit = nom_produit;
        this.qte = qte;
        this.prix_unitaire = prix_unitaire;
        this.prix_total = prix_total;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(int prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(int prix_total) {
        this.prix_total = prix_total;
    }
    
}
