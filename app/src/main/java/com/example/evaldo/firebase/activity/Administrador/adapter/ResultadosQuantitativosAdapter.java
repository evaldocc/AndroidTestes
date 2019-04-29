package com.example.evaldo.firebase.activity.Administrador.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evaldo.firebase.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultadosQuantitativosAdapter extends RecyclerView.Adapter<ResultadosQuantitativosAdapter.ViewHolder> {

    private HashMap<String, HashMap<String, Integer>> perguntas;
    private List<String> perguntaText;


    public ResultadosQuantitativosAdapter() {
        perguntaText = new ArrayList<>();
    }

    public ResultadosQuantitativosAdapter(HashMap<String, HashMap<String, Integer>> perguntas) {

        update(perguntas);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView perguntaView;
        private TextView respostasView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            perguntaView = itemView.findViewById(R.id.perguntaView);
            respostasView = itemView.findViewById(R.id.respostaView);
        }
    }

    public void update(HashMap<String, HashMap<String, Integer>> perguntas){
        this.perguntas = perguntas;
        perguntaText = new ArrayList<>(perguntas.keySet());

        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_resultados_quantitativos, viewGroup, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.perguntaView.setText(perguntaText.get(i));

        StringBuilder respostas = new StringBuilder();

        HashMap<String, Integer> resposta = perguntas.get(perguntaText.get(i));

        for (String key : resposta.keySet()) {
            respostas.append(key).append(" ").append(resposta.get(key)).append("\n");
        }

        viewHolder.respostasView.setText(respostas.toString());

    }

    @Override
    public int getItemCount() {
        return perguntaText.size();
    }
}
