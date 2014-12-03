package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolandoantonio on 12-03-14.
 */
public class ContentList extends BaseAdapter {

    private List<String> Listado = new ArrayList<String>();
    private Context context;

    public ContentList(List<String> L , Context context)
    {
        super();
        this.Listado = L;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.Listado.size();
    }

    @Override
    public Object getItem(int i) {
        return this.Listado.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup p) {

        final String data = this.getItem(i).toString();

        if (view == null && this.context != null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.listado_datos,null);
        }

        TextView txt = (TextView) view.findViewById(R.id.txtlistado_datos);
        txt.setText(data);

        return view;
    }
}
