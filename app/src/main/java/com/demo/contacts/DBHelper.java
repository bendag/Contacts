package com.demo.contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // À implémenter
    final static String DB_NAME = "contacts.db";
    final static int VERSION = 1;

    final static String TABLE_CONTACTS = "contacts";
    final static String C_ID = "_id";
    final static String C_FNAME = "_fname";
    final static String C_LNAME = "_lname";
    final static String C_EMAIL = "_email";
    final static String C_PHONE = "_phone";
    final static String C_FAVORITE = "_favorite";

    private static SQLiteDatabase db = null;

    public DBHelper(Context context) {
        // À implémenter. Vous avez le droit de changer la signature du constructeur.
        super(context, DB_NAME, null, VERSION);

        if(db == null){
            db = getWritableDatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // On crée un table avec (id, fname, lname, email, phone, favorite)
        String sql_1 = "create table " + TABLE_CONTACTS + " ( "
                + C_ID + " integer primary key autoincrement, "
                + C_FNAME + " text not null default 'Ex', "
                + C_LNAME + " text not null default 'Machina', "
                + C_EMAIL + " text, "
                + C_PHONE + " text, "
                + C_FAVORITE + " int not null default 0);";
        Log.d("SQL", sql_1);
        db.execSQL(sql_1);

        //on insere 3 valeur.. Benjamin, David et Guillaume. Les emails et numeros ne fonctionnent pas
        //id sauto incremente. pas besoin dy toucher
        String sql_2 =
                "INSERT or replace INTO " + TABLE_CONTACTS + " "
                + "("+C_FNAME+", "+C_LNAME+", "+C_EMAIL+", "+C_PHONE +", " + C_FAVORITE + ") "
                + "VALUES "
                + "('Benjamin','Dagenais','benjamin.dagenais@unecompagnie.com','514-123-1234', 1),"
                + "('David','Garon','david.garon@uneautrecompagnie.com','514-432-4321', 1),"
                + "('Guillaume','Phaneuf','guillaume.phaneuf@umontreal.ca','514-296....', 1);";
        Log.d("SQL", sql_2);
        db.execSQL(sql_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //onUpgrade idiot, on new fait que detruire et recreer
        String sql_drop = "drop table if exists " + TABLE_CONTACTS;
        Log.d("SQL", sql_drop);
        db.execSQL(sql_drop);

        onCreate(db);
    }

    //DBHelper soccupe de retourner une liste ordonnee par prenom de A a Z
    public List<Contact> getContacts(boolean isFavorite){
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS +" ORDER BY " + C_FNAME + " ASC", null);
        List<Contact> contacts = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                // Passing values
                int id = c.getInt(c.getColumnIndex(C_ID));
                String fname = c.getString(c.getColumnIndex(C_FNAME));
                String lname = c.getString(c.getColumnIndex(C_LNAME));
                String email = c.getString(c.getColumnIndex(C_EMAIL));
                String phone = c.getString(c.getColumnIndex(C_PHONE));
                int ifavorite = c.getInt(c.getColumnIndex(C_FAVORITE));
                boolean favorite = false;
                if(ifavorite != 0) favorite = true;

                Contact contact = new Contact();
                contact.setId(id);
                contact.setFirstName(fname);
                contact.setLastName(lname);
                contact.setEmail(email);
                contact.setPhone(phone);
                contact.setFavorite(favorite);

                if(favorite==isFavorite || isFavorite == false) contacts.add(contact);
            } while(c.moveToNext());
        }
        return contacts;
    }

    //on ajoute un contact
    //Lincrement des ids est automatique
    public void addContact(Contact contact){
        int favorite = 0;
        if(contact.getFavorite()) favorite = 1;
        String sql =
                "INSERT or replace INTO " + TABLE_CONTACTS + " "
                + "("+C_FNAME+", "+C_LNAME+", "+C_EMAIL+", "+C_PHONE +", "+C_FAVORITE+") "
                + "VALUES "
                + "('"+contact.getFirstName()+"', '"+contact.getLastName()+"', '"+contact.getEmail()+"', '"+contact.getPhone()+"', "+favorite+");";
        db.execSQL(sql);

    }

    //on edit un contact a laide du id
    public void editContact(Contact contact){
        int favorite = 0;
        if(contact.getFavorite()) favorite = 1;
        String sql =
                "UPDATE " + TABLE_CONTACTS + " SET "
                        + C_FNAME + " = '" + contact.getFirstName() + "', "
                        + C_LNAME + " = '" + contact.getLastName() + "', "
                        + C_EMAIL + " = '" + contact.getEmail() + "', "
                        + C_PHONE + " = '" + contact.getPhone() + "', "
                        + C_FAVORITE + " = " + favorite + " "
                        + "WHERE " + C_ID + " = " + contact.getId() + ";";
        db.execSQL(sql);
    }

    public void deletedContact(int c_id){
        String sql =
                "DELETE from " + TABLE_CONTACTS
                        + " WHERE " + C_ID + " = " + c_id + ";";
        db.execSQL(sql);
    }
}
