package easyway2in.com.onlinetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 6/26/2017.
 */

public class ConceptActivity extends AppCompatActivity implements OnTaskCompleted {

    private List<Question> questionsList;
    private Question currentQuestion;
    RadioGroup grp;
    CounterClass timer;
    ArrayList<HashMap<String, Object>> originalValues = new ArrayList<HashMap<String, Object>>();;

    HashMap<Integer, Integer> PreviousAnswer = new HashMap<Integer, Integer>();
    private TextView txtQuestion,tvNoOfQs,times;
    private RadioButton rbtnA, rbtnB, rbtnC,rbtnD;
    private Button btnNext;
    private Button btprevious;
    private int obtainedScore=0;
    private int questionId=0;
    public  Integer KEY_QUES ;
    private int answeredQsNo=0;
    RadioButton answer;
    ArrayList<String> myAnsList;

    String json_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept);

        json_string=getIntent().getExtras().getString("json_data");

        //Initialize the view
        init();

        //Initialize the database
        final DBAdapter dbAdapter=new DBAdapter(this,json_string);
        // dbAdapter.addQuestions(json_string);
        questionsList= dbAdapter.getAllQuestions();
        currentQuestion=questionsList.get(questionId);

        //Set question
        setQuestionsView();
        // the timer
        times = (TextView) findViewById(R.id.timers);
        times.setText("00:02:00");
        timer = new CounterClass(60000, 1000,this);
        timer.start();
        //  getTimer("00:02:00");


        // A timer of 60 seconds to play for, with an interval of 1 second (1000 milliseconds)
        //   CounterClass timer = new CounterClass(60000, 1000);
        //   timer.start();

        //Check Previous Question
        btprevious = (Button) findViewById(R.id.btnPrevious);

        btprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionId > 1){
                    grp=(RadioGroup)findViewById(R.id.radioGroup1);
                    KEY_QUES=currentQuestion.getId();
                    answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                    if(PreviousAnswer.containsKey(questionId)){
                        PreviousAnswer.put(KEY_QUES,grp.getCheckedRadioButtonId());
                        myAnsList.set((questionId-1),""+answer.getText());
                    }
                    int i=questionId-2;
                    currentQuestion=questionsList.get(i);
                    questionId --;
                    setQuestionPrevious();
                    KEY_QUES=currentQuestion.getId();

                    grp.clearCheck();
                    Log.d("score", "question " + KEY_QUES);
                    Log.d("score", "question " + PreviousAnswer.get(KEY_QUES));
                    grp.check(PreviousAnswer.get(KEY_QUES));

                }
                else {
                    btprevious.setEnabled(false);
                    // btprevious.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }

            }
        });
        //Check and Next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grp=(RadioGroup)findViewById(R.id.radioGroup1);
                answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                btprevious.setEnabled(true);
                KEY_QUES=currentQuestion.getId();
                int k= grp.getCheckedRadioButtonId();
                Log.d("score", "question " + KEY_QUES);
                Log.d("score", "answer " + k);


                if (PreviousAnswer.containsKey(questionId)){
                    PreviousAnswer.put(KEY_QUES,grp.getCheckedRadioButtonId());
                    System.out.print(PreviousAnswer);
                    myAnsList.set((questionId-1),""+answer.getText());
                    currentQuestion=questionsList.get(questionId);
                    int SQ=questionId;
                    setQuestionsView();
                    grp.clearCheck();
                    if (PreviousAnswer.containsKey(currentQuestion.getId())){
                        grp.check(PreviousAnswer.get(currentQuestion.getId()));
                    }
                    else{
                        grp.clearCheck();
                    }
                }
                else{
                    Log.e("Answer ID", "Selected Positioned  value - "+grp.getCheckedRadioButtonId());

                    if(answer!=null){

                        Log.e("Answer", currentQuestion.getANSWER() + " -- " + answer.getText());
                        //Add answer to the list
                        PreviousAnswer.put(KEY_QUES,grp.getCheckedRadioButtonId());
                        System.out.print(PreviousAnswer);
                        myAnsList.add(""+answer.getText());

                        if(currentQuestion.getANSWER().equals(answer.getText())){

                            Log.d("correct", "score " + KEY_QUES);
                            Log.e("comments", "Correct Answer");
                            Log.d("score", "Obtained score " + obtainedScore);
                        }else{
                            Log.e("comments", "Wrong Answer");
                        }
                        if(questionId<dbAdapter.rowCount()){
                            currentQuestion=questionsList.get(questionId);
                            setQuestionsView();

                        }else{

                            for (int j = 0; j < questionsList.size(); j++) {
                                currentQuestion=questionsList.get(j);
                                if(currentQuestion.getANSWER().equals(myAnsList.get(j))){
                                    obtainedScore++;
                                }
                            }
                            Intent intent = new Intent(ConceptActivity.this, ResultActivity.class);

                            Bundle b = new Bundle();
                            b.putInt("score", obtainedScore);
                            b.putInt("totalQs", questionsList.size());
                            b.putStringArrayList("myAnsList", myAnsList);
                            intent.putExtras(b);
                            startActivity(intent);
                            finish();

                        }

                    }else{
                        Log.e("comments", "No Answer");
                    }

                    //Need to clear the checked item id
                    grp.clearCheck();


                }//end onClick Method
            }

        });


    }

    public void init(){
        tvNoOfQs=(TextView)findViewById(R.id.tvNumberOfQuestions);
        txtQuestion=(TextView)findViewById(R.id.tvQuestion);
        rbtnA=(RadioButton)findViewById(R.id.radio0);
        rbtnB=(RadioButton)findViewById(R.id.radio1);
        rbtnC=(RadioButton)findViewById(R.id.radio2);
        rbtnD=(RadioButton)findViewById(R.id.radio3);

        btnNext=(Button)findViewById(R.id.btnNext);

        myAnsList = new ArrayList<String>();
    }


    private void setQuestionsView()
    {

        rbtnA.setChecked(false);
        rbtnB.setChecked(false);
        rbtnC.setChecked(false);
        rbtnD.setChecked(false);

        answeredQsNo=questionId+1;
        tvNoOfQs.setText("Questions "+answeredQsNo+" of "+questionsList.size());

        txtQuestion.setText(currentQuestion.getQUESTION());
        rbtnA.setText(currentQuestion.getOptionA());
        rbtnB.setText(currentQuestion.getOptionB());
        rbtnC.setText(currentQuestion.getOptionC());
        rbtnD.setText(currentQuestion.getOptionD());

        questionId++;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getTimer( String time ){
        // if(time.contains("00:02:00")){

        times.setText(time);
        // timer = new CounterClass(60000, 1000);
        //timer.start();
        //}
       /* else {
           Intent intent = new Intent(ConceptActivity.this, ResultActivity.class);

           Bundle b = new Bundle();
           b.putInt("score", obtainedScore);
           b.putInt("totalQs", questionsList.size());
           b.putStringArrayList("myAnsList", myAnsList);
           intent.putExtras(b);
           startActivity(intent);
           finish();*/
        //  }





    }

    public void Quiz_Finish(View view) {
        timer.cancel();

        for (int j = 0; j < questionsList.size(); j++) {
            currentQuestion=questionsList.get(j);
            if (PreviousAnswer.containsKey(currentQuestion.getId()))
            {
                if(currentQuestion.getANSWER().equals(myAnsList.get(j))){
                    obtainedScore++;
                }
            }
            else{
                myAnsList.add(""+"Not Attempted");
            }
        }

        Intent intent = new Intent(ConceptActivity.this, ResultActivity.class);

        Bundle b = new Bundle();
        b.putInt("score", obtainedScore);
        b.putInt("totalQs", questionsList.size());
        b.putStringArrayList("myAnsList", myAnsList);
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }
    public void setQuestionPrevious(){

        answeredQsNo=questionId;
        tvNoOfQs.setText("Questions "+answeredQsNo+" of "+questionsList.size());

        txtQuestion.setText(currentQuestion.getQUESTION());
        rbtnA.setText(currentQuestion.getOptionA());
        rbtnB.setText(currentQuestion.getOptionB());
        rbtnC.setText(currentQuestion.getOptionC());
        rbtnD.setText(currentQuestion.getOptionD());

    }

    @Override
    public void onTaskCompleted(String responseJson) {
        for (int j = 0; j < questionsList.size(); j++) {
            currentQuestion=questionsList.get(j);
            if (PreviousAnswer.containsKey(currentQuestion.getId()))
            {
                if(currentQuestion.getANSWER().equals(myAnsList.get(j))){
                    obtainedScore++;
                }
            }
            else{
                myAnsList.add(""+"Not Attempted");
            }
        }
        timer.cancel();
        Intent intent = new Intent(ConceptActivity.this, ResultActivity.class);
        Bundle b = new Bundle();
        b.putInt("score", obtainedScore);
        b.putInt("totalQs", questionsList.size());
        b.putStringArrayList("myAnsList", myAnsList);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

}