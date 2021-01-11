package  com.example.menu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.menu.R;
import com.example.menu.SessionManagment;
import com.example.menu.activities.MainActivity;
import com.example.menu.data.model.LoggedInUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText,passwordEditText;
    private String username,password;
    private ProgressBar loadingProgressBar;
//    private String JSON_URL = "http://192.168.1.5/saiAgency/upload/index.php?route=api/login/login" ;
    private final String JSON_URL = "https://projects.eyecreative.org/shreeAgency/upload/index.php?route=api/login/login" ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
//        loadingProgressBar = findViewById(R.id.loading);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
                    login(v);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        SessionManagment sessionManagment = new SessionManagment(LoginActivity.this);
        int userId = sessionManagment.getSESSION();
        if(userId != -1){
            moveToMainActivity();
        }else{
            // do nothing
        }
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void login(View view) {
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        if(!username.equals("") &&  !password.equals("")){

            Map<String, String> params = new HashMap<String, String>();
            params.put("username",username);
            params.put("password",password);
            JSONObject jsonBody = new JSONObject(params);
            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST,JSON_URL,jsonBody, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    Log.d("result", String.valueOf(response));
//                    JSONObject mainObject = new JSONObject((HashMap) response);
                    try {
                        String responseStatus = response.getString("status");
                        JSONObject responseUser = response.getJSONObject("user_data");
                        if (String.valueOf(responseStatus).equals("success")) {
                            String uname = responseUser.getString("firstname")+" "+responseUser.getString("lastname");
                            String uemail = responseUser.getString("email");
                            int uid = responseUser.getInt("user_id");
                            LoggedInUser user = new LoggedInUser(uid,uname,uemail);
                            SessionManagment sessionManagment = new SessionManagment(LoginActivity.this);
                            sessionManagment.saveSession(user);

//                            loadingProgressBar.setVisibility(View.INVISIBLE);
                            moveToMainActivity();

                            finish();
                        } else{
                            Toast.makeText(LoginActivity.this,
                                    "invalid Login id and password ",
                                    Toast.LENGTH_SHORT).show();
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(),Toast.LENGTH_SHORT);
//                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }
            });
//            {
//                protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> data = new HashMap<>();
//                data.put("username", username);
//                data.put("password",password);
//                return data;
//            }
//            }
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(jsonObjRequest);

        }

    }


}