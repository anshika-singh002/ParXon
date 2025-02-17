package com.example.parxondemo1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class ThirdActivity2 extends AppCompatActivity {
    private TextToSpeech textToSpeech;

    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLeftmills;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported or missing data");
                } else {
                    Log.d("TTS", "TextToSpeech initialized successfully");
                }
            } else {
                Log.e("TTS", "Initialization failed");
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");


        int intvalue = Integer.valueOf(buttonvalue);


        switch(intvalue){

            case 1:
                setContentView(R.layout.activity_exercise_stage2_1);
                break;
            case 2:
                setContentView(R.layout.activity_exercise_stage2_2);
                break;
            case 3:
                setContentView(R.layout.activity_exercise_stage2_3);
                break;
            case 4:
                setContentView(R.layout.activity_exercise_stage2_4);
                break;
            case 5:
                setContentView(R.layout.activity_exercise_stage2_5);
                break;
            case 6:
                setContentView(R.layout.activity_exercise_stage2_6);
                break;
            case 7:
                setContentView(R.layout.activity_exercise_stage2_7);
                break;
            case 8:
                setContentView(R.layout.activity_exercise_stage2_8);
                break;
            case 9:
                setContentView(R.layout.activity_exercise_stage2_9);
                break;
            case 10:
                setContentView(R.layout.activity_exercise_stage2_10);
                break;

        }



        startBtn = findViewById(R.id.startbutton);
        mtextview=findViewById(R.id.time);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MTimeRunning)

                {
                    stoptimer();
                }
                else {
                    startTimer();
                }
            }
        });



    }
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }




    public void speakInstructions(View view) {
        String instructions = "";
        switch (buttonvalue) {
            case "1":
                instructions = getString(R.string.pose1);
                break;
            case "2":
                instructions = getString(R.string.pose2);
                break;
            case "3":
                instructions = getString(R.string.pose3);
                break;
            case "4":
                instructions = getString(R.string.pose4);
                break;
            case "5":
                instructions = getString(R.string.pose5);
                break;
            case "6":
                instructions = getString(R.string.pose6);
                break;
            case "7":
                instructions = getString(R.string.pose7);
                break;
            case "8":
                instructions = getString(R.string.pose8);
                break;
            case "9":
                instructions = getString(R.string.pose9);
                break;
            case "10":
                instructions = getString(R.string.pose10);
                break;

        }

        if (!instructions.isEmpty()) {
            textToSpeech.speak(instructions, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void stoptimer()
    {
        countDownTimer.cancel();
        MTimeRunning=false;
        startBtn.setText("START");

    }
    private void startTimer()
    {
        final CharSequence value1 = mtextview.getText();
        String num1=value1.toString();
        String num2=num1.substring(0,2);
        String num3=num1.substring(3,5);

        final int number = Integer.valueOf(num2)*60+ Integer.valueOf(num3);
        MTimeLeftmills=number*1000;


        countDownTimer=new CountDownTimer(MTimeLeftmills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                MTimeLeftmills=millisUntilFinished;
                updateTimer();



            }

            @Override
            public void onFinish() {
                int newvalue=Integer.valueOf(buttonvalue)+1;
                if (newvalue<=7){

                    Intent intent = new Intent(ThirdActivity2.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);

                }
                else{
                    newvalue=1;
                    Intent intent=new Intent(ThirdActivity2.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);
                }

            }
        }.start();
        startBtn.setText("Pause");
        MTimeRunning=true;


    }

    private void updateTimer()
    {
        int minutes = (int) MTimeLeftmills/60000;
        int seconds = (int) MTimeLeftmills%60000 / 1000;



        String timeLeftText="";
        if(minutes<10)
            timeLeftText="0";
        timeLeftText = timeLeftText+minutes+":";
        if(seconds<10)
            timeLeftText+="0";
        timeLeftText+=seconds;
        mtextview.setText(timeLeftText);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}