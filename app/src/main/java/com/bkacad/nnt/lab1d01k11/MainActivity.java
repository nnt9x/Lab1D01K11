package com.bkacad.nnt.lab1d01k11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private ListView lvContact;
    private MyAdapter myAdapter;
    private List<Contact> dataContacts;
    private NewContactDialog contactDialog;

    private void initUI(){
        btnAdd = findViewById(R.id.btnAdd);
        lvContact = findViewById(R.id.lvContact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        // Tạo dialog


        // Sự kiện khi click vào button add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sau khi có dialog
                if(contactDialog == null){
                    contactDialog = new NewContactDialog(MainActivity.this) {
                        @Override
                        protected void createContactFromDialog(Contact contact) {
                            dataContacts.add(contact);
                            myAdapter.notifyDataSetChanged();
                            // Gửi dữ liệu lên API đồng bộ....

                            // Thông báo lên notification 
                        }
                    };
                }
                contactDialog.show();
            }
        });

        // Fake dữ liệu
        dataContacts = new ArrayList<>();
        dataContacts.add(new Contact("C1","Contact 1","0918282823","HN"));
        dataContacts.add(new Contact("C2","Contact 2","0918282824","ĐN"));
        dataContacts.add(new Contact("C3","Contact 3","0918282825","TP.HCM"));

        // Tạo Adaptaer
        myAdapter = new MyAdapter(this, dataContacts);

        // Set adapter cho listview
        lvContact.setAdapter(myAdapter);

        // Sự kiện khi nhấn giữ vào item listview
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Cảnh báo")
                        .setMessage("Bạn có muốn xoá liên hệ " + dataContacts.get(position).getName()+"?")
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataContacts.remove(position);
                                myAdapter.notifyDataSetChanged();
                                // tạo request thông qua API để xoá
                            }
                        }).create().show();

                return false;
            }
        });
    }
}