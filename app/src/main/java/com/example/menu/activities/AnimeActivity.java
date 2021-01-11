package com.example.menu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.menu.R;
import com.example.menu.adapters.ProductViewAdapter;
import com.example.menu.ui.ProductList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

//import android.support.design.widget.CollapsingToolbarLayout;

public class AnimeActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    private List<ProductList> products ;
    private RecyclerView recyclerView ;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // hide the default actionbar
//        getSupportActionBar().hide();

        // Recieve data

        String name  = getIntent().getExtras().getString("anime_name");
        String companyName  = getIntent().getExtras().getString("company");
        String address = getIntent().getExtras().getString("address");
        String email = getIntent().getExtras().getString("email") ;
        long telephone = getIntent().getExtras().getLong("telephone");
        int order_id = getIntent().getExtras().getInt("order_id") ;
        int total = getIntent().getExtras().getInt("total") ;
//        String image_url = getIntent().getExtras().getString("anime_img") ;

        // ini views

//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
//        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_cname = findViewById(R.id.aa_cname);
        TextView tv_orderId = findViewById(R.id.aa_order_id);
        TextView tv_total = findViewById(R.id.aa_total) ;
        TextView tv_email = findViewById(R.id.aa_email);
        TextView tv_telephone  = findViewById(R.id.aa_telephone) ;
        TextView tv_address  = findViewById(R.id.aa_address) ;
//        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_name.setText(name);
        tv_cname.setText(companyName);
        tv_address.setText(address);
        tv_email.setText(email);
        tv_orderId.setText(""+order_id);
        tv_total.setText(""+total);
        tv_telephone.setText(""+telephone);

//        setuprecyclerview(products);
        //gmap code
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(AnimeActivity.this);
        products = new ArrayList<>();
        recyclerView = findViewById(R.id.prodRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(AnimeActivity.this));
//        setuprecyclerview(products);
//        collapsingToolbarLayout.setTitle(name);
        Log.d("product", String.valueOf(products));
        btnPay = findViewById(R.id.btnPayment);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimeActivity.this,OrderSubmit.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
//        Glide.with(this).load(image_url).apply(requestOptions).into(img);





    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(27, 57);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private void setuprecyclerview(List<ProductList> products) {

        ProductViewAdapter myadapter = new ProductViewAdapter(AnimeActivity.this,products) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myadapter);

    }
}
