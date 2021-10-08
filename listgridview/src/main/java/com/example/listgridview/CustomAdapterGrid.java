package com.example.listgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapterGrid extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    public String[]  Title;
    private int[] imge;

    public CustomAdapterGrid(Context context, String[] text1, int[] imageIds) {
        mContext = context;
        Title = text1;
        imge = imageIds;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Title.length;
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.griditems,null);
        }
        TextView title;
        ImageView i1;
        i1 = (ImageView) convertView.findViewById(R.id.gridimage);
        title = (TextView) convertView.findViewById(R.id.gridtext);
        title.setText(Title[position]);
        i1.setImageResource(imge[position]);
        GridViewApp.ToastMsg=title.toString();
        return convertView;
    }

}
