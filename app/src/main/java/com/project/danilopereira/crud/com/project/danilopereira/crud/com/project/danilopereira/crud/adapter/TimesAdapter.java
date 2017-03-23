package com.project.danilopereira.crud.com.project.danilopereira.crud.com.project.danilopereira.crud.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.danilopereira.crud.R;
import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Resultado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilopereira on 15/03/17.
 */

public class TimesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Resultado> resultados = new ArrayList<>();
    private NBAOnClickListener nbaOnClickListener;


    public TimesAdapter(Context context, List<Resultado> resultados, NBAOnClickListener nbaOnClickListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.resultados = resultados;
        this.nbaOnClickListener = nbaOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new AndroidItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final AndroidItemHolder itemHolder = (AndroidItemHolder) holder;

        itemHolder.tvResultado1.setText(String.valueOf(resultados.get(position).getResultadoTime1()));
        itemHolder.tvResultado2.setText(String.valueOf(resultados.get(position).getResultadoTime2()));

        String escudo1 = resultados.get(position).getTime1().getEscudo();
        String escudo2 = resultados.get(position).getTime2().getEscudo();

        itemHolder.ivTime1.setImageResource(getImage(escudo1));
        itemHolder.ivTime2.setImageResource(getImage(escudo2));

        itemHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                nbaOnClickListener.onLongClickCelular(itemHolder.itemView, position);

                return true;
            }
        });
    }

    private int getImage(String escudo1) {
        switch (escudo1){
            case ("bucks.gif"):
                return R.drawable.bucks;
            case ("bulls.gif"):
                return R.drawable.bulls;
            case ("cavaliers.gif"):
                return R.drawable.cavaliers;
            case ("celtics.gif"):
                return R.drawable.celtics;
            case ("clippers.gif"):
                return R.drawable.clippers;
            case ("grizzlies.gif"):
                return R.drawable.grizzlies;
            case ("hawks.gif"):
                return R.drawable.hawks;
            case ("heat.gif"):
                return R.drawable.heat;
            case ("hornets.gif"):
                return R.drawable.hornets;
            case ("jazz.gif"):
                return R.drawable.jazz;
            case ("kings.gif"):
                return R.drawable.kings;
            case ("knicks.gif"):
                return R.drawable.knicks;
            case ("lakers.gif"):
                return R.drawable.lakers;
            case ("magic.gif"):
                return R.drawable.magic;
            case ("mavericks.gif"):
                return R.drawable.mavericks;
            case ("okc.png"):
                return R.drawable.okc;
            case ("pacers.gif"):
                return R.drawable.pacers;
            case ("pelicans.gif"):
                return R.drawable.pelicans;
            case ("pistons.gif"):
                return R.drawable.pistons;
            case ("raptors.gif"):
                return R.drawable.raptors;
            case ("rockets.gif"):
                return R.drawable.rockets;
            case ("seventh6ers.gif"):
                return R.drawable.seventh6ers;
            case ("spurs.gif"):
                return R.drawable.spurs;
            case ("suns.gif"):
                return R.drawable.suns;
            case ("timberwolves.gif"):
                return R.drawable.timberwolves;
            case ("trailblazers.gif"):
                return R.drawable.trailblazers;
            case ("warriors.gif"):
                return R.drawable.warriors;
            case ("wizards.gif"):
                return R.drawable.wizards;
            default:
                return R.mipmap.ic_launcher;
        }

    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    private class AndroidItemHolder extends RecyclerView.ViewHolder{

        ImageView ivTime1;
        ImageView ivTime2;
        TextView tvResultado1, tvResultado2;

        public AndroidItemHolder(View itemView) {
            super(itemView);

            ivTime1 = (ImageView) itemView.findViewById(R.id.ivTime1);
            ivTime2 = (ImageView) itemView.findViewById(R.id.ivTime2);

            tvResultado1 = (TextView) itemView.findViewById(R.id.tvResultado1);
            tvResultado2 = (TextView) itemView.findViewById(R.id.tvResultado2);


        }


    }

    public interface NBAOnClickListener {
        void onLongClickCelular(View view, int posicao);
    }
}
