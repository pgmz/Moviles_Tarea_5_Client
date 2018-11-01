package com.iteso.itesoclient;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    static final String PROVIDER_NAME = "com.iteso.pdm18_scrollabletabs.tools.ItemProductsContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/";

    ContentResolver cr;

    RecyclerView recyclerView;
    ArrayList<String> products;
    AdapterProduct adapterProduct;

    Integer currentSpinnerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.layout_recycler);
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        cr = getContentResolver();
        loadListOfCategory(0);
    }

    public void loadListOfCategory(int Category){
        Bundle bundle = new Bundle();
        bundle.putInt("CATEGORY", Category);
        Bundle result = cr.call(Uri.parse(URL), "getItemProductsByCategory", null, bundle);
        products = result.getStringArrayList("ITEMS");
        adapterProduct = new AdapterProduct(this, products);
        recyclerView.setAdapter(adapterProduct);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh){
            loadListOfCategory(currentSpinnerPosition);
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem spinnerMenuItem = menu.findItem( R.id.menu_spinner);
        View view = spinnerMenuItem.getActionView();
        if (view instanceof Spinner)
        {

            ArrayList<String> categoryNames = new ArrayList<>();
            categoryNames.add("Technology");
            categoryNames.add("Home");
            categoryNames.add("Electronics");

            final Spinner spinner = (Spinner) view;
            spinner.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    categoryNames));


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    currentSpinnerPosition = arg2;
                    loadListOfCategory(arg2);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });

        }
        return true;
    }
}
