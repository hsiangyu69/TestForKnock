package com.hsiangyu.testforknock.game;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsiangyu.testforknock.R;
import com.hsiangyu.testforknock.databinding.ActivityGameBinding;

/**
 * Created by hsiangyuchen on 08/08/2017.
 */

public class GameActivity extends AppCompatActivity {
    public static final int MAX_COUNT = 8;
    private ActivityGameBinding binding;
    private ViewTreeObserver viewTreeObserver;

    /* -------------------------- Life cycle */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        // init view
        initView();

    }

    private void initView() {

        // Get device size
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int deviceWidth = displayMetrics.widthPixels;
        // Measure cell width
        final int cellWidth = deviceWidth / MAX_COUNT;


        binding.layoutMain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // layout 1
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(cellWidth, binding.layout1.getMeasuredHeight());
                for (int i = 0; i < MAX_COUNT; i++) {
                    LinearLayout cellLayout = (LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.cell_layout_1, null, false);
                    cellLayout.setLayoutParams(layoutParams);
                    final int itemIndex = i;
                    cellLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "You click " + String.format("%s", itemIndex + 1), Toast.LENGTH_SHORT).show();
                        }
                    });
                    binding.layout1.addView(cellLayout);
                }
                binding.layout1.setDividerDrawable(ContextCompat.getDrawable(
                        getBaseContext(), R.drawable.divider));
                binding.layout1.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

                // layout 2
                LinearLayout.LayoutParams layoutParamsLayout2 = new LinearLayout.LayoutParams(cellWidth, binding.layout2.getMeasuredHeight());
                for (int i = 0; i < MAX_COUNT; i++) {
                    LinearLayout cellLayout = (LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.cell_layout_2, null, false);
                    cellLayout.setLayoutParams(layoutParamsLayout2);
                    final int itemIndex = i;
                    cellLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "You click " + String.format("%s", 2 * itemIndex + 1), Toast.LENGTH_SHORT).show();
                        }
                    });
                    binding.layout2.addView(cellLayout);
                }
                binding.layout2.setDividerDrawable(ContextCompat.getDrawable(
                        getBaseContext(), R.drawable.divider));
                binding.layout2.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);


                // layout 3
                LinearLayout.LayoutParams layoutParamsLayout3 = new LinearLayout.LayoutParams(cellWidth, binding.layout3.getMeasuredHeight());
                for (int i = 0; i < MAX_COUNT; i++) {
                    LinearLayout cellLayout = (LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.cell_layout_3, null, false);
                    cellLayout.setLayoutParams(layoutParamsLayout3);
                    final int itemIndex = i;
                    cellLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "You click " + String.format("%s", 3 * itemIndex + 1), Toast.LENGTH_SHORT).show();
                        }
                    });
                    binding.layout3.addView(cellLayout);
                }
                binding.layout3.setDividerDrawable(ContextCompat.getDrawable(
                        getBaseContext(), R.drawable.divider));
                binding.layout3.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);


                // layout number
                LinearLayout.LayoutParams layoutParamsLayoutNumber = new LinearLayout.LayoutParams(cellWidth, binding.layoutNumber.getMeasuredHeight());
                for (int i = 0; i < MAX_COUNT; i++) {
                    LinearLayout cellLayout = (LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.cell_layout_number, null, false);
                    cellLayout.setLayoutParams(layoutParamsLayoutNumber);
                    final int itemIndex = i;
                    TextView textView = (TextView) cellLayout.findViewById(R.id.textView);
                    textView.setText(String.format("%s", itemIndex + 1));
                    cellLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "You click " + String.format("Number %s", 4 * itemIndex + 1), Toast.LENGTH_SHORT).show();
                        }
                    });
                    binding.layoutNumber.addView(cellLayout);
                }

                // Remove tree observer
                binding.layoutMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


    }
}
