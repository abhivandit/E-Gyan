package easyway2in.com.onlinetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by DELL on 6/24/2017.
 */

public class StartScience extends AppCompatActivity implements OnTaskCompleted {

    TextView txtJson;

    public StartScience(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_science);
        final Button bStartEnglish = (Button) findViewById(R.id.bStartScience);
        txtJson = (TextView) findViewById(R.id.txtJSON);

        bStartEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSON();
            }
        });

    }

    public void getJSON(){
        String type="science";
        BackgroundWorker backgroundWorker= new BackgroundWorker(this, this);
        backgroundWorker.execute(type);
    }

    public void onTaskCompleted(String responseJson) {
        txtJson.setText("");
    }

}
