package com.dailymotion.dailymotion4j;

import java.util.List;

import com.dailymotion.dailymotion4j.api.SearchField;
import com.dailymotion.dailymotion4j.api.SelectionField;
import com.dailymotion.dailymotion4j.api.SortingField;
import com.dailymotion.dailymotion4j.exceptions.DailymotionResponseProcessingException;
import com.dailymotion.dailymotion4j.model.Video;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DailymotionClient client = new DailymotionClient();
        try {
        	SearchField srf = new SearchField();
        	srf.addField("paulo");
        	SelectionField fields = new SelectionField();
        	fields.addField("owner.screenname").addField("title");
        	SortingField sf = new SortingField();
        	sf.addField("language=fr");
        	List<MyVideo> myvideos = client.getVideos(srf, sf, fields, MyVideo.class, 10);
        	System.out.println(myvideos.size());
        	Video myvideo = client.getVideo("x7lni3");
        	System.out.println(myvideo.getTitle());
		} catch (DailymotionResponseProcessingException e) {
			e.printStackTrace();
		}
    }
    
}
