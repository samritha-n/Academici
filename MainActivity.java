package com.example.sam_nagesh.academici;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    String name;
    EditText userInput;
    TextView myTextView;
    TextView nameText;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        userInput = (EditText) findViewById(R.id.getName);
        myTextView = (TextView) findViewById(R.id.name);
        nameText = (TextView) findViewById(R.id.hint);
        btn = (Button) findViewById(R.id.button);

        myTextView.setText(readFromFile()+"!");
        if (!(myTextView.getText().equals("!"))){
            userInput.setVisibility(View.INVISIBLE);
            nameText.setVisibility(View.INVISIBLE);
            btn.setVisibility(View.INVISIBLE);
            myTextView.setVisibility(View.VISIBLE);
        }
        else{
            userInput.setVisibility(View.VISIBLE);
            nameText.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);
            myTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_math) {
            //Open the math activity
            Intent myIntent = new Intent(this,MathActivity.class);
                    startActivity(myIntent);
        } else if (id == R.id.nav_science) {
            Intent myIntent=new Intent(this,ScienceActivity.class);
            startActivity(myIntent);
            //Open the science activity

        } else if (id == R.id.nav_english) {
            Intent myIntent=new Intent(this,EnglishActivity.class);
            startActivity(myIntent);
            //Open English Activity

        } else if (id == R.id.nav_language) {
            Intent myIntent=new Intent(this,LanguageActivity.class);
            startActivity(myIntent);
            //Open Language Activity

        } else if (id == R.id.nav_new) {
            Intent myIntent=new Intent(this,AddNewActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_favorites) {
            Intent myIntent=new Intent(this, Favorites.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void addName(View view)
    {
        name = userInput.getText().toString();
        writeToFile(userInput.getText().toString());

        myTextView.setText(name+"!");

        nameText.setVisibility(View.INVISIBLE);

        userInput.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);

        myTextView.setVisibility(View.VISIBLE);
    }
    private void writeToFile(String data) {
        Context context = this;
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("name.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("name.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}
