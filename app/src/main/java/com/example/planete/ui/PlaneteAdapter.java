package com.example.planete.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planete.dao.Planete;
import com.example.planete.databinding.PlaneteBinding;
import com.example.planete.ui.PlaneteViewHolder;

import java.util.List;

public class PlaneteAdapter extends RecyclerView.Adapter<PlaneteViewHolder> {
    private List<Planete> listPlanete;
    private Context context;

    public PlaneteAdapter(Context context,List<Planete> listPlanete) {
        this.listPlanete = listPlanete;
        this.context = context;
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener l) {

        this.listener = l;
    }

        @NonNull
    @Override
    public PlaneteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaneteBinding binding = PlaneteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//            holder.setOnItemClickListener(this.listener) ; }
        return new PlaneteViewHolder(context,binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaneteViewHolder holder, int position) {
        holder.setPlanete(listPlanete.get(position));
        holder.setOnItemClickListener(this.listener);
    }

    @Override
    public int getItemCount() {
        return this.listPlanete.size();
    }

}
