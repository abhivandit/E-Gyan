package easyway2in.com.onlinetest.slidelist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import easyway2in.com.onlinetest.R;
import easyway2in.com.onlinetest.StartEnglish;
import easyway2in.com.onlinetest.StartMathematics;
import easyway2in.com.onlinetest.StartScience;

/**
 * Created by DELL on 6/21/2017.
 */
@SuppressLint("NewApi")
public class TestsFragment extends Fragment/* implements OnTaskCompleted */{

    TextView txtJson;
    View rootView;

    public TestsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.tests_fragment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button bEnglish = (Button) view.findViewById(R.id.bEnglish);
        final Button bMathematics = (Button) view.findViewById(R.id.bMathematics);
        final Button bScience = (Button) view.findViewById(R.id.bScience);

        /*bEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent englishIntent = new Intent(TestsFragment.this.getActivity(), StartEnglish.class);
                TestsFragment.this.startActivity(englishIntent);
                getJSON();
            }
        });

        bMathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mathematicsIntent = new Intent(TestsFragment.this.getActivity(), StartMathematics.class);
                TestsFragment.this.startActivity(mathematicsIntent);
                getJSON();
            }
        });

        bScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scienceIntent = new Intent(TestsFragment.this.getActivity(), StartScience.class);
                TestsFragment.this.startActivity(scienceIntent);
                getJSON();
            }
        });*/

        bEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent englishIntent = new Intent(TestsFragment.this.getActivity(), StartEnglish.class);
                TestsFragment.this.startActivity(englishIntent);
            }
        });

        bMathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mathematicsIntent = new Intent(TestsFragment.this.getActivity(), StartMathematics.class);
                TestsFragment.this.startActivity(mathematicsIntent);
            }
        });

        bScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scienceIntent = new Intent(TestsFragment.this.getActivity(), StartScience.class);
                TestsFragment.this.startActivity(scienceIntent);
            }
        });
    }
/*
    public void getJSON(){
        String type="JSON";
        BackgroundWorker backgroundWorker= new BackgroundWorker(getActivity(), this);
        backgroundWorker.execute(type);
    }

    public void onTaskCompleted(String responseJson) {
        txtJson.setText(responseJson);
    }*/
}
