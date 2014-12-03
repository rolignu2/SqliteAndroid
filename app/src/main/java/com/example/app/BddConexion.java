package com.example.app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by rolandoantonio on 12-03-14.
 * Clase en la cualcreamos la base de datos prueba
 * la calse solo se le agrego una tabla
 *
 */
public class BddConexion extends SQLiteOpenHelper {


    private static int version =1;
    private static String BddName = "Prueba";
    private static SQLiteDatabase.CursorFactory Fact = null;
    private Context context;

    public BddConexion(Context context)
    {
        super(context , BddName, Fact, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        if(db.isReadOnly())
        {
            db = getWritableDatabase();
        }

         String tablas = "CREATE TABLE LOGIN IF NOT EXISTS ("
                 + "ID_LOGIN INT PRIMARY KEY , "
                 + "USUARIO TEXT NOT NULL, "
                 + "PASSWORD TEXT NOT NULL );";
        db.execSQL(tablas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
            //actualizar la db
    }
}
