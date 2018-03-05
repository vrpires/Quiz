package com.example.android.quiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int countCorrectAnswer = 0;
    int q1_answer = 35;
    String total = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmitQuiz(View view){
        EditText editTextQuestion = (EditText) findViewById(R.id.editText);
        RadioGroup radioGroup2 = (RadioGroup)findViewById(R.id.radioGroupQuestion2);

        CheckBox q3_option1 = (CheckBox) findViewById(R.id.q3_op1);
        CheckBox q3_option2 = (CheckBox) findViewById(R.id.q3_op2);
        CheckBox q3_option3 = (CheckBox) findViewById(R.id.q3_op3);
        CheckBox q3_option4 = (CheckBox) findViewById(R.id.q3_op4);

        RadioGroup radioGroup4 = (RadioGroup)findViewById(R.id.radioGroupQuestion4);
        RadioGroup radioGroup5 = (RadioGroup)findViewById(R.id.radioGroupQuestion5);

        if (Integer.parseInt(editTextQuestion.getText().toString()) == q1_answer) {
            checkAnswerAndSetBackground(editTextQuestion, true);
        } else {
            checkAnswerAndSetBackground(editTextQuestion, false);
        }
        editTextQuestion.setEnabled(false);

        checkRadioButtonSelected(radioGroup2);

        verifyCheckBoxesSelcted(q3_option1,q3_option2,q3_option3,q3_option4);

        checkRadioButtonSelected(radioGroup4);
        checkRadioButtonSelected(radioGroup5);

        answeredAllQuestions();
    }

    public void verifyCheckBoxesSelcted(CheckBox q3_option1, CheckBox q3_option2, CheckBox q3_option3, CheckBox q3_option4){

        if (q3_option1.isChecked() && q3_option3.isChecked() && !q3_option2.isChecked() && !q3_option4.isChecked()) {
            q3_option1.setBackgroundColor(Color.parseColor("#C5E1A5")); // green #C5E1A5
            q3_option3.setBackgroundColor(Color.parseColor("#C5E1A5")); // green #C5E1A5
            countCorrectAnswer++;
        }
        else{
            if(q3_option1.isChecked()){
                q3_option1.setBackgroundColor(Color.parseColor("#C5E1A5")); // red #EF9A9A
            }

            if(q3_option2.isChecked()){
                q3_option2.setBackgroundColor(Color.parseColor("#EF9A9A")); // red #EF9A9A
            }

            if (q3_option3.isChecked()){
                q3_option3.setBackgroundColor(Color.parseColor("#C5E1A5")); // red #EF9A9A
            }

            if (q3_option4.isChecked()){
                q3_option4.setBackgroundColor(Color.parseColor("#EF9A9A")); // red #EF9A9A
            }
        }

        q3_option1.setEnabled(false);
        q3_option2.setEnabled(false);
        q3_option3.setEnabled(false);
        q3_option4.setEnabled(false);
    }

    public void checkAnswerAndSetBackground(View view, boolean correctAnswer){

        if(correctAnswer == true){
            view.setBackgroundColor(Color.parseColor("#C5E1A5")); // green #C5E1A5
            countCorrectAnswer++;
        }
        else {
            view.setBackgroundColor(Color.parseColor("#EF9A9A")); // red #EF9A9A
        }
    }

    public void checkRadioButtonSelected(RadioGroup radioGroupSelct) {

        int radioButtonID = radioGroupSelct.getCheckedRadioButtonId();
        View radioButton = radioGroupSelct.findViewById(radioButtonID);
        int idx = radioGroupSelct.indexOfChild(radioButton);

        RadioButton r = (RadioButton)  radioGroupSelct.getChildAt(idx);
        int idRadioButtonChecked = r.getId();


        switch (idRadioButtonChecked){
            case R.id.q2_op2:
                checkAnswerColorAndDisable(radioButton, R.id.radioGroupQuestion2, true);
                break;
            case R.id.q2_op1:
            case R.id.q2_op3:
            case R.id.q2_op4:
                checkAnswerColorAndDisable(radioButton, R.id.radioGroupQuestion2, false);
                break;
            case R.id.q4_op2:
                checkAnswerColorAndDisable(radioButton, R.id.radioGroupQuestion4, true);
                break;
            case R.id.q4_op1:
            case R.id.q4_op3:
            case R.id.q4_op4:
                checkAnswerColorAndDisable(radioButton, R.id.radioGroupQuestion4, false);
                break;
            case R.id.q5_op1:
                checkAnswerColorAndDisable(radioButton, R.id.radioGroupQuestion5, true);
                break;
            case R.id.q5_op2:
            case R.id.q5_op3:
            case R.id.q5_op4:
                checkAnswerColorAndDisable(radioButton, R.id.radioGroupQuestion5, false);
                break;
        }
    }

    public void checkAnswerColorAndDisable(View view, int idQuest, boolean correctAnswer){

        RadioGroup quest = (RadioGroup) findViewById(idQuest);

        checkAnswerAndSetBackground(view, correctAnswer);

        enabledRadioButton(quest, false);
    }

    public void enabledRadioButton(RadioGroup quest, boolean option){
        for(int i = 0; i < quest.getChildCount(); i++){
            ((RadioButton)quest.getChildAt(i)).setEnabled(option);
            if(option == true){
                ((RadioButton)quest.getChildAt(i)).setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    public void answeredAllQuestions(){

        Button totalPoints = (Button) findViewById(R.id.totalAnswers);
        Button resetQuizButton = (Button) findViewById(R.id.resetButton);
        Button endMathQuiz = (Button) findViewById(R.id.endQuiz);

        total = "Total: " + countCorrectAnswer + "/5";

        if(countCorrectAnswer == 5){
            total+= "\nParabéns!\nVocê acertou tudo!";
        }



        Toast.makeText(this, total, Toast.LENGTH_LONG).show();

        totalPoints.setVisibility(View.VISIBLE);
        endMathQuiz.setVisibility(View.GONE);
        resetQuizButton.setVisibility(View.VISIBLE);

    }

    public void checkTotal(View view){
        Toast.makeText(this, total, Toast.LENGTH_LONG).show();
    }

    public void resetQuiz(View view){
        Button totalPoints = (Button) findViewById(R.id.totalAnswers);
        Button endMathQuiz = (Button) findViewById(R.id.endQuiz);

        EditText editTextQuest = (EditText) findViewById(R.id.editText);
        RadioGroup radioGroup2 = (RadioGroup)findViewById(R.id.radioGroupQuestion2);

        CheckBox q3_option1 = (CheckBox) findViewById(R.id.q3_op1);
        CheckBox q3_option2 = (CheckBox) findViewById(R.id.q3_op2);
        CheckBox q3_option3 = (CheckBox) findViewById(R.id.q3_op3);
        CheckBox q3_option4 = (CheckBox) findViewById(R.id.q3_op4);

        RadioGroup radioGroup4 = (RadioGroup)findViewById(R.id.radioGroupQuestion4);
        RadioGroup radioGroup5 = (RadioGroup)findViewById(R.id.radioGroupQuestion5);

        editTextQuest.getText().clear();
        radioGroup2.clearCheck();

        q3_option1.setChecked(false);
        q3_option2.setChecked(false);
        q3_option3.setChecked(false);
        q3_option4.setChecked(false);

        radioGroup4.clearCheck();
        radioGroup5.clearCheck();

        editTextQuest.setEnabled(true);
        editTextQuest.setBackgroundColor(Color.TRANSPARENT);

        enabledRadioButton(radioGroup2, true);

        q3_option1.setEnabled(true);
        q3_option2.setEnabled(true);
        q3_option3.setEnabled(true);
        q3_option4.setEnabled(true);

        q3_option1.setBackgroundColor(Color.TRANSPARENT);
        q3_option2.setBackgroundColor(Color.TRANSPARENT);
        q3_option3.setBackgroundColor(Color.TRANSPARENT);
        q3_option4.setBackgroundColor(Color.TRANSPARENT);

        enabledRadioButton(radioGroup4, true);
        enabledRadioButton(radioGroup5, true);

        countCorrectAnswer = 0;
        total = "";

        endMathQuiz.setVisibility(View.VISIBLE);
        totalPoints.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
    }
}