package com.example.sam_nagesh.academici;
import java.io.Serializable;

/**
 * Created by sam_nagesh on 5/10/16.
 */

public class newWebsites implements Serializable {
    String category, url, name="";
    public newWebsites(String category, String url)
    {
        this.category=category;
        this.url=url;
    }
    public String getCategory()
    {
        return category;
    }
    public String getUrl()
    {
        return url;
    }
    public String getName()
    {
        int firstIndex=url.indexOf(".");
        int secondIndex=url.indexOf("com");
        name=url.substring(firstIndex+1,secondIndex-1);

        return name;
    }
}
