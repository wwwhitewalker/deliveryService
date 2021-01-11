package com.example.menu.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.menu.R;
import com.example.menu.SessionManagment;
import com.example.menu.data.model.LoggedInUser;
import com.example.menu.ui.HistoryList;
import com.example.menu.ui.login.LoginActivity;
import com.example.menu.ui.order.OrderViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView mTextViewResult;
//    private final String JSON_URL = "http://192.168.1.3/saiAgency/upload/index.php?route=api/order/getOrders" ;
    private final String JSON_URL = "https://projects.eyecreative.org/shreeAgency/upload/index.php?route=api/order/getOrders" ;
    private JsonObjectRequest request ;
    private RequestQueue requestQueue ;
    private List<HistoryList> lstAnime ;
    private RecyclerView recyclerView ;
    private LoggedInUser loggedInUser;
    TextView username;
    TextView useremail;
    View hview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        hview = navigationView.getHeaderView(0);

        username = (TextView) hview.findViewById(R.id.user_name);
        useremail = (TextView) hview.findViewById(R.id.user_email);
        SessionManagment sessionManagment = new SessionManagment(this);
        String uname = sessionManagment.getUserName();
        int uid = sessionManagment.getSESSION();
        String uemail = sessionManagment.getUserEmail();
        username.setText(uname);
        useremail.setText(uemail);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_order, R.id.nav_history,R.id.nav_phistory)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    public void logout(){
        SessionManagment sessionManagment = new SessionManagment(MainActivity.this);
        sessionManagment.destroySession();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//    public void openHistoryActivity(){
//        Intent intent = new Intent(this,OrderHistoryActivity.class);
//        startActivity(intent);
//    }
//



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
//        if (id == R.id.nav_order) {
//            Toast.makeText(MainActivity.this,"order list loading ",Toast.LENGTH_SHORT).show();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



//    private void jsonrequest() {
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,JSON_URL,null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray jsonarray = response.getJSONArray("order_infos");
////                     GsonBuilder gsonBuilder = new GsonBuilder();
////                     Gson gson = gsonBuilder.create();
////                     Anime anime = gson.fromJson(String.valueOf(response), Anime.class);
//
//                    for (int i = 0; i < jsonarray.length(); i++) {
//                        JSONObject jsonObject = jsonarray.getJSONObject(i);
//                        HistoryList anime = new HistoryList();
//                        anime.setName(jsonObject.getString("name"));
//                        anime.setEmail(jsonObject.getString("email"));
//                        anime.setOrderId(jsonObject.getInt("order_id"));
//                        anime.setTelephone(jsonObject.getInt("telephone"));
////                            anime.setTotal(jsonObject.getInt("total"));
//                        lstAnime.add(anime);
//
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                setuprecyclerview(lstAnime);
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this,"Something went Wrong ",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        requestQueue = Volley.newRequestQueue(MainActivity.this);
//        requestQueue.add(request) ;
//
//
//    }

//    private void setuprecyclerview(List<HistoryList> lstAnime) {
//
//        HistoryViewAdapter myadapter = new HistoryViewAdapter(this,lstAnime) ;
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(myadapter);
//
//    }

}