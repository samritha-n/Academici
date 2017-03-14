package com.example.sam_nagesh.academici;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MathActivity extends AppCompatActivity {

    public static ArrayList<newWebsites> mathSites= new ArrayList<newWebsites>();
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFile();
        setContentView(R.layout.activity_math2);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.llayout);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

        for (int i=0; i<mathSites.size(); i++)
        {
            Button button = new Button(this);
            button.setWidth(70);
            button.setHeight(70);
            button.setText(mathSites.get(i).getName());
            layout.addView(button,params);
            url=mathSites.get(i).getUrl();

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Open Website", Toast.LENGTH_SHORT);
                    toast.show();

                    Uri uri = Uri.parse("http://"+url);
                    Intent intent=new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            });
        }

    }
    public void openDesmos(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.desmos.com"));
        startActivity(browserIntent);
    }
    public void openDictionary(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dictionary.com/"));
        startActivity(browserIntent);
    }
    public void openMain(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
    public void loadFile()
    {
        try
        {
            FileInputStream fileInput = this.openFileInput("mathWebsites.bin");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput );
            mathSites = (ArrayList<newWebsites>) objectInput.readObject();
            objectInput.close();
            fileInput.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}


