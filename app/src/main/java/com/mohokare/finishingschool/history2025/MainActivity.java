package com.mohokare.finishingschool.history2025;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mohokare.finishingschool.history2025.adapter.FlashcardAdapter;
import com.mohokare.finishingschool.history2025.model.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlashcardAdapter flashcardAdapter;
    private List<Flashcard> flashcardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rv_flashcards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        flashcardList = new ArrayList<>();
        // Sample data
        flashcardList.add(new Flashcard("The Berlin Blockade (1948-1949)"));
        flashcardList.add(new Flashcard("The Berlin Airlift ('Operation Vittles')"));
        flashcardList.add(new Flashcard("Causes of the Berlin Crisis"));
        flashcardList.add(new Flashcard("Consequences of the Berlin Crisis"));
        flashcardList.add(new Flashcard("Key Figures of the Crisis"));

        flashcardAdapter = new FlashcardAdapter(flashcardList);
        recyclerView.setAdapter(flashcardAdapter);

        findViewById(R.id.fab_start_quiz).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, QuizActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_announcements) {
            startActivity(new Intent(this, AnnouncementsActivity.class));
            return true;
        } else if (id == R.id.action_profile) {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_logout) {
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        // Here you would clear any session data
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
