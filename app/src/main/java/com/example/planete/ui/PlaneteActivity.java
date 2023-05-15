        package com.example.planete.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.planete.dao.Planete;
import com.example.planete.databinding.ActivityPlaneteBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class PlaneteActivity extends AppCompatActivity {

    private ActivityPlaneteBinding ui;
    public static String TAG="planeteActivity";

    public static Planete[] initData={
            new Planete(1, "Mars",227),
            new Planete(2, "Jupiter",778),
            new Planete(3, "Terre",150),
            new Planete(4, "Venus",108),
            new Planete(5, "Pluton",90856),
            new Planete(6, "Mercure",58),
            new Planete(7, "Uranus",90856),
            new Planete(8, "Saturne",90856),
    };
     ArrayList<Planete> listes;
    private  PlaneteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // mettre en place le layout contenant le RecyclerView
        ui = ActivityPlaneteBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        //initialisation de la liste des planetes
        this.listes = new ArrayList<>(Arrays.asList(initData));

        //creer une instance de l'adaptateur du recyclerView des planetes
        this.adapter = new PlaneteAdapter(this,listes);

        //on affecte l'adaptateur au recyclerView
        ui.recycler.setAdapter(adapter);
        Log.e(TAG,"La taille de notre liste est de "+listes.size());

       // layout manager liste
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ui.recycler.setLayoutManager(lm);

        // séparateur
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ui.recycler.addItemDecoration(dividerItemDecoration);
        adapter.setOnItemClickListener(this::onItemClick);
    }
    private void onItemClick(int position){
        this.listes.get(position).setNom("BABY");
        this.adapter.notifyItemChanged(position);
    }
}