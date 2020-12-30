package com.example.mobiletbcn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobiletbcn.model.Booking;

import java.util.List;

public class List_Booking extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Booking> bookingList;

    public List_Booking(Context context, int layout, List<com.example.mobiletbcn.model.Booking> bookingList) {
        this.context = context;
        this.layout = layout;
        this.bookingList = bookingList;
    }

    @Override
    public int getCount() {
        return bookingList.size();
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
        //view = layoutInflater.inflate(R.layout.activity_list__booking, null);
        view = layoutInflater.inflate(layout, null);

        TextView textNameBook = view.findViewById(R.id.bnamebook);
        TextView texttypeBook = view.findViewById(R.id.btypebook);
        TextView textstssBook = view.findViewById(R.id.bsttbook);
        ImageView imageView = view.findViewById(R.id.bimage);

        Booking booking = bookingList.get(i);
        textNameBook.setText(booking.getBook().getName());
        texttypeBook.setText(booking.getBook().getType());
        //textstssBook.setText(booking.getBook().getName());

        // chuyển byte từ data sang bitmap và gắn vào imageView
        byte[] image = booking.getBook().getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);
        return view;
    }

}
