package easyway2in.com.onlinetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfiroj on 5/13/16.
 */
public class DBAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "onlineicttutorQuiz";

    // Table name
    private static final String TABLE_QUESTION = "question";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUESION = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private static final String KEY_OPTD= "optd"; //option d
    private String JSON_String;
    JSONArray jsonArray;
    JSONObject jsonObject;
    private SQLiteDatabase myDatabase;

    public DBAdapter(Context context, String json) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        JSON_String=json;
       // this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        myDatabase=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);

        // Create tables again
        onCreate(db);
    }

    public int rowCount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

    public List<Question> getAllQuestions() {

        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        myDatabase=this.getReadableDatabase();
        if (JSON_String.contains("server_response")){
            addQuestions();
        }

        Cursor cursor = myDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setId(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOptionA(cursor.getString(3));
                quest.setOptionB(cursor.getString(4));
                quest.setOptionC(cursor.getString(5));
                quest.setOptionD(cursor.getString(6));

                quesList.add(quest);

            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public void addQuestions()
    {
        myDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTION + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESION
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC +" TEXT, "+KEY_OPTD+" TEXT)";

        myDatabase.execSQL(sql);

        // JSON_String=json;
        //format is question-option1-option2-option3-option4-answer
        try {
            jsonObject= new JSONObject(JSON_String);
            Log.d("responses",JSON_String);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count =0;
            String ques,optionA,optionB,optionC,optionD,Answer;
            while (count<jsonArray.length()){
                JSONObject JO=jsonArray.getJSONObject(count);
                ques=JO.getString("Ques");
                optionA=JO.getString("OptionA");
                optionB=JO.getString("OptionB");
                optionC=JO.getString("OptionC");
                optionD=JO.getString("OptionD");
                Answer=JO.getString("Answer");
                Question q1=new Question(ques,optionA,optionB,optionC,optionD,Answer);
                this.addQuestion(q1);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    // Adding new question
    public void addQuestion(Question quest) {
//SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESION, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOptionA());
        values.put(KEY_OPTB, quest.getOptionB());
        values.put(KEY_OPTC, quest.getOptionC());
        values.put(KEY_OPTD, quest.getOptionD());

        // Inserting Row
        myDatabase.insert(TABLE_QUESTION, null, values);
    }


}
