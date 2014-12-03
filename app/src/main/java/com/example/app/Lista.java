package com.example.app;

import android.app.LauncherActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Lista extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        try{
        if (savedInstanceState == null) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
        }catch (Exception ex)
        {

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ListView lista;

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_lista, container, false);
            lista = (ListView) rootView.findViewById(R.id.vista_lista);
            this.InitList(rootView.getContext());
            return rootView;
        }

        private void InitList(Context context)
        {
            BddConexion bdd = new BddConexion(context);
            SQLiteDatabase db = bdd.getReadableDatabase();
            Cursor C = db.rawQuery("SELECT * FROM LOGIN" ,null);
            List<String> listado = new ArrayList<String>();
            if(C.moveToFirst())
            {
                do {
                    String data = C.getString(0) + " "+ C.getString(1) + " " + C.getString(2);
                    listado.add(data);
                }while (C.moveToNext());
                lista.setAdapter(new ContentList(listado , context));
            }
        }


    }

}
