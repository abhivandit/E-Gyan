package easyway2in.com.onlinetest.slidelist;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import easyway2in.com.onlinetest.Config;
import easyway2in.com.onlinetest.PrefManager;
import easyway2in.com.onlinetest.R;

public class AccountFragment extends Fragment implements View.OnClickListener {



    View rootView;
    private TextView tvDetails;
    PrefManager session;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        System.out.println("Class Account Frag");
        rootView = inflater.inflate(R.layout.account_fragment, container, false);
        tvDetails = (TextView) rootView.findViewById(R.id.tvDetails);
        getData();
        return rootView;
    }

    private void getData() {

        System.out.println("Get Data");
        session = new PrefManager(getActivity().getApplicationContext());
        String username =session.getUsername();
        String url = Config.DATA_URL+username.toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("ERROR");
                        Toast.makeText(AccountFragment.this.getActivity(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(AccountFragment.this.getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){

        System.out.println("Show JSON ENTRY");
        String firstname="";
        String lastname="";
        String age="";
        String email="";
        String username = "";
        String password = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            System.out.println("rr"+response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            System.out.println(result);
            JSONObject collegeData = result.getJSONObject(0);
            System.out.println(collegeData);
            firstname = collegeData.getString(Config.KEY_FIRST_NAME);
            lastname = collegeData.getString(Config.KEY_LAST_NAME);
            age = collegeData.getString(Config.KEY_AGE);
            email = collegeData.getString(Config.KEY_EMAIL);
            username = collegeData.getString(Config.KEY_USERNAME);
            password = collegeData.getString(Config.KEY_PASSWORD);
            System.out.println("firstname"+firstname+"lastname"+lastname);
            tvDetails.setText("Name:\t"+firstname+" "+lastname+"\nAge:\t" +age+ "\nEmail id:\t"+ email+ "\nUsername:\t"+username+ "\nPassword:\t"+password);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        getData();
    }
}