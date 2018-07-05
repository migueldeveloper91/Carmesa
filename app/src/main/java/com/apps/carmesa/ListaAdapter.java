package com.apps.carmesa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListaAdapter extends ArrayAdapter<Producto> {
    private  final List<Producto> list;
    private  final Context context;


    public ListaAdapter(Context context, List<Producto> list) {
        super(context, R.layout.list_fila,list);
        this.context = context;
        this.list = list;
    }


        static class ViewHolder{

            protected TextView tvTitulo;
            protected ImageView imageView_producto;
            protected TextView tvValor;
            protected CheckBox checkBox;

        }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null){
            LayoutInflater inflator = LayoutInflater.from(context);
            view = inflator.inflate(R.layout.list_fila,null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvTitulo =(TextView) view.findViewById(R.id.tvTitulo);

            viewHolder.imageView_producto = (ImageView) view.findViewById(R.id.imageView_Producto);
            viewHolder.tvValor = (TextView) view.findViewById(R.id.tvValor);
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.check);


            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Producto element = (Producto) viewHolder.checkBox.getTag();
                    element.setSelected(buttonView.isChecked());
                }
            });

            view.setTag(viewHolder);
            viewHolder.checkBox.setTag(list.get(position));
        }else{
            view = convertView;
            ((ViewHolder) view.getTag()).checkBox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvTitulo.setText(list.get(position).getTitulo());
        holder.imageView_producto.setImageResource(list.get(position).getId_Imagen());
        holder.tvValor.setText(list.get(position).getValor());
        holder.checkBox.setChecked(list.get(position).isSelected());

        return view;

    }
    @Override

    public int getCount() {
        return super.getCount();
    }

    @Override

    public Producto getItem(int position) {
        return super.getItem(position);
    }
}
