package ru.mail.hw_1.fragment;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class DataSourse {

    private  static final DataSourse ourInstance = new DataSourse();
    private final List<NewElement> list;

    private int[] mColors = new int[] {
            Color.RED,
            Color.BLUE,
    };

    public DataSourse() {
        list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int number = i + 1;
            int color = mColors[number % 2];

            list.add(new NewElement(number, color));
        }
    }

    static  DataSourse getInstance() {
        return ourInstance;
    }

    public  List<NewElement> getData() {
        return list;
    }

    public static  class NewElement {
        private int mNumber;
        private int mColor;

        public NewElement (int number, int color) {
            mNumber = number;
            mColor = color;
        }

        public int getNumber() {
            return mNumber;
        }

        public int getColor () {
            return mColor;
        }
    }
}
