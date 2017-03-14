package com.example.sam_nagesh.academici;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Favorites extends AppCompatActivity {
    public static ArrayList<newWebsites> favoriteSites=new ArrayList<newWebsites>();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFile();
        setContentView(R.layout.activity_favorites);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.llayout);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        for (int i=0; i<favoriteSites.size(); i++)
        {
            Button button = new Button(this);
            button.setText(favoriteSites.get(i).getName());
            layout.addView(button,params);
            url=favoriteSites.get(i).getUrl();

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Open Website", Toast.LENGTH_SHORT);
                    toast.show();

                    Uri uri = Uri.parse("http://" + url);
                    Intent intent=new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }
    }
    public void openNaviance(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://connection.naviance.com/family-connection/auth/login/?hsid=mountainview"));
        startActivity(browserIntent);

    }
    public void openAeries(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mvla.asp.aeries.net/student/LoginParent.aspx?page=default.aspx"));
        startActivity(browserIntent);
    }
    public void openMail(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com"));
        startActivity(browserIntent);

    }
    public void openClassroom(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://classroom.google.com/h"));
        startActivity(browserIntent);

    }
    public void openMain(View view)
    {
        Intent myIntent=new Intent(this,MainActivity.class);
        startActivity(myIntent);
    }
    public void loadFile()
    {
        //Load profileList from file - The name of the file is called profileSaves.bin
        try
        {
            FileInputStream fileInput = this.openFileInput("favoriteWebsites.bin");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput );
            favoriteSites = (ArrayList<newWebsites>) objectInput.readObject();
            objectInput.close();
            fileInput.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
