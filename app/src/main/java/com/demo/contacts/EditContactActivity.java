package com.demo.contacts;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.demo.contacts.databinding.ContactEditActivityBinding;

public class EditContactActivity extends AppCompatActivity {
    boolean is_new = false;
    ContactEditActivityBinding mBinding;
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // À implémenter

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.contact_edit_activity);
        Intent intent = getIntent();
        String edit_type = (String) intent.getSerializableExtra("EDIT_TYPE");
        /*
        int id = intent.getIntExtra("C_ID", 0);
        String fname = intent.getStringExtra("C_FNAME");
        String lname = intent.getStringExtra("C_LNAME");
        String email = intent.getStringExtra("C_EMAIL");
        String phone = intent.getStringExtra("C_PHONE");
        boolean favorite = intent.getBooleanExtra("C_FAVORITE", false);*/
        contact = (Contact)intent.getSerializableExtra("CONTACT");
        if(contact.getId() == -1) is_new = true;
        mBinding.setContact(contact);
        setTitle(edit_type);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    //TODO: Listener du bouton add contact
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_save:
                Intent data = new Intent();
                Contact c = mBinding.getContact();
                data.putExtra("CONTACT", c);
                data.putExtra("IS_NEW", is_new);
                /*intent.putExtra("C_FNAME", c.getFirstName());
                intent.putExtra("C_LNAME", c.getLastName());
                intent.putExtra("C_EMAIL", c.getEmail());
                intent.putExtra("C_PHONE", c.getPhone());
                intent.putExtra("C_FAVORITE", c.getFavorite());
                intent.putExtra("C_ID", c.getId());*/
                setResult(RESULT_OK, data);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
/*
    public void setContact(int id, String fname, String lname, String email, String phone, boolean favorite){
        contact.setId(id);
        contact.setFirstName(fname);
        contact.setLastName(lname);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setFavorite(favorite);
    }*/
}
