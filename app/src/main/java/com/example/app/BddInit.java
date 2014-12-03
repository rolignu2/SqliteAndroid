package com.example.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.database.sqlite.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import java.util.Random;

public class BddInit extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdd_init);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bdd_init, menu);
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

        private  BddConexion con;
        private TextView txt;
        private TextView txt_u ;
        private  TextView txt_p;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_bdd_init, container, false);
            txt = (TextView)  rootView.findViewById(R.id.txtdata);
            Button cmd_guardar = (Button) rootView.findViewById(R.id.cmd_guardar);
            Button cmd_lista = (Button) rootView.findViewById(R.id.cmdlista);
            txt_u = (TextView) rootView.findViewById(R.id.editText);
            txt_p = (TextView) rootView.findViewById(R.id.editText2);
            con = new BddConexion(rootView.getContext());

            try{
                    SQLiteDatabase db = con.getWritableDatabase();
            }
            catch (Exception ex)
            {
                txt.setText(ex.getMessage());
            }

            txt.setText("Base de datos activa");
           // Toast.makeText(rootView.getContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();

            cmd_guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDatabase data = con.getWritableDatabase();
                    Random rnd = new Random();
                    int id_c = rnd.nextInt(10);
                    try{
                        txt.setText("comenzo");
                    data.execSQL("INSERT INTO LOGIN (ID_LOGIN , USUARIO , PASSWORD) VALUES ("
                            + id_c + ",'" + txt_u.getText() + "','" + txt_p.getText() + "')");
                       txt.setText("Todo bien insertado");
                    }catch (Exception ex){ txt.setText(ex.getMessage()); }
                }
            });

            cmd_lista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(rootView.getContext(), Lista.class);
                    startActivity(i);
                }
            });


            return rootView;
        }
    }

}
