package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.User;

import java.util.List;

public class List_all_user extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private int layout;
    private List<User> userList;

    public List_all_user(Context context, int layout, List<com.example.mobiletbcn.model.User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;

    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);

        TextView textFullName = view.findViewById(R.id.fullnameUser);
        TextView textUserName = view.findViewById(R.id.username);
        TextView textRole = view.findViewById(R.id.userorle);
        //TextView textStatus = view.findViewById(R.id.status);

        User user = userList.get(i);
        textFullName.setText(user.getFullName());
        textUserName.setText("username: " + user.getUserName());
        textRole.setText("Role: " + user.getRole());
        return view;
    }

    @Override
    public void onClick(View v) {
    }
}