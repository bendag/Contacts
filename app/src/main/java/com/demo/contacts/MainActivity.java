package com.demo.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import com.demo.contacts.RecyclerUtils.ContactRecyclerAdapter;
import com.demo.contacts.RecyclerUtils.ContactSwipeCallback;

/**
 * Devoir 4 - IFT2905
 *
 * Auteurs :
 * Benjamin Dagenais
 * David Garon
 * Guillaume Phaneuf
 *
 * Date :
 * 30 Avril 2019
 */

public class MainActivity extends AppCompatActivity  {
    public final static String MY_KEY = "CONTACTS"; //pour notre savedInstanceState

    RecyclerView rv;
    List<Contact> list_contacts; //pour notre liste de contact
    Contact[] contacts; // pour notre savedInstanceState
    DBHelper dbh;
    ContactRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On initialise notre DBHelper
        dbh = new DBHelper(this);

        //On retrouve notre bar de navigation du bas
        BottomNavigationView nav = findViewById(R.id.navigation);


        //On instancie notre liste de contact
        //Cette appel de getContacts() donne aussi des valeurs a notre table contacts pour le savedInstanceState
        list_contacts = getContacts(savedInstanceState,true);

        //On instancie notre adapter
        adapter = new ContactRecyclerAdapter(list_contacts, this);

        // On retrouve notre RecyclerView
        rv = findViewById(R.id.contact_list);

        //On initialise notre adapter a notre RecyclerView
        rv.setAdapter(adapter);

        //On initialise notre ContactSwipeCallback pour pouvoir swipe et supprimer des contacts
        ItemTouchHelper.SimpleCallback contactSwipeCallback = new ContactSwipeCallback(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(contactSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        //On change is_favorite de notre adapter
        //On inverse les couleurs des icones
        //On mentionne le changement de liste a notre adapter
        nav.setOnNavigationItemSelectedListener((item) -> {
            switch(item.getItemId()){
                case R.id.menu_item_1:
                    adapter.setFavorite(true);
                    nav.getMenu().getItem(1).setChecked(false);
                    nav.getMenu().getItem(0).setChecked(true);
                    break;

                case R.id.menu_item_2:
                    adapter.setFavorite(false);
                    nav.getMenu().getItem(0).setChecked(false);
                    nav.getMenu().getItem(1).setChecked(true);
                    break;

                default:
                    break;
            }
            list_contacts.clear();
            list_contacts.addAll(getContacts(adapter.getFavorite()));
            adapter.notifyDataSetChanged();
            return false;
        });

    }

    //On sauvegarde nos contacts pour letat
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(MY_KEY, contacts);
        super.onSaveInstanceState(outState);
    }

    //On ajoute notre bouton dajout de contact
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top, menu);
        return true;
    }

    //On veut ajouter un nouveau contact
    //On instancie un new Contact() et initialise ses valeurs
    //Important de mettre son id a -1 pour quEditContactActivity sache quil sajit dun nouveau contact
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_add:
                Intent intent = new Intent(this, EditContactActivity.class);
                Contact c = new Contact();
                c.setId(-1);
                c.setFavorite(false);
                c.setPhone("");
                c.setEmail("");
                c.setLastName("");
                c.setFirstName("");
                intent.putExtra("CONTACT", c);
                startActivityForResult(intent, 1);

        }
        return super.onOptionsItemSelected(item);
    }

    //On recoit un resultat de lactivite EditContactActivity
    //Si le resultat est un nouveau contact on appel addContact()
    //Si le resultat est une modification de contact on appel editContact()
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Contact c = (Contact)data.getSerializableExtra("CONTACT");
                boolean is_new = data.getBooleanExtra("IS_NEW", false);
                if(is_new)
                    addContact(c);
                else
                    editContact(c);
            }
        }
    }

    //On ajoute un nouveau contact a la base de donnees et a la liste de contacts
    //On mentionne le changement a ladapter
    public void addContact(Contact c){
        dbh.addContact(c);
        list_contacts.clear();
        list_contacts.addAll(getContacts(adapter.getFavorite()));
        adapter.notifyDataSetChanged();
    }

    //On change un contact existant dans la base de donnees et la liste de contacts
    //On mentionne le changement a ladapter
    public void editContact(Contact c){
        dbh.editContact(c);
        list_contacts.clear();
        list_contacts.addAll(getContacts(adapter.getFavorite()));
        adapter.notifyDataSetChanged();
    }

    //On retrouve les contacts et on sassure de preserver letat avec savedInstanceState
    //On appel cette methode lors du onCreate
    public List<Contact> getContacts(Bundle savedInstanceState, boolean isFavorite){
        contacts = null;
        List<Contact> c_list = new ArrayList<>();
        if(savedInstanceState != null) {
            contacts = (Contact[]) savedInstanceState.get(MY_KEY);
            int table_size = contacts.length;
            for(int i = 0; i<table_size; i++){
                c_list.add(contacts[i]);
            }
        }
        if(contacts == null){
            c_list = dbh.getContacts(isFavorite);
            int list_size = c_list.size();
            contacts = new Contact[list_size];
            for(int i = 0; i < list_size; i++){
                contacts[i] = c_list.get(i);
            }
        }
        return c_list;
    }

    //On retrouve la liste de contacts de la base de donnees
    public List<Contact> getContacts(boolean isFavorite){
        return dbh.getContacts(isFavorite);
    }

    //On supprime un contact de la liste et de la base de donnees
    //On mentionne a ladapter la nouvelle liste
    public void deleteContact(int index){
        dbh.deletedContact(list_contacts.remove(index).getId());
        adapter.notifyDataSetChanged();
    }
}
