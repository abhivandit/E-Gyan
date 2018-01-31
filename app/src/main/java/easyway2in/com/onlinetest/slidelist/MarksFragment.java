package easyway2in.com.onlinetest.slidelist;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import easyway2in.com.onlinetest.R;

/**
 * Created by DELL on 6/21/2017.
 */
@SuppressLint("NewApi")
public class MarksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.marks_fragment, container, false);
        return rootView;
    }
}
