package easyway2in.com.onlinetest.slidelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import easyway2in.com.onlinetest.PrefManager;
import easyway2in.com.onlinetest.R;

/**
 * Created by DELL on 6/24/2017.
 */

public class HomeFragment extends Fragment {

    TextView UsernameHF;
    PrefManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        session = new PrefManager(getActivity().getApplicationContext());
        UsernameHF = (TextView) rootView.findViewById(R.id.tvUsernameHF);
        String username =session.getUsername();
        UsernameHF.setText(username);
        return rootView;
    }
}