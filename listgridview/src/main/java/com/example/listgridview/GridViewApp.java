package com.example.listgridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GridViewApp extends AppCompatActivity {
    private GridView mGridView;
    private CustomAdapterGrid aAdapter;
    public static String ToastMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        String[] ActListApps= {"Google Chrome","Facebook","Google Play Store","Messenger","Microsoft Word","Whatsapp",
                "Twitter","Subway Surfers","Spotify","Google Docs","Netflix","Youtube"};
        int[] IconsIds={R.drawable.chrome_icon,R.drawable.fb_icon,R.drawable.play_icon,
                R.drawable.messenger_icon,R.drawable.word__icon,R.drawable.whatsapp_icon,R.drawable.twitter_icon,
                R.drawable.subsurf_icon,R.drawable.spotify_icon,R.drawable.office_icon,R.drawable.netflix_icon,
                R.drawable.youtube_icon2};
        mGridView=(GridView)findViewById(R.id.appGridView);
        aAdapter=new CustomAdapterGrid(this,ActListApps,IconsIds);
        mGridView.setAdapter(aAdapter);

    }
    public void ToListview(View v){
        startActivity(new Intent(this, ListViewApp.class));
    }

    public void TextClick(View v){
        String toastMsg = ((TextView) v).getText().toString();
        Toast.makeText(getApplicationContext(),"Opening "+toastMsg, Toast.LENGTH_SHORT).show();
    }
}
