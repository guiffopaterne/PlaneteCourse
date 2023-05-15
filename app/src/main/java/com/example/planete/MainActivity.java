package com.example.planete;

import static com.example.planete.ui.PlaneteActivity.initData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planete.dao.Planete;
import com.example.planete.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Realm uiThreadRealm;
    private ActivityMainBinding binding;
    private Button btnSelect,btnSave,btnDelete;
    private TextView textViewResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        on initialiser les element de la vue qui vont changer
        btnDelete=binding.btnDelete;
        btnSave=binding.btnSave;
        btnSelect=binding.btnSelect;
        textViewResultat=binding.resultat;
//        on ecouter le clic sur les boutons
        btnSelect.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        String realmName = "My Project";
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name(realmName)
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();
        uiThreadRealm = Realm.getInstance(config);
        JSONArray jsonStr = new JSONArray();
        Planete[] listes= initData;

            try {
                for(Planete p:listes)
                    jsonStr.put(p.convertPlanete2Json());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
//        JSONObject jsonStr = new JSONObject(initData[1]);
        textViewResultat.setText(jsonStr.toString());
        //        addChangeListenerToRealm(uiThreadRealm);
    }
    private void savePlanetes(ArrayList<Planete> listes) throws JSONException {
        JSONArray jsonStr = new JSONArray();
        for(Planete p:listes)
            jsonStr.put(p.convertPlanete2Json());
        uiThreadRealm.executeTransaction(r->{
                r.createAllFromJson(Planete.class,jsonStr);
//            r.createAllFromJson(Planete.class,jsonStr);
        });
        uiThreadRealm.commitTransaction();
    }
    @Override
    public void onClick(View v) {
        int id_btn = v.getId();
        if(id_btn==btnSelect.getId()){
            uiThreadRealm.executeTransaction(r->{
                    RealmResults<Planete> resultat = uiThreadRealm.where(Planete.class).findAll();
            textViewResultat.setText(resultat.toString());
            });

            return;
        }
        if(id_btn==btnSave.getId()){
            ArrayList<Planete> listes =new ArrayList<>(Arrays.asList(initData));
            try {
                savePlanetes(listes);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        if(id_btn==btnDelete.getId()){
            uiThreadRealm.executeTransaction(r->{
                Planete planete = uiThreadRealm.where(Planete.class).containsValue("id",1).findFirst();
               if(planete != null)
                    planete.deleteFromRealm();
            });
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiThreadRealm.close();
    }


}
