package com.example.evaldo.firebase.activity.Administrador.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.activity.Administrador.Classes.ResultadosQuestionario;

import java.util.ArrayList;

public class ResultadosQuantitativosRecyclerAdapter extends RecyclerView.Adapter<ResultadosQuantitativosRecyclerAdapter.MeuViewHolderResultadosQuantitativos> {

    private ArrayList<PerguntasQuestionario> listaPergAdapter;
    //private ArrayList<ResultadosQuestionario> listResultAdapter;

    private Context context;

    public ResultadosQuantitativosRecyclerAdapter(ArrayList<PerguntasQuestionario> listPergAdapter, Context c) {
        context = c;
        listaPergAdapter = listPergAdapter;
        //System.out.println("ResultadosResumidosRecyclerAdapter getItemCount() = " + listaPergAdapter.size());
    }

    public ResultadosQuantitativosRecyclerAdapter(ArrayList<PerguntasQuestionario> listPergAdapter) {
        listaPergAdapter = listPergAdapter;
    }

    @NonNull
    @Override
    public MeuViewHolderResultadosQuantitativos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exibir_resultados_quantitativos, viewGroup, false);

        return new MeuViewHolderResultadosQuantitativos(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolderResultadosQuantitativos meuViewHolderResultadosQuantitativos, int position) {

        //System.out.println("public void onBindViewHolder(@NonNull MeuViewHolderResultadosDetalhados meuViewHolderResultadosDetalhados, int " + position +"+)");
        meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosNomeQuestionario.setText("Administrador Resposável: " + listaPergAdapter.get(position).getAdministradorResponsavel());
        meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosAdministradorResponsavel.setText("Nome do Questionário: " + listaPergAdapter.get(position).getNomeQuestionario());

        meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta1.setText("Pergunta 1: " + listaPergAdapter.get(position).getPergunta1());
        meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta1.setText(listaPergAdapter.get(position).getResposta1());

        if (listaPergAdapter.get(position).getPergunta2() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta2.setText("Pergunta 2: " + listaPergAdapter.get(position).getPergunta2());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta2.setText(listaPergAdapter.get(position).getResposta2());
        }
        if (listaPergAdapter.get(position).getPergunta3() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta3.setText("Pergunta 3: " + listaPergAdapter.get(position).getPergunta3());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta3.setText(listaPergAdapter.get(position).getResposta3());
        }
        if (listaPergAdapter.get(position).getPergunta4() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta4.setText("Pergunta 4: " + listaPergAdapter.get(position).getPergunta4());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta4.setText(listaPergAdapter.get(position).getResposta4());
        }
        if (listaPergAdapter.get(position).getPergunta5() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta5.setText("Pergunta 5: " + listaPergAdapter.get(position).getPergunta5());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta5.setText(listaPergAdapter.get(position).getResposta5());
        }
        if (listaPergAdapter.get(position).getPergunta6() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta6.setText("Pergunta 6: " + listaPergAdapter.get(position).getPergunta6());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta6.setText(listaPergAdapter.get(position).getResposta6());
        }
        if (listaPergAdapter.get(position).getPergunta7() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta7.setText("Pergunta 7: " + listaPergAdapter.get(position).getPergunta7());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta7.setText(listaPergAdapter.get(position).getResposta7());
        }
        if (listaPergAdapter.get(position).getPergunta8() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta8.setText("Pergunta 8: " + listaPergAdapter.get(position).getPergunta8());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta8.setText(listaPergAdapter.get(position).getResposta8());
        }
        if (listaPergAdapter.get(position).getPergunta9() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta9.setText("Pergunta 9: " + listaPergAdapter.get(position).getPergunta9());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta9.setText(listaPergAdapter.get(position).getResposta9());
        }
        if (listaPergAdapter.get(position).getPergunta10() != null) {
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosPergunta10.setText("Pergunta 10: " + listaPergAdapter.get(position).getPergunta10());
            meuViewHolderResultadosQuantitativos.tvExibirResultadosQuantitativosResposta10.setText(listaPergAdapter.get(position).getResposta10());
        }
    }

    @Override
    public int getItemCount() {
        //System.out.println("ResultadosResumidosRecyclerAdapter getItemCount() = " + listaPergAdapter.size());
        return listaPergAdapter.size();
    }

    public class MeuViewHolderResultadosQuantitativos extends RecyclerView.ViewHolder {
        public TextView tvExibirResultadosQuantitativosKey;
        public TextView tvExibirResultadosQuantitativosAdministradorResponsavel;
        public TextView tvExibirResultadosQuantitativosNomeQuestionario;
        public TextView tvExibirResultadosQuantitativosPergunta1;
        public TextView tvExibirResultadosQuantitativosPergunta2;
        public TextView tvExibirResultadosQuantitativosPergunta3;
        public TextView tvExibirResultadosQuantitativosPergunta4;
        public TextView tvExibirResultadosQuantitativosPergunta5;
        public TextView tvExibirResultadosQuantitativosPergunta6;
        public TextView tvExibirResultadosQuantitativosPergunta7;
        public TextView tvExibirResultadosQuantitativosPergunta8;
        public TextView tvExibirResultadosQuantitativosPergunta9;
        public TextView tvExibirResultadosQuantitativosPergunta10;
        public TextView tvExibirResultadosQuantitativosResposta1;
        public TextView tvExibirResultadosQuantitativosResposta2;
        public TextView tvExibirResultadosQuantitativosResposta3;
        public TextView tvExibirResultadosQuantitativosResposta4;
        public TextView tvExibirResultadosQuantitativosResposta5;
        public TextView tvExibirResultadosQuantitativosResposta6;
        public TextView tvExibirResultadosQuantitativosResposta7;
        public TextView tvExibirResultadosQuantitativosResposta8;
        public TextView tvExibirResultadosQuantitativosResposta9;
        public TextView tvExibirResultadosQuantitativosResposta10;

        public MeuViewHolderResultadosQuantitativos(@NonNull View itemView) {
            super(itemView);

            tvExibirResultadosQuantitativosAdministradorResponsavel = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_administradorResponsavel);
            tvExibirResultadosQuantitativosNomeQuestionario = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos__nomeQuestionario);
            tvExibirResultadosQuantitativosPergunta1 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta1);
            tvExibirResultadosQuantitativosPergunta2 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta2);
            tvExibirResultadosQuantitativosPergunta3 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta3);
            tvExibirResultadosQuantitativosPergunta4 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta4);
            tvExibirResultadosQuantitativosPergunta5 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta5);
            tvExibirResultadosQuantitativosPergunta6 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta6);
            tvExibirResultadosQuantitativosPergunta7 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta7);
            tvExibirResultadosQuantitativosPergunta8 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta8);
            tvExibirResultadosQuantitativosPergunta9 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta9);
            tvExibirResultadosQuantitativosPergunta10 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_pergunta10);
            tvExibirResultadosQuantitativosResposta1 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta1);
            tvExibirResultadosQuantitativosResposta2 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta2);
            tvExibirResultadosQuantitativosResposta3 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta3);
            tvExibirResultadosQuantitativosResposta4 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta4);
            tvExibirResultadosQuantitativosResposta5 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta5);
            tvExibirResultadosQuantitativosResposta6 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta6);
            tvExibirResultadosQuantitativosResposta7 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta7);
            tvExibirResultadosQuantitativosResposta8 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta8);
            tvExibirResultadosQuantitativosResposta9 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta9);
            tvExibirResultadosQuantitativosResposta10 = itemView.findViewById(R.id.tv_exibirResultadosQuantitativos_resposta10);

        }
    }


}
