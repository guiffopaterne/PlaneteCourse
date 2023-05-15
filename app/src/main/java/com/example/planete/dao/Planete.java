package com.example.planete.dao;

import static com.example.planete.ui.PlaneteActivity.TAG;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Planete extends RealmObject {
    @PrimaryKey  private int id;
    private String nom;
    private int distance;
    public Planete(){

    }

    public Planete( int id,String nom, int distance) {
        this.id=id;
        this.nom = nom;
        this.distance = distance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public int getId(){
        return this.id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planete planete = (Planete) o;
        return getDistance() == planete.getDistance() && getNom().equals(planete.getNom());
    }

    public JSONObject convertPlanete2Json() throws JSONException {
        final JSONObject obj = new JSONObject();
        obj.put("nom",this.nom);
        obj.put("id",this.id);
        obj.put("distance",this.distance);
        Log.e(TAG,"erreur "+obj.toString());
        return obj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getDistance());
    }

    @Override
    public String toString() {
        return "Planete{" +
                "nom='" + nom + '\'' +
                ", distance=" + distance +
                '}';
    }
}
