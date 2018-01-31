package easyway2in.com.onlinetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
ImageView imageView;
    TextView txt_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layout);
        imageView=(ImageView)findViewById(R.id.Contact_image);
        txt_description=(TextView) findViewById(R.id.description);
        Glide.with(this).load(getIntent().getStringExtra("img_link")).into(imageView);
        txt_description.setText(getIntent().getStringExtra("description"));
    }
}
