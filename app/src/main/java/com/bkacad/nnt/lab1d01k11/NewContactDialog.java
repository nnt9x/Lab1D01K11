package com.bkacad.nnt.lab1d01k11;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;


public abstract class NewContactDialog extends Dialog {

    private EditText edtName, edtPhone, edtAddress;
    private Button btnSave, btnCancel;

    // Hàm dùng để callback
    protected abstract void createContactFromDialog(Contact contact);

    protected NewContactDialog(@NonNull Context context) {
        super(context);
    }

    private void initUI(){
        edtName = findViewById(R.id.edt_dialog_name);
        edtPhone = findViewById(R.id.edt_dialog_phone);
        edtAddress = findViewById(R.id.edt_dialog_address);
        btnSave = findViewById(R.id.btn_dialog_save);
        btnCancel = findViewById(R.id.btn_dialog_cancel);
    }

    @Override
    public void show() {
        super.show();
        edtName.setText("");
        edtAddress.setText("");
        edtPhone.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_contact);
        setCancelable(false);
        initUI();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy dữ liệu từ Edit -> check đã hợp lệ hay chưa -> Main
                String name = edtName.getText().toString();
                if(name.isEmpty()){
                    edtName.setError("Hãy nhập họ tên");
                    return;
                }
                String phone = edtPhone.getText().toString();
                if(phone.isEmpty()){
                    edtPhone.setError("Hãy nhập số điện thoại");
                    return;
                }
                String address = edtAddress.getText().toString();
                if(address.isEmpty()){
                    edtAddress.setError("Hãy nhập địa chỉ");
                    return;
                }
                // Có thể validate bằng Regex (trong java tự tra)
                // Gửi dữ liệu về nới tạo ra nó để xử lý
                Contact contact = new Contact();
                contact.setPhone(phone);
                contact.setName(name);
                contact.setAddress(address);
                createContactFromDialog(contact);
                // Ẩn dialog
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }




}
