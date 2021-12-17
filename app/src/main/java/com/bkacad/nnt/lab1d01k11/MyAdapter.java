package com.bkacad.nnt.lab1d01k11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> data;

    public MyAdapter(Context context, List<Contact> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        }
        // Bind view
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);
        TextView tvName= convertView.findViewById(R.id.tvName);
        ImageView imgMenu = convertView.findViewById(R.id.imgMenu);

        // Đổ dữ liệu vào view
        Contact contact = data.get(position);
        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhone());

        // Tạo popup menu
        PopupMenu popupMenu = new PopupMenu(context, imgMenu);
        popupMenu.getMenuInflater().inflate(R.menu.item_list_menu, popupMenu.getMenu());

        // Xử lý sự kiện khi click vào từng item menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_list_menu_details:
                        // CHuyển sang Details Activity để xem chi tiết
                        break;
                    case R.id.item_list_menu_detete:
                        // Xoá và đưa thông báo
                        data.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Đã xoá", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_list_menu_edit:
                        // Hiển thi dialog lên
                        break;
                }
                return false;
            }
        });

        // Sự kiện khi click vào menu
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sau khi có popup menu
                popupMenu.show();
            }
        });

        return convertView;
    }
}
