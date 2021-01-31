package com.lcmf.mmgo.recyclemenu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcmf.mmgo.R;

import java.util.ArrayList;
import java.util.List;

public class HolyTsMainActivity extends AppCompatActivity {

    private List<HolyMenuItem> menuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        initMenus();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HolyMenuAdapter adapter = new HolyMenuAdapter(menuItems);
        recyclerView.setAdapter(adapter);
    }

    private void initMenus() {
        for (int i = 0; i < 1; i++) {
            HolyMenuItem apple = new HolyMenuItem("Apple", R.drawable.draw_btn_circle_blue);
            menuItems.add(apple);
            HolyMenuItem banana = new HolyMenuItem("Banana", R.drawable.draw_btn_circle_blue);
            menuItems.add(banana);
            HolyMenuItem orange = new HolyMenuItem("Orange", R.drawable.draw_btn_circle_blue);
            menuItems.add(orange);
        }
    }
}