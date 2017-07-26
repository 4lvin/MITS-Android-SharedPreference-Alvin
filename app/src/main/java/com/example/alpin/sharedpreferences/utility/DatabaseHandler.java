package com.example.alpin.sharedpreferences.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import com.example.alpin.sharedpreferences.model.Doa;
import com.example.alpin.sharedpreferences.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alpin on 20/07/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 11;
    private static final String DATABSE_NAME = "db_person";

    private static final String TABLE_PERSON = "person";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "pass";
    private static final String KEY_NO_HP = "no_hp";

    private static final String TABLE_DOA = "doa";
    private static final String KEY_ID_DOA = "id";
    private static final String KEY_NAME_DOA = "name";
    private static final String KEY_DOA = "isidoa";
    private static final String KEY_KET = "ket";
    private static final String KEY_IMAGE = "image";


    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHandler databaseHandler;
    private final String TAG = DatabaseHandler.class.getSimpleName();

    public DatabaseHandler(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    public static void init(Context context) {
        databaseHandler = new DatabaseHandler(context);
        sqLiteDatabase = databaseHandler.getWritableDatabase();
    }

    public static synchronized DatabaseHandler getInstance() {
        return databaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_DOA = "CREATE TABLE " + TABLE_DOA + "("
                + KEY_ID_DOA + " INTEGER PRIMARY KEY,"
                + KEY_NAME_DOA + " TEXT,"
                + KEY_DOA + " TEXT,"
                + KEY_KET + " TEXT,"
                + KEY_IMAGE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_DOA);

        String CREATE_TABLE_PERSON = "CREATE TABLE IF NOT EXISTS " + TABLE_PERSON + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASS + " TEXT,"
                + KEY_NO_HP + " INTEGER" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE_PERSON);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DOA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        onCreate(sqLiteDatabase);

    }

    public void addUser(Person person) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_EMAIL, person.getEmail());
        values.put(KEY_PASS, person.getPassword());
        values.put(KEY_NO_HP, person.getNoTelp());

        sqLiteDatabase.insert(TABLE_PERSON, null, values);
        Log.d(TAG, "insert table user success !");
    }
    public void addDoa(Doa doa) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_DOA, doa.getNama());
        values.put(KEY_DOA, doa.getDoa());
        values.put(KEY_KET, doa.getKet());
        values.put(KEY_IMAGE, doa.getImageAddrees());

        sqLiteDatabase.insert(TABLE_DOA, null, values);
        Log.d(TAG, "insert table doa success !");
    }

    public List<Person> getAllUser() {
        List<Person> persons = new ArrayList<>();
        String selectAllUser = "SELECT * FROM " + TABLE_PERSON + " ORDER BY " + KEY_ID + " DESC";

        Cursor cursor = sqLiteDatabase.rawQuery(selectAllUser, null);
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(cursor.getInt(0));
                person.setName(cursor.getString(1));
                person.setEmail(cursor.getString(2));
                person.setPassword(cursor.getString(3));
                person.setNoTelp(cursor.getInt(4));
                persons.add(person);
            } while (cursor.moveToNext());
        }
        return persons;
    }

    public List<Doa> getAllDoa() {
        List<Doa> doas = new ArrayList<>();
        String selectAllDoa = "SELECT * FROM " + TABLE_DOA + " ORDER BY " + KEY_ID_DOA + " DESC";

        Cursor cursor = sqLiteDatabase.rawQuery(selectAllDoa, null);
        if (cursor.moveToFirst()) {
            do {
                Doa doa = new Doa();
                doa.setId(cursor.getInt(0));
                doa.setNama(cursor.getString(1));
                doa.setDoa(cursor.getString(2));
                doa.setKet(cursor.getString(3));
                doa.setImageAddrees(cursor.getString(4));
                doas.add(doa);
            } while (cursor.moveToNext());
        }
        return doas;
    }

    public void updateDoa(Doa doa) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_DOA, doa.getNama());
        values.put(KEY_DOA, doa.getDoa());
        values.put(KEY_KET, doa.getKet());
        values.put(KEY_IMAGE, doa.getImageAddrees());


        sqLiteDatabase.update(TABLE_DOA, values, KEY_ID_DOA + " = '"+ doa.getId() +"'" , null);
        Log.d(TAG,"Update Succes !");
    }

    public void deleteDoa(Doa doa) {
        sqLiteDatabase.delete(TABLE_DOA, KEY_ID_DOA + " = ?",
                new String[]{String.valueOf(doa.getId())});
        Log.d(TAG,"Delete Succes !");
    }

    public void deleteDoaById(int id) {
        sqLiteDatabase.execSQL("DELETE FROM "+TABLE_DOA+" WHERE "+KEY_ID_DOA+" = "+id);
    }

    public boolean checkUser(String email, String pass){
        String selectQuery = "SELECT * FROM " + TABLE_PERSON + " WHERE " + KEY_EMAIL + " = '" + email
                + "' AND " + KEY_PASS  + " = '" + pass +"'";

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        return cursor.moveToFirst();
    }
}
