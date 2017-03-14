package com.example.sam_nagesh.academici;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AddNewActivity extends AppCompatActivity {

    String category="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
    }
    public void openMain(View view){
        Intent myIntent = new Intent(this,MainActivity.class);
        startActivity(myIntent);
    }
    public void math(View view)
    {
        category="Math";
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Math Chosen", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void science(View view)
    {
        category="Science";
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Science Chosen", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void language(View view)
    {
       category="Language";
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Language Chosen", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void english(View view)
    {
        category="English";
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "English Chosen", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void favorite(View view)
    {
        category="Favorite";
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Favorite Chosen", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void writeEnglishFile()
    {
        //Write profileList to file - The name of the file is called profileSaves.bin
        Context context = this;

        try
        {
            FileOutputStream fileOutput = context.openFileOutput("englishWebsites.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(EnglishActivity.englishSites);
            objectOutput.close();
            fileOutput.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void writeMathFile()
    {
        //Write profileList to file - The name of the file is called profileSaves.bin
        Context context = this;

        try
        {
            FileOutputStream fileOutput = context.openFileOutput("mathWebsites.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(MathActivity.mathSites);
            objectOutput.close();
            fileOutput.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void writeScienceFile()
    {
        //Write profileList to file - The name of the file is called profileSaves.bin
        Context context = this;

        try
        {
            FileOutputStream fileOutput = context.openFileOutput("scienceWebsites.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(ScienceActivity.scienceSites);
            objectOutput.close();
            fileOutput.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void writeLanguageFile()
    {
        //Write profileList to file - The name of the file is called profileSaves.bin
        Context context = this;

        try
        {
            FileOutputStream fileOutput = context.openFileOutput("languageWebsites.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(LanguageActivity.languageSites);
            objectOutput.close();
            fileOutput.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void writeFavoriteFile()
    {
        //Write profileList to file - The name of the file is called profileSaves.bin
        Context context = this;

        try
        {
            FileOutputStream fileOutput = context.openFileOutput("favoriteWebsites.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(Favorites.favoriteSites);
            objectOutput.close();
            fileOutput.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addIt(View view)
    {

        EditText userInput = (EditText) findViewById(R.id.website);
        String website = userInput.getText().toString();
        if (category.equals("Math")) {
            MathActivity.mathSites.add(new newWebsites("Math", website));
            writeMathFile();
        }
        if (category.equals("Science")) {
            ScienceActivity.scienceSites.add(new newWebsites("Science", website));
            writeScienceFile();
        }
        if (category.equals("English")) {
            EnglishActivity.englishSites.add(new newWebsites("English", website));
            writeEnglishFile();
        }
        if (category.equals("Language")){
            LanguageActivity.languageSites.add(new newWebsites("Language", website));
            writeLanguageFile();
        }
        if (category.equals("Favorite")){
            Favorites.favoriteSites.add(new newWebsites("Favorite",website));
            writeFavoriteFile();
        }
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Website Added", Toast.LENGTH_SHORT);
        toast.show();
    }

}
