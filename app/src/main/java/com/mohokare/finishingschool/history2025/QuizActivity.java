package com.mohokare.finishingschool.history2025;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mohokare.finishingschool.history2025.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private static final long QUIZ_TIME_IN_MILLIS = 900000; // 15 minutes

    private TextView tvScore, tvTimer, tvQuestion;
    private RadioGroup rgOptions;
    private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    private Button btnNext;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvScore = findViewById(R.id.tv_score);
        tvTimer = findViewById(R.id.tv_timer);
        tvQuestion = findViewById(R.id.tv_question);
        rgOptions = findViewById(R.id.rg_options);
        rbOption1 = findViewById(R.id.rb_option1);
        rbOption2 = findViewById(R.id.rb_option2);
        rbOption3 = findViewById(R.id.rb_option3);
        rbOption4 = findViewById(R.id.rb_option4);
        btnNext = findViewById(R.id.btn_next);

        loadQuestions();
        showNextQuestion();

        timeLeftInMillis = QUIZ_TIME_IN_MILLIS;
        startCountDown();

        btnNext.setOnClickListener(v -> checkAnswer());
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("When did the Berlin Blockade begin?",
                Arrays.asList("June 24, 1948", "May 12, 1949", "October 3, 1990", "August 13, 1961"), 0));
        questionList.add(new Question("What was the main reason for the Soviet Union's blockade of West Berlin?",
                Arrays.asList("To protest the formation of NATO", "To force the Western Allies to abandon their plans for a separate West German state", "To test the resolve of the United States", "To build the Berlin Wall"), 1));
        questionList.add(new Question("What was the Western Allies' response to the Berlin Blockade?",
                Arrays.asList("A military invasion of East Germany", "A diplomatic protest to the United Nations", "The Berlin Airlift", "Economic sanctions against the Soviet Union"), 2));
        questionList.add(new Question("What was the official name of the Berlin Airlift for the Americans?",
                Arrays.asList("Operation Vittles", "Operation Overlord", "Operation Market Garden", "Operation Barbarossa"), 0));
        questionList.add(new Question("Which of these countries did NOT control a sector of Berlin after WWII?",
                Arrays.asList("United States", "United Kingdom", "France", "Japan"), 3));
        questionList.add(new Question("How long did the Berlin Blockade last?",
                Arrays.asList("About 3 months", "About 6 months", "Nearly a year", "Over two years"), 2));
        questionList.add(new Question("The division of Germany and Berlin was decided at which conference?",
                Arrays.asList("The Yalta Conference", "The Tehran Conference", "The Potsdam Conference", "All of the above"), 3));
        questionList.add(new Question("What currency reform in the Western zones of Germany prompted the Soviets to start the blockade?",
                Arrays.asList("Introduction of the Euro", "Introduction of the Deutsche Mark", "Return to the Reichsmark", "Adoption of the US Dollar"), 1));
        questionList.add(new Question("Who was the US President during the Berlin Blockade?",
                Arrays.asList("Franklin D. Roosevelt", "Harry S. Truman", "Dwight D. Eisenhower", "John F. Kennedy"), 1));
        questionList.add(new Question("When did the Berlin Blockade officially end?",
                Arrays.asList("June 24, 1948", "May 12, 1949", "October 3, 1990", "August 13, 1961"), 1));
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            rgOptions.clearCheck();
            Question currentQuestion = questionList.get(currentQuestionIndex);
            tvQuestion.setText(currentQuestion.getQuestionText());
            rbOption1.setText(currentQuestion.getOptions().get(0));
            rbOption2.setText(currentQuestion.getOptions().get(1));
            rbOption3.setText(currentQuestion.getOptions().get(2));
            rbOption4.setText(currentQuestion.getOptions().get(3));
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = rgOptions.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        int selectedAnswerIndex = rgOptions.indexOfChild(selectedRadioButton);

        if (selectedAnswerIndex == questionList.get(currentQuestionIndex).getCorrectAnswerIndex()) {
            score += 3; // 3 marks per question
            tvScore.setText("Score: " + score);
        }

        currentQuestionIndex++;
        showNextQuestion();
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                finishQuiz();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTimer.setText(timeFormatted);
    }

    private void finishQuiz() {
        countDownTimer.cancel();
        new AlertDialog.Builder(this)
                .setTitle("Quiz Finished")
                .setMessage("Your final score is: " + score)
                .setPositiveButton("OK", (dialog, which) -> finish()) // finish activity
                .setCancelable(false)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
