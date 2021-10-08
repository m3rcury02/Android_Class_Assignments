package com.example.listgridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewApp extends AppCompatActivity {
    private ListView mListView;
    private CustomAdapter aAdapter;
    String[] AppList= {"Google Chrome","Facebook","Google Play Store","Messenger","Microsoft Word","Whatsapp",
            "Twitter","Subway Surfers","Spotify","Google Docs","Netflix","Youtube"};
    int[] iconApps={R.drawable.chrome_icon,R.drawable.fb_icon,R.drawable.play_icon,
            R.drawable.messenger_icon,R.drawable.word__icon,R.drawable.whatsapp_icon,R.drawable.twitter_icon,
            R.drawable.subsurf_icon,R.drawable.spotify_icon,R.drawable.office_icon,R.drawable.netflix_icon,
            R.drawable.youtube_icon2};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        mListView=(ListView)findViewById(R.id.appListView);
        aAdapter=new CustomAdapter(this,AppList,iconApps);
        mListView.setAdapter(aAdapter);
    }
    public void ToGridview(View v){
        startActivity(new Intent(this, GridViewApp.class));
    }
    public void TextClick(View v){
        String toastMsg = ((TextView) v).getText().toString();
        Toast.makeText(getApplicationContext(),"Opening "+toastMsg, Toast.LENGTH_SHORT).show();
    }
}
