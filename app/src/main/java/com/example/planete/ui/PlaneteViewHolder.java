package com.example.planete.ui;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.planete.dao.Planete;
import com.example.planete.databinding.PlaneteBinding;

public class PlaneteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final PlaneteBinding ui;
    private final Context context;
    public OnItemClickListener listener;

    public PlaneteViewHolder(Context context, PlaneteBinding ui) {
        super(ui.getRoot());
        this.context=context;
        this.ui = ui;
    }
    public void setOnItemClickListener(OnItemClickListener l){
        this.listener=l;
    }
    @SuppressLint("SetTextI18n")
    public void setPlanete(Planete planete){
        //TODO afficher l'image
        ui.image.setOnClickListener(this);
        ui.nom.setText(planete.getNom());
        ui.distance.setText(Integer.toString(planete.getDistance()));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == ui.image.getId()){
            Log.w("ViewHolder","clik image click");
//
            listener.onItemClick(getAdapterPosition());
        }
    }
}