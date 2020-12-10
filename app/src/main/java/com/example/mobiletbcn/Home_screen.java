package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.mobiletbcn.HomeAdapter.FeatureAdapter;
import com.example.mobiletbcn.HomeAdapter.FeatureHelperClass;

import java.util.ArrayList;

public class Home_screen extends AppCompatActivity {
    //Context context = this;
    //ListView listView;
    //List<Book> bookArrayList;
    //ListBookAdapter listBookAdapter;

    RecyclerView feature_recycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //hooks
        feature_recycler = findViewById(R.id.feature_recycler);


        feature_recycler();

    }


    private void feature_recycler() {
        feature_recycler.setHasFixedSize(true);
        feature_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeatureHelperClass> featureLocation = new ArrayList<>();
        featureLocation.add(new FeatureHelperClass(R.drawable.book_cover_1,"Gravity of us"));
        featureLocation.add(new FeatureHelperClass(R.drawable.book_cover_2,"Glory of it all"));
        featureLocation.add(new FeatureHelperClass(R.drawable.book_cover_3,"The crying book"));

        featureLocation.add(new FeatureHelperClass(R.drawable.our_top_picks_cover_1,"Very nice"));
        featureLocation.add(new FeatureHelperClass(R.drawable.our_top_picks_cover_2,"All the things we never said"));
        featureLocation.add(new FeatureHelperClass(R.drawable.our_top_picks_cover_3,"Wilder girl"));

        adapter = new FeatureAdapter(featureLocation);
        feature_recycler.setAdapter(adapter);

        //GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});
    }
}