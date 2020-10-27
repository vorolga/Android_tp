package ru.mail.hw_1.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.mail.hw_1.R;


public class NumberFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.number_fragment, container, false);
        TextView textView = view.findViewById(R.id.number);
        Bundle bundle = this.getArguments();
        textView.setText(Integer.toString(bundle.getInt("number")));

        if (bundle.getInt("number") % 2 == 0)  textView.setTextColor(Color.RED);
        else textView.setTextColor(Color.BLUE);

        return view;
    }


}