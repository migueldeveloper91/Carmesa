package com.apps.carmesa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class adaptador extends BaseAdapter{

    Context contexto;
    List<datos> ListaObjetos;

    public adaptador(Context contexto, List<datos> listaObjetos) {
        this.contexto = contexto;
        ListaObjetos = listaObjetos;
    }

    @Override
    public int getCount() {
        return ListaObjetos.size();
    }

    @Override
    public Object getItem(int i) {
        return ListaObjetos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ListaObjetos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista = view;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista = inflate.inflate(R.layout.listaseleccionada,null);

        TextView valor = (TextView) vista.findViewById(R.id.tvTituloseleccion);
        TextView titulo = (TextView) vista.findViewById(R.id.tvPrecioSeleccion);

        titulo.setText(ListaObjetos.get(i).getTitulo());
        valor.setText(ListaObjetos.get(i).getValor());

        return vista;
    }
}
