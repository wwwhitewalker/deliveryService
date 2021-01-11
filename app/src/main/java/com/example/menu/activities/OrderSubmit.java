package com.example.menu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.menu.R;

public class OrderSubmit extends AppCompatActivity {
    Spinner select;
    Spinner selectStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // hide the default actionbar
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_order_submit);
        select = (Spinner) findViewById(R.id.selectPaymentMode);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(OrderSubmit.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.itemselect));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(myadapter);

        selectStatus = (Spinner) findViewById(R.id.selectProdStatus);
        ArrayAdapter<String> mystatusadapter=new ArrayAdapter<String>(OrderSubmit.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.itemstatus));
        mystatusadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectStatus.setAdapter(mystatusadapter);
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(OrderSubmit.this,AnimeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
////        super.onBackPressed();
//    }
}