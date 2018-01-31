package easyway2in.com.onlinetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by DELL on 6/24/2017.
 */

public class StartEnglish extends AppCompatActivity implements OnTaskCompleted {

    TextView txtJson;
    Button bStartEnglish;

    public StartEnglish(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_english);
        bStartEnglish = (Button) findViewById(R.id.bStartEnglish);
        txtJson = (TextView) findViewById(R.id.txtJSON);

        bStartEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSON();
            }
        });

    }

    public void getJSON(){
        String type="english";
        BackgroundWorker backgroundWorker= new BackgroundWorker(this, this);
        backgroundWorker.execute(type);
    }

    public void onTaskCompleted(String responseJson) {
        txtJson.setText("");
    }

}
