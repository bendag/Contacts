package com.demo.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    // À implémenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // À implémenter

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.navigation);

        //TODO: Listener pour les boutons de navigation
        nav.setOnNavigationItemSelectedListener((item) -> {
            switch(item.getItemId()){
                case R.id.menu_item_1:


                case R.id.menu_item_2:

            }
            return false;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top, menu);
        return true;
    }

    //TODO: Listener du bouton add contact
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_add:
                Intent intent = new Intent(this, EditContactActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
