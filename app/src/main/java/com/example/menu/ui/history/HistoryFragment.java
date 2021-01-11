package com.example.menu.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.menu.R;
import com.example.menu.SessionManagment;
import com.example.menu.adapters.HistoryViewAdapter;
import com.example.menu.adapters.OrderViewAdapter;
import com.example.menu.ui.HistoryList;
import com.example.menu.ui.order.OrderViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment {
//    private final String JSON_URL = "http://192.168.1.5/saiAgency/upload/index.php?route=api/order/getOrders" ;
    private final String JSON_URL = "https://projects.eyecreative.org/shreeAgency/upload/index.php?route=api/order/getOrders" ;
    private JsonObjectRequest request ;
    private RequestQueue requestQueue ;
    private List<HistoryList> lstAnime ;
    private RecyclerView recyclerView ;
    private HistoryViewModel historyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.order_main_layout, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        historyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        lstAnime = new ArrayList<>() ;
        recyclerView = root.findViewById(R.id.recyclerviewid);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        jsonrequest();

        return root;
    }

    private void jsonrequest() {
        SessionManagment sessionManagment = new SessionManagment(requireActivity());
        String userId = String.valueOf(sessionManagment.getUserId());
//        JSONObject object = new JSONObject();
//        try {
//            //input your API parameters
//            object.put("userId",userId);
////            object.put("parameter","value");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId",userId);
        String reqURL = JSON_URL+"&userId="+userId;
        JSONObject jsonBody = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,reqURL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("result", String.valueOf(response));
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
                        anime.setTelephone(jsonObject.getLong("telephone"));
                        anime.setTotal(jsonObject.getInt("total"));
                        anime.setCompanyName(jsonObject.getString("company"));
                        anime.setAddress(jsonObject.getString("address"));
                        anime.setStatus(jsonObject.getString("status"));
                        anime.setDdate(jsonObject.getString("delivery_date"));
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
                Log.d("error", ""+error);
                Toast.makeText(requireActivity(),"Something went Wrong "+error,Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(request) ;


    }
    private void setuprecyclerview(List<HistoryList> lstAnime) {

        HistoryViewAdapter myadapter = new HistoryViewAdapter(requireActivity(),lstAnime) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myadapter);

    }
}