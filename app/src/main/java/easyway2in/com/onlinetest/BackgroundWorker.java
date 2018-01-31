package easyway2in.com.onlinetest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by DELL on 6/13/2017.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    public OnTaskCompleted listener;
    Context context;
    AlertDialog alertDialog;
    Activity activity;
    String type;
    ProgressDialog progDialog;

    public BackgroundWorker(Context context, OnTaskCompleted listener){
        this.context = context;
        this.listener = listener;
        activity = (Activity)context;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String JSON_url_English="http://192.168.56.1/App/JSON_Get_Data_English.php";
        String JSON_url_Mathematics="http://192.168.56.1/App/JSON_Get_Data_Mathematics.php";
        String JSON_url_Science="http://192.168.56.1/App/JSON_Get_Data_Science.php";
        String login_url = "http://192.168.56.1/App/Login.php";
        String reg_url = "http://192.168.56.1/App/Register.php";
        if (type.equals("Login")){
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("Register")){
                String firstname = params[1];
                String lastname = params[2];
                String age = params[3];
                String email = params [4];
                String username = params[5];
                String password = params[6];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("firstname", "UTF-8")+"="+URLEncoder.encode(firstname, "UTF-8")+"&"
                        +URLEncoder.encode("lastname", "UTF-8")+"="+URLEncoder.encode(lastname, "UTF-8")+"&"
                        +URLEncoder.encode("age", "UTF-8")+"="+URLEncoder.encode(age, "UTF-8")+"&"
                        +URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        +URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                */inputStream.close();
                httpURLConnection.disconnect();
                return "Registration Successful!";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("english")){
            try {
                URL url= new URL(JSON_url_English);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder =new StringBuilder();
                String JSON_String="";
                String line="";
                while ((JSON_String=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_String+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        } else if (type.equals("mathematics")){
            try {
                URL url= new URL(JSON_url_Mathematics);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder =new StringBuilder();
                String JSON_String="";
                String line="";
                while ((JSON_String=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_String+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("science")){
            try {
                URL url= new URL(JSON_url_Science);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder =new StringBuilder();
                String JSON_String="";
                String line="";
                while ((JSON_String=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_String+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        progDialog = new ProgressDialog(activity);
        progDialog.setMessage("Loading. Please Wait.");
        progDialog.setIndeterminate(false);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setCancelable(false);
        //progDialog.setCancelable(false);
        progDialog.setCanceledOnTouchOutside(false);
        progDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Registration Successful!")){
            alertDialog.setMessage(result);
            alertDialog.show();
            Toast.makeText(context, result, Toast.LENGTH_SHORT);
        }
        else if(result.contains("Login successful. Welcome ")){
            alertDialog.setMessage(result);
            alertDialog.show();
            alertDialog.setMessage(result);
            alertDialog.show();
            Intent conceptIntent=new Intent(context,LoginSuccess.class);
            context.startActivity(conceptIntent);
        } else if (result.contains("server_response")){
            listener.onTaskCompleted(result);
            Intent conceptIntent=new Intent(context,ConceptActivity.class);
            conceptIntent.putExtra("json_data", result);
            context.startActivity(conceptIntent);
            progDialog.dismiss();
        }
        else if(result.contains("current_affairs")){
            //recyclerView=(RecyclerView)activity.findViewById(R.id.recycler_view);
            // data_list= new ArrayList<>();
            // gridLayoutManager= new GridLayoutManager(activity,2);
            //  recyclerView.setLayoutManager(gridLayoutManager);
            //adapter= new CustomAdapter(activity,data_list);

            //recyclerView.setAdapter(adapter);
            // adapter.notifyDataSetChanged();
            // listener.onTaskCompleted(result);

        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
