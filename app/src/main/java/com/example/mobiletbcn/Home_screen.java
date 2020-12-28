package com.example.mobiletbcn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.HomeAdapter.FeatureAdapter;
import com.example.mobiletbcn.HomeAdapter.FeatureHelperClass;
import com.example.mobiletbcn.model.KeepInformation;
import com.example.mobiletbcn.model.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home_screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    //ListView listView;
    //List<Book> bookArrayList;
    //ListBookAdapter listBookAdapter;

    Database database;
    UserController userController;
    private User user;
    //private int idus;

    RecyclerView feature_recycler;
    RecyclerView.Adapter adapter;
    ImageView navic;
    TextView nameOfUser;


    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //dialog
    Dialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        userController = new UserController(database);

        //hooks
        feature_recycler = findViewById(R.id.feature_recycler);
        navic = findViewById(R.id.nav_ic);

        //nav hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        if (KeepInformation.getRole().equals("admin")) {
            navigationView.getMenu().findItem(R.id.mangae_gr).setVisible(true);
        } else {
            navigationView.getMenu().findItem(R.id.mangae_gr).setVisible(false);
        }

        //navigation view function calls
        navigationDrawer();

        //recycler view function calls
        feature_recycler();

    }

    private void navigationDrawer() {
        //navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);



        navic.setOnClickListener(new View.OnClickListener() {
            private  int idus;
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                    /*Bundle extras = getIntent().getExtras();
                    String name = extras.getString("name",KeepInformation.getFullnameUser());
                    nameOfUser = findViewById(R.id.nameofuser);
                    nameOfUser.setText("Hello " + name);*/


                    Bundle extras = getIntent().getExtras();
                    int idus = extras.getInt("name");

                    user = userController.findUserById(idus);
                    this.idus = user.getId();
                    User usern = userController.findUserById(idus);
                    nameOfUser = findViewById(R.id.nameofuser);
                    nameOfUser.setText("Hello " + usern.getFullName());
                }
            }
        });
    }


    private void feature_recycler() {
        feature_recycler.setHasFixedSize(true);
        feature_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeatureHelperClass> featureLocation = new ArrayList<>();
        featureLocation.add(new FeatureHelperClass(R.drawable.book_cover_1, "Gravity of us"));
        featureLocation.add(new FeatureHelperClass(R.drawable.book_cover_2, "Glory of it all"));
        featureLocation.add(new FeatureHelperClass(R.drawable.book_cover_3, "The crying book"));

        featureLocation.add(new FeatureHelperClass(R.drawable.our_top_picks_cover_1, "Very nice"));
        featureLocation.add(new FeatureHelperClass(R.drawable.our_top_picks_cover_3, "Wilder girl"));

        adapter = new FeatureAdapter(featureLocation);
        feature_recycler.setAdapter(adapter);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final Intent intent;
        if (KeepInformation.getRole().equals("admin")) {
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    intent = new Intent(this, Profile.class);
                    intent.putExtra("user",KeepInformation.getIdUser());
                    startActivity(intent);
                    break;

                case R.id.nav_addbook:
                    intent = new Intent(this, Add_Book.class);
                    startActivity(intent);
                    break;

                case R.id.nav_search:
                    intent = new Intent(this, Search_Book.class);
                    startActivity(intent);
                    break;

                case R.id.nav_editrole:
                    //intent = new Intent(this, HomeListBook.class);
                    //startActivity(intent);
                    break;

                case R.id.nav_list:
                    intent = new Intent(this, ListUser_frame.class);
                    startActivity(intent);
                    break;

                case R.id.nav_language:
                    AlertDialog.Builder mydialog_lang=new
                            AlertDialog.Builder(context);
                    mydialog_lang.setTitle("We are updating....");
                    mydialog_lang.setIcon(R.mipmap.ic_launcher);
                    mydialog_lang.setNegativeButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // TODO Auto-generated method stub
                            dialog.cancel(); //dong dialog
                        }
                    });
                    AlertDialog alerthongbao = mydialog_lang.create();
                    alerthongbao.show();
                    break;

                case R.id.nav_logout:
                    //KeepInformation.setIdUser(0);
                    KeepInformation.setRole("ADMIN");

                    intent = new Intent(this, MainActivity.class);
                    AlertDialog.Builder mydialog=new
                            AlertDialog.Builder(context);
                    mydialog.setTitle("Are you sure want to logout?");
                    mydialog.setIcon(R.mipmap.ic_launcher);
                    mydialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            startActivity(intent);
                        }
                    });
                    mydialog.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // TODO Auto-generated method stub
                            dialog.cancel(); //dong dialog
                        }
                    });
                    AlertDialog alerthoitham=mydialog.create();
                    alerthoitham.show();
                    break;
            }
        } else {
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    intent = new Intent(this, Profile.class);
                    intent.putExtra("user",KeepInformation.getIdUser());
                    startActivity(intent);
                    break;

                case R.id.listBookUser:
                    //intent = new Intent(this, HomeListBook.class);
                    //startActivity(intent);
                    break;

                case R.id.searchBookUser:
                    //intent = new Intent(this, SearchBook.class);
                    //startActivity(intent);
                    break;

                case R.id.nav_language:
                    AlertDialog.Builder mydialog_lang = new AlertDialog.Builder(context);
                    mydialog_lang.setTitle("We are updating....");
                    mydialog_lang.setIcon(R.mipmap.ic_launcher);
                    mydialog_lang.setNegativeButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // TODO Auto-generated method stub
                            dialog.cancel(); //dong dialog
                        }
                    });
                    AlertDialog alerthongbao = mydialog_lang.create();
                    alerthongbao.show();
                    break;

                case R.id.nav_Policy:
                    mydialog = new Dialog(this);
                    mydialog.setContentView(R.layout.custumpopup);
                    mydialog.show();

                    //mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));*/
                    mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    break;

                case R.id.nav_logout:
                    //KeepInformation.setIdUser(0);
                    //KeepInformation.setRole("");

                    intent = new Intent(this, MainActivity.class);
                    AlertDialog.Builder mydialog = new AlertDialog.Builder(context);
                    mydialog.setTitle("Are you sure want to logout?");
                    mydialog.setIcon(R.mipmap.ic_launcher);
                    mydialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            startActivity(intent);
                        }
                    });
                    mydialog.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // TODO Auto-generated method stub
                            dialog.cancel(); //dong dialog
                        }
                    });

                    AlertDialog alerthoitham = mydialog.create();
                    alerthoitham.show();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowAll(View view) {
        Intent intent = new Intent(this,ListBook_frame.class);
        startActivity(intent);
    }

    public void Dimiss(View view) {
        mydialog.dismiss();
    }

    public static void buttonUpdateUser() {
    }

}



