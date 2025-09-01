package com.mohokare.finishingschool.history2025;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mohokare.finishingschool.history2025.adapter.AnnouncementAdapter;
import com.mohokare.finishingschool.history2025.model.Announcement;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnnouncementAdapter announcementAdapter;
    private List<Announcement> announcementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        recyclerView = findViewById(R.id.rv_announcements);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        announcementList = new ArrayList<>();
        // Sample data
        announcementList.add(new Announcement("Welcome!", "Welcome to the History 2025 app."));
        announcementList.add(new Announcement("Quiz Reminder", "Don't forget to take the daily quiz on the Berlin Blockade."));

        announcementAdapter = new AnnouncementAdapter(announcementList);
        recyclerView.setAdapter(announcementAdapter);
    }
}
