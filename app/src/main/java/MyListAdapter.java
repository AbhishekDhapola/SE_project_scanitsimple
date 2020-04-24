package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

class Items
{
    public String p_name;
    public String count;
    public String price;
    public Items(String _name, String _price,String _count)
    {
        p_name = _name;
        count = _count;
        price = _price;
    }
}

public class MyListAdapter extends ArrayAdapter<Items> {
    Context _context;
    private ArrayList<Items> objects;
    LayoutInflater inflater;

    public MyListAdapter (Context context,
                                 ArrayList<Items> objects) {
        super(context,0, objects);
        this.objects = objects;
        _context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    static class ViewHolder{
        TextView Product_name;
        TextView Count_t;//name doubt
        TextView txt_ptice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = this.getItemViewType(position);
        Items item = objects.get(position);
        //  convertView = null;
        View rootView = convertView;
        final ViewHolder viewHolder;
        if (rootView == null) {
            viewHolder= new ViewHolder();
            rootView = inflater.inflate(R.layout.customlist, parent, false);
//doubt
            viewHolder.Product_name = (TextView) rootView .findViewById(R.id.idproduct);
            viewHolder.Count_t = (TextView)rootView .findViewById(R.id.idcount);
            viewHolder.txt_ptice = (TextView)rootView .findViewById(R.id.idprice);

            rootView.setTag(viewHolder);

        }
        else
            {
            viewHolder= (ViewHolder) rootView.getTag();
           }



        viewHolder.Product_name.setText(item.p_name);//doubt
        viewHolder.Count_t.setText(item.count);
        viewHolder.txt_ptice.setText(item.price);


        return rootView;
    }

}
