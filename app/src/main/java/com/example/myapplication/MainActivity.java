package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MyListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner SpinnerItemPosition;
    TextView PositionDisplay;
    int Hold;
    Button btnAdd2Cart;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ABHISHEK
        listView=findViewById(R.id.list_cart);//copy this NEW ACT

        //ArrayList myAdapter = new ArrayList<com.example.myapplication.Items>();
        //myAdapter.add(new com.example.myapplication.Items("Sugar", "75","5"));

        //MyListAdapter adapter = new MyListAdapter(getApplicationContext(), R.layout.customlist, myAdapter);

        //MyListAdapter adapter = new MyListAdapter(getApplicationContext(),  myAdapter);
        //listView.setAdapter(adapter);

        // ON RUN TIME
        /*
        myAdapter.add(new com.example.myapplication.Items("tEST", "0.0","1"));
        //adapter = new MyListAdapter(getApplicationContext(),  myAdapter);
        adapter = (MyListAdapter) listView.getAdapter();
        listView.setAdapter(adapter);
*/
        SpinnerItemPosition = findViewById(R.id.spinner1);
        PositionDisplay = (TextView) findViewById(R.id.textView);

        //ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, number);


        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0; i< master_item.product.length; ++i)
            arrayList.add( master_item.product[i] + " Rs" + master_item.price[i]);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerItemPosition.setAdapter(arrayAdapter);
        /*
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        SpinnerItemPosition.setAdapter(spinnerArrayAdapter);
*/

        /*
        SpinnerItemPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),""+position, Toast.LENGTH_SHORT);
              int Hold = SpinnerItemPosition.getSelectedItemPosition() + 1;
                Toast.makeText(getApplicationContext()," SPINNER Item Selectedx is = " + master_item.product[Hold],Toast.LENGTH_SHORT);
               // PositionDisplay.setText("Item Position is = " + Hold);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            //   @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            //@Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return MainActivity.super.onOptionsItemSelected(item);
            }


        });

         */
        //Toast.makeText(getApplicationContext(),"helo",Toast.LENGTH_SHORT);
        btnAdd2Cart= findViewById(R.id.button);
        btnAdd2Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hold = SpinnerItemPosition.getSelectedItemPosition();

               master_item.selecteItems[Hold]++;
               // PositionDisplay.setText("Item Selectedx is = " + master_item.product[Hold]);

                // ADDING IN LIST VIEW
                ArrayList myAdapter = new ArrayList<com.example.myapplication.Items>();
                MyListAdapter adapter;// = new MyListAdapter(getApplicationContext(),  myAdapter);

                //myAdapter.add(new com.example.myapplication.Items(master_item.product[Hold], master_item.price[Hold],"1"));
                long total=0, item_count=0;
                for (int i=0; i<master_item.selecteItems.length; ++i)
                {
                    if (master_item.selecteItems[i] != 0)
                    {
                        myAdapter.add(new com.example.myapplication.Items(master_item.product[i], master_item.price[i], "" + master_item.selecteItems[i]));
                        total += Integer.parseInt(master_item.price[i]) +  master_item.selecteItems[i];
                        item_count++;
                    }
                }
                adapter = new MyListAdapter(getApplicationContext(),  myAdapter);
                //adapter = (MyListAdapter) listView.getAdapter();
                listView.setAdapter(adapter);

                PositionDisplay.setText("Total Rs"+total+", item count:"+item_count);
            }
        });


    }
}