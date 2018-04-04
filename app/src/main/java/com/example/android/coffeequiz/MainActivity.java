package com.example.android.coffeequiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Grade the user's answers and display the quiz results
    public void gradeQuiz(View view) {

        String frenchPressGrindUserAnswer = "";
        String frenchPressGrindCorrectAnswer = getString(R.string.coarseGrindOption);
        String frenchPressTempUserAnswer = "";
        String frenchPressTempCorrectAnswer = getString(R.string.frenchPressTempAnswer);
        String espressoGrindUserAnswer = "";
        String espressoGrindCorrectAnswer = getString(R.string.fineGrindOption);
        String coffeeSpeciesGrindUserAnswer = "";
        String coffeeSpeciesGrindCorrectAnswer = getString(R.string.arabicaOption);
        int score = 0;
        String wrongAnswerMessage = "";

        //Get the user answer for the proper french press grind size
        RadioGroup frenchPressGrindGroup = (RadioGroup) findViewById(R.id.french_press_grind_radioGroup);
        int selectedFrenchPressGrindRadioButtonID = frenchPressGrindGroup.getCheckedRadioButtonId();

        if (selectedFrenchPressGrindRadioButtonID != -1) {
            RadioButton selectedFrenchPressGrindRadioButton = (RadioButton) findViewById(selectedFrenchPressGrindRadioButtonID);
            frenchPressGrindUserAnswer = selectedFrenchPressGrindRadioButton.getText().toString();

            //Compare the user's answer to the correct answer
            //Add to the score if the answer is correct
            //Create a message with the correct answer if the user answers incorrectly
            if (frenchPressGrindUserAnswer.equals(frenchPressGrindCorrectAnswer)) {
                score += 1;
            }
            else {
                wrongAnswerMessage = getString(R.string.frenchPressGrindWrongMessage,
                        frenchPressGrindCorrectAnswer,frenchPressGrindUserAnswer);
            }

        }

        //Get the user answer for the proper french press brew temperature
        EditText frenchPressTempField = (EditText) findViewById(R.id.french_press_temp_answer);
        frenchPressTempUserAnswer = frenchPressTempField.getText().toString();

        //Compare the user's answer to the correct answer
        //Add to the score if the answer is correct
        //Create a message with the correct answer if the user answers incorrectly
        if (frenchPressTempUserAnswer.equals(frenchPressTempCorrectAnswer)) {
            score += 1;
        }
        else {
            wrongAnswerMessage += "\n\n" + getString(R.string.frenchPressTempWrongMessage,
                    frenchPressTempCorrectAnswer, frenchPressTempUserAnswer);
        }

        //Get the user answer for the proper espresso grind size
        RadioGroup espressoGrindGroup = (RadioGroup) findViewById(R.id.espresso_grind_radioGroup);
        int selectedEspressoGrindRadioButtonID = espressoGrindGroup.getCheckedRadioButtonId();

        if (selectedEspressoGrindRadioButtonID != -1) {
            RadioButton selectedEspressoGrindRadioButton = (RadioButton) findViewById(selectedEspressoGrindRadioButtonID);
            espressoGrindUserAnswer = selectedEspressoGrindRadioButton.getText().toString();

            //Compare the user's answer to the correct answer
            //Add to the score if the answer is correct
            //Create a message with the correct answer if the user answers incorrectly
            if (espressoGrindUserAnswer.equals(espressoGrindCorrectAnswer)) {
                score += 1;
            }
            else {
                wrongAnswerMessage += "\n\n" + getString(R.string.espressoGrindWrongMessage,
                        espressoGrindCorrectAnswer,espressoGrindUserAnswer);
            }

        }

        //Get the user answer for the coffee species
        RadioGroup coffeeSpeciesGrindGroup = (RadioGroup) findViewById(R.id.coffee_species_radioGroup);
        int selectedCoffeeSpeciesGrindRadioButtonID = coffeeSpeciesGrindGroup.getCheckedRadioButtonId();

        if (selectedCoffeeSpeciesGrindRadioButtonID != -1) {
            RadioButton selectedCoffeeSpeciesGrindRadioButton = (RadioButton) findViewById(selectedCoffeeSpeciesGrindRadioButtonID);
            coffeeSpeciesGrindUserAnswer = selectedCoffeeSpeciesGrindRadioButton.getText().toString();

            //Compare the user's answer to the correct answer
            //Add to the score if the answer is correct
            //Create a message with the correct answer if the user answers incorrectly
            if (coffeeSpeciesGrindUserAnswer.equals(coffeeSpeciesGrindCorrectAnswer)) {
                score += 1;
            }
            else{
                wrongAnswerMessage += "\n\n" + getString(R.string.coffeeSpeciesWrongMessage,
                        coffeeSpeciesGrindCorrectAnswer,coffeeSpeciesGrindUserAnswer);
            }
        }

        //Get the user answers for the brew methods
        final CheckBox dripCheckBox = (CheckBox) findViewById(R.id.drip_method_checkbox);
        CheckBox frenchPressCheckBox = (CheckBox) findViewById(R.id.french_press_method_checkbox);
        CheckBox airPotCheckBox = (CheckBox) findViewById(R.id.air_pot_method_checkbox);
        CheckBox chemexCheckBox = (CheckBox) findViewById(R.id.chemex_method_checkbox);
        CheckBox mokaPotCheckBox = (CheckBox) findViewById(R.id.moka_method_checkbox);

        //Compare the user's answer to the correct answer
        //Add to the score if the answer is correct
        if (dripCheckBox.isChecked() && frenchPressCheckBox.isChecked() && !airPotCheckBox.isChecked()
                && chemexCheckBox.isChecked() && mokaPotCheckBox.isChecked()) {
            score += 1;
        }
        else{
            wrongAnswerMessage += "\n\n" + getString(R.string.brewMethodWrongMessage);

            if(dripCheckBox.isChecked()){
                wrongAnswerMessage += getString(R.string.userDripAnswer);
            }

            if(frenchPressCheckBox.isChecked()){
                wrongAnswerMessage += getString(R.string.userFrenchPressAnswer);
            }

            if(airPotCheckBox.isChecked()){
                wrongAnswerMessage += getString(R.string.userAirPotAnswer);
            }

            if(chemexCheckBox.isChecked()){
                wrongAnswerMessage += getString(R.string.userChemexAnswer);
            }

            if(mokaPotCheckBox.isChecked()){
                wrongAnswerMessage += getString(R.string.userMokaPotAnswer);
            }
        }

        //Now that the user has submitted their answers, hide the last question of the quiz
        LinearLayout brewMethodLayout = (LinearLayout) findViewById(R.id.brew_method_layout);
        brewMethodLayout.setVisibility(View.GONE);

        //Display the user's score
        LinearLayout scoreLayout = (LinearLayout) findViewById(R.id.score_layout);
        String scoreMessage = getString(R.string.scoreMessageText, score);

        //If the user didn't get a perfect score, display the missed questions
        if(score<5){
            scoreMessage += "\n\n" + wrongAnswerMessage;
        }

        //Set the quiz score message and make visible
        TextView messageView = (TextView) findViewById(R.id.message);
        messageView.setText(scoreMessage);
        scoreLayout.setVisibility(View.VISIBLE);

    }

    //Process button clicks for the next button
    public void nextButtonClick(View v) {

        LinearLayout frenchPressGrindLayout;
        LinearLayout frenchPressTempLayout;
        LinearLayout espressoGrindLayout;
        LinearLayout coffeeSpeciesLayout;
        LinearLayout brewMethodLayout;

        //Figure out which next button was clicked
        switch (v.getId()) {
            case R.id.french_press_grind_button:
                //Hide current question
                frenchPressGrindLayout = (LinearLayout) findViewById(R.id.french_press_grind_layout);
                frenchPressGrindLayout.setVisibility(View.GONE);

                //Make next question visible
                frenchPressTempLayout = (LinearLayout) findViewById(R.id.french_press_temp_layout);
                frenchPressTempLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.french_press_temp_button:
                //Hide current question
                frenchPressTempLayout = (LinearLayout) findViewById(R.id.french_press_temp_layout);
                frenchPressTempLayout.setVisibility(View.GONE);

                // Hide the keyboard after the editText field is done.
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                //Make next question visible
                espressoGrindLayout = (LinearLayout) findViewById(R.id.espresso_grind_layout);
                espressoGrindLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.espress_grind_button:
                //Hide current question
                espressoGrindLayout = (LinearLayout) findViewById(R.id.espresso_grind_layout);
                espressoGrindLayout.setVisibility(View.GONE);

                //Make next question visible
                coffeeSpeciesLayout = (LinearLayout) findViewById(R.id.coffee_species_layout);
                coffeeSpeciesLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.coffee_species_button:
                //Hide current question
                coffeeSpeciesLayout = (LinearLayout) findViewById(R.id.coffee_species_layout);
                coffeeSpeciesLayout.setVisibility(View.GONE);

                //Make next question visible
                brewMethodLayout = (LinearLayout) findViewById(R.id.brew_method_layout);
                brewMethodLayout.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
    }
}
