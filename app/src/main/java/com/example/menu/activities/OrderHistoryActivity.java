package com.example.menu.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.menu.R;
import com.example.menu.adapters.OrderViewAdapter;
import com.example.menu.ui.HistoryList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {
//    private final String JSON_URL = "http://192.168.1.3/saiAgency/upload/index.php?route=api/order/getOrders" ;
    private final String JSON_URL = "https://projects.eyecreative.org/shreeAgency/upload/index.php?route=api/order/getOrders" ;
    private JsonObjectRequest request ;
    private RequestQueue requestQueue ;
    private List<HistoryList> lstAnime ;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        lstAnime = new ArrayList<>() ;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jsonrequest();
    }
    private void jsonrequest() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,JSON_URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonarray = response.getJSONArray("order_infos");
//                     GsonBuilder gsonBuilder = new GsonBuilder();
//                     Gson gson = gsonBuilder.create();
//                     Anime anime = gson.fromJson(String.valueOf(response), Anime.class);

                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonObject = jsonarray.getJSONObject(i);
                        HistoryList anime = new HistoryList();
                        anime.setName(jsonObject.getString("name"));
                        anime.setEmail(jsonObject.getString("email"));
                        anime.setOrderId(jsonObject.getInt("order_id"));
                        anime.setTelephone(jsonObject.getInt("telephone"));
//                            anime.setTotal(jsonObject.getInt("total"));
                        lstAnime.add(anime);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setuprecyclerview(lstAnime);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderHistoryActivity.this,"Something went Wrong ",Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue = Volley.newRequestQueue(OrderHistoryActivity.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<HistoryList> lstAnime) {

        OrderViewAdapter myadapter = new OrderViewAdapter(this,lstAnime) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myadapter);



    }
}