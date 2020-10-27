package ru.mail.hw_1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.mail.hw_1.R;

import ru.mail.hw_1.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new ListFragment()).
                        commit();
            }
        }
    }
}