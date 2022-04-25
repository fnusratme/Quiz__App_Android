package my.first.a3_QuizApp_Android_fnusrat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    QuestionBank obj = new QuestionBank();
    int index = 0;
    int QuestionId;
    int ColorId;
    QuesFragment fragmentObj;
    Button trueButton;
    Button falseButton;
    StorageManager storageObject = new StorageManager();
    ProgressBar BottomProgressBar;
    Boolean tag;
    int totalScore;
    String getAverageDialogString;



    public void UpdateFragment(int qId, int colorId) {

        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.fragment_container);
        fragmentObj = QuesFragment.newInstance(qId, colorId);
        manager.beginTransaction().replace(R.id.fragment_container, fragmentObj).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // saved state
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("QuestionIndex");
        }

        QuestionId = obj.questionList.get(index).ques;
        ColorId = obj.colorList.get(index);
        UpdateFragment(QuestionId, ColorId);

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);

        BottomProgressBar =(ProgressBar)findViewById(R.id.simpleProgressBar); //progress bar
        BottomProgressBar.setMax(100);
        BottomProgressBar.setProgress(0);
    }

    //Saving state

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("QuestionIndex", index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.average: {
                String message = storageObject.GetData(MainActivity.this);
                    int attemptCount = storageObject.CountNumberOfAttempts();
                    int totalAverage = storageObject.CountAverageScore();
                    System.out.println("Average Score = " + totalAverage);
                    String dialogMessage = "Your correct answers are " + totalAverage
                            + " in " + attemptCount + " attempts!"; //String to display in dialog box
                    System.out.println(dialogMessage);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(dialogMessage);
                    builder.setPositiveButton("OK", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    break;
                }

            case R.id.reset_data:{
                storageObject.ResetData(MainActivity.this);
                break;
            }
        }
        return true;
    }


    public void ButtonClicked(View view) {
        if (index < obj.questionList.size()-1) {
            if(trueButton.isPressed()){
                tag = true;
            }
            else{
                tag = false;
            }

            if(tag==obj.questionList.get(index).ans){// Checking if answer is correct or incorrect
                totalScore++;
                Toast.makeText(this, R.string.CorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            if(tag!=obj.questionList.get(index).ans) {
                Toast.makeText(this, R.string.IncorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            System.out.println("  Total Score  " + totalScore);

            index++;
            QuestionId = obj.questionList.get(index).ques;
            ColorId = obj.colorList.get(index);
            System.out.println("    Button Clicked    ");

            UpdateFragment(QuestionId, ColorId);
            BottomProgressBar.setProgress(BottomProgressBar.getProgress()+10);


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Your Scores are " + "\t" +  totalScore +"\t"+ "out of 10!");
            getAverageDialogString = totalScore +"/" + 10 + "#";
            builder.setPositiveButton("Save", (dialogInterface, i) -> storageObject.SaveData
                    (MainActivity.this,getAverageDialogString));
            totalScore=0;
            builder.setNegativeButton("Ignore",null);
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            index = 0;
            Collections.shuffle(obj.questionList);
            Collections.shuffle(obj.colorList);
            UpdateFragment(obj.questionList.get(index).ques,obj.colorList.get(index));
            BottomProgressBar.setProgress(0);
        }
    }
}

