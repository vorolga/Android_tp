package ru.mail.hw_1.fragment;

import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import  java.util.List;

import ru.mail.hw_1.R;

public class ListFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        final List<DataSourse.NewElement> elements = DataSourse.getInstance().getData();

        int numberOfColumns = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), numberOfColumns));

        final MyAdapter adapter = new MyAdapter(elements);
        recyclerView.setAdapter(adapter);

        Button addButton = view.findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addElement();

            }
        });
        return view;
    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private final List<DataSourse.NewElement> elements;

        public MyAdapter(List<DataSourse.NewElement> elements) {
            this.elements = elements;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element, parent, false);
            final Button myButton = view.findViewById(R.id.number);
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = Integer.parseInt(myButton.getText().toString());
                    addNumberFragment(number);
                }
            });
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            DataSourse.NewElement element = elements.get(position);
            holder.mText.setText(Integer.toString(element.getNumber()));
            holder.mText.setTextColor(element.getColor());
        }

        @Override
        public int getItemCount() {
            return elements.size();
        }

        public void addElement() {
            int color;
            int number = elements.size() + 1;
            if (number % 2 == 0) color = Color.RED;
            else color = Color.BLUE;

            elements.add(new DataSourse.NewElement(number, color));
            notifyItemInserted(elements.size() - 1);
        }

        public void addNumberFragment(int number) {
            Bundle bundle = new Bundle();
            bundle.putInt("number", number);
            NumberFragment numberFragment = new NumberFragment();
            numberFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            assert fragmentManager != null;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.numberFragment, numberFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView mText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.number);

        }

    }
}