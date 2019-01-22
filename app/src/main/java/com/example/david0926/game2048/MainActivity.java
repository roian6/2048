package com.example.david0926.game2048;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {
    
    RelativeLayout table;
    float x1, x2, y1, y2, xMove, yMove;
    int board[][] = new int[4][4];
    int defaultNum1, defaultNum2;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Random rand = new Random();
        defaultNum1 = rand.nextInt(16);
        defaultNum2 = rand.nextInt(16);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(defaultNum1 == i*4+j+1 || defaultNum2 == i*4+j+1){
                    board[i][j] = 2;
                }
                else board[i][j] = 0;

            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                getTextView(i*4+j+1).setText(String.valueOf(board[j][i]));
            }
        }
//        t1 = findViewById(R.id.text1);
//        t1.setText(String.valueOf(board[0][0]));
//        t2 = findViewById(R.id.text2);
//        t2.setText(String.valueOf(board[1][0]));
//        t3 = findViewById(R.id.text3);
//        t3.setText(String.valueOf(board[2][0]));
//        t4 = findViewById(R.id.text4);
//        t4.setText(String.valueOf(board[3][0]));
//        t5 = findViewById(R.id.text5);
//        t5.setText(String.valueOf(board[0][1]));
//        t6 = findViewById(R.id.text6);
//        t6.setText(String.valueOf(board[1][1]));
//        t7 = findViewById(R.id.text7);
//        t7.setText(String.valueOf(board[2][1]));
//        t8 = findViewById(R.id.text8);
//        t8.setText(String.valueOf(board[3][1]));
//        t9 = findViewById(R.id.text9);
//        t9.setText(String.valueOf(board[0][2]));
//        t10 = findViewById(R.id.text10);
//        t10.setText(String.valueOf(board[1][2]));
//        t11 = findViewById(R.id.text11);
//        t11.setText(String.valueOf(board[2][2]));
//        t12 = findViewById(R.id.text12);
//        t12.setText(String.valueOf(board[3][2]));
//        t13 = findViewById(R.id.text13);
//        t13.setText(String.valueOf(board[0][3]));
//        t14 = findViewById(R.id.text14);
//        t14.setText(String.valueOf(board[1][3]));
//        t15 = findViewById(R.id.text15);
//        t15.setText(String.valueOf(board[2][3]));
//        t16 = findViewById(R.id.text16);
//        t16.setText(String.valueOf(board[3][3]));

        table = findViewById(R.id.table);
        table.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();

                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();

                        xMove = x2 - x1;
                        yMove = y2 - y1;
                        if (Math.abs(xMove) > 50 || Math.abs(yMove) > 50) {
                            if (Math.abs(xMove) > Math.abs(yMove)) {
                                if (xMove > 0) {
                                    Toast.makeText(getApplicationContext(), "right swipe", Toast.LENGTH_SHORT).show();

                                    for(int i=0;i<4;i++){
                                        if(board[2][i]!=0){
                                            if(board[3][i]==0){
                                                board[3][i] = board[2][i];
                                                board[2][i] = 0;
                                            }
                                            else if(board[3][i]==board[2][i]){
                                                board[3][i] += board[2][i];
                                                board[2][i] = 0;
                                            }
                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[1][i] != 0) {
                                            if (board[2][i] == 0) {
                                                board[2][i] = board[2][i];
                                                board[1][i] = 0;
                                                if (board[3][i] == 0) {
                                                    board[3][i] = board[2][i];
                                                    board[2][i] = 0;
                                                } else if (board[3][i] == board[2][i]) {
                                                    board[3][i] += board[2][i];
                                                    board[2][i] = 0;
                                                }
                                            } else if (board[2][i] == board[1][i]) {
                                                board[2][i] += board[1][i];
                                                board[1][i] = 0;
                                            }

                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[0][i] != 0) {
                                            if (board[1][i] == 0) {
                                                board[1][i] = board[0][i];
                                                board[0][i] = 0;
                                                if (board[2][i] == 0) {
                                                    board[2][i] = board[1][i];
                                                    board[1][i] = 0;
                                                    if (board[3][i] == 0) {
                                                        board[3][i] = board[2][i];
                                                        board[2][i] = 0;
                                                    } else if (board[3][i] == board[2][i]) {
                                                        board[3][i] += board[2][i];
                                                        board[2][i] = 0;
                                                    }
                                                } else if (board[2][i] == board[1][i]) {
                                                    board[2][i] += board[1][i];
                                                    board[1][i] = 0;
                                                }
                                            } else if (board[1][i] == board[0][i]) {
                                                board[1][i] += board[0][i];
                                                board[0][i] = 0;
                                            }
                                        }
                                    }
                                    for(int i=0;i<4;i++){
                                        for(int j=0;j<4;j++){
                                            getTextView(i*4+j+1).setText(String.valueOf(board[j][i]));
                                        }
                                    }

//                                    t1.setText(String.valueOf(board[0][0]));
//                                    t2.setText(String.valueOf(board[1][0]));
//                                    t3.setText(String.valueOf(board[2][0]));
//                                    t4.setText(String.valueOf(board[3][0]));
//                                    t5.setText(String.valueOf(board[0][1]));
//                                    t6.setText(String.valueOf(board[1][1]));
//                                    t7.setText(String.valueOf(board[2][1]));
//                                    t8.setText(String.valueOf(board[3][1]));
//                                    t9.setText(String.valueOf(board[0][2]));
//                                    t10.setText(String.valueOf(board[1][2]));
//                                    t11.setText(String.valueOf(board[2][2]));
//                                    t12.setText(String.valueOf(board[3][2]));
//                                    t13.setText(String.valueOf(board[0][3]));
//                                    t14.setText(String.valueOf(board[1][3]));
//                                    t15.setText(String.valueOf(board[2][3]));
//                                    t16.setText(String.valueOf(board[3][3]));

                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "left swipe", Toast.LENGTH_SHORT).show();
                                    for(int i=0;i<4;i++){
                                        if(board[1][i]!=0){
                                            if(board[0][i]==0){
                                                board[0][i] = board[1][i];
                                                board[1][i] = 0;
                                            }
                                            else if(board[0][i]==board[1][i]){
                                                board[0][i] += board[1][i];
                                                board[1][i] = 0;
                                            }
                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[2][i] != 0) {
                                            if (board[1][i] == 0) {
                                                board[1][i] = board[2][i];
                                                board[2][i] = 0;
                                                if (board[0][i] == 0) {
                                                    board[0][i] = board[1][i];
                                                    board[1][i] = 0;
                                                } else if (board[0][i] == board[1][i]) {
                                                    board[0][i] += board[1][i];
                                                    board[1][i] = 0;
                                                }
                                            } else if (board[1][i] == board[2][i]) {
                                                board[1][i] += board[2][i];
                                                board[2][i] = 0;
                                            }

                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[3][i] != 0) {
                                            if (board[2][i] == 0) {
                                                board[2][i] = board[3][i];
                                                board[3][i] = 0;
                                                if (board[1][i] == 0) {
                                                    board[1][i] = board[2][i];
                                                    board[2][i] = 0;
                                                    if (board[0][i] == 0) {
                                                        board[0][i] = board[1][i];
                                                        board[1][i] = 0;
                                                    } else if (board[0][i] == board[1][i]) {
                                                        board[0][i] += board[1][i];
                                                        board[1][i] = 0;
                                                    }
                                                } else if (board[1][i] == board[2][i]) {
                                                    board[1][i] += board[2][i];
                                                    board[2][i] = 0;
                                                }
                                            } else if (board[2][i] == board[3][i]) {
                                                board[2][i] += board[3][i];
                                                board[3][i] = 0;
                                            }
                                        }
                                    }
                                    for(int i=0;i<4;i++){
                                        for(int j=0;j<4;j++){
                                            getTextView(i*4+j+1).setText(String.valueOf(board[j][i]));
                                        }
                                    }
//                                    t1.setText(String.valueOf(board[0][0]));
//                                    t2.setText(String.valueOf(board[1][0]));
//                                    t3.setText(String.valueOf(board[2][0]));
//                                    t4.setText(String.valueOf(board[3][0]));
//                                    t5.setText(String.valueOf(board[0][1]));
//                                    t6.setText(String.valueOf(board[1][1]));
//                                    t7.setText(String.valueOf(board[2][1]));
//                                    t8.setText(String.valueOf(board[3][1]));
//                                    t9.setText(String.valueOf(board[0][2]));
//                                    t10.setText(String.valueOf(board[1][2]));
//                                    t11.setText(String.valueOf(board[2][2]));
//                                    t12.setText(String.valueOf(board[3][2]));
//                                    t13.setText(String.valueOf(board[0][3]));
//                                    t14.setText(String.valueOf(board[1][3]));
//                                    t15.setText(String.valueOf(board[2][3]));
//                                    t16.setText(String.valueOf(board[3][3]));
                                }
                            }
                            else if (Math.abs(xMove) < Math.abs(yMove)) {
                                if (yMove < 0) {
                                    Toast.makeText(getApplicationContext(), "up swipe", Toast.LENGTH_SHORT).show();
                                    for(int i=0;i<4;i++){
                                        if(board[i][1]!=0){
                                            if(board[i][0]==0){
                                                board[i][0] = board[i][1];
                                                board[i][1] = 0;
                                            }
                                            else if(board[i][0]==board[i][1]){
                                                board[i][0] += board[i][1];
                                                board[i][1] = 0;
                                            }
                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[i][2] != 0) {
                                            if (board[i][1] == 0) {
                                                board[i][1] = board[i][2];
                                                board[i][2] = 0;
                                                if (board[i][0] == 0) {
                                                    board[i][0] = board[i][1];
                                                    board[i][1] = 0;
                                                } else if (board[i][0] == board[i][1]) {
                                                    board[i][0] += board[i][1];
                                                    board[i][1] = 0;
                                                }
                                            } else if (board[i][1] == board[i][2]) {
                                                board[i][1] += board[i][2];
                                                board[i][2] = 0;
                                            }

                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[i][3] != 0) {
                                            if (board[i][2] == 0) {
                                                board[i][2] = board[i][3];
                                                board[i][3] = 0;
                                                if (board[i][1] == 0) {
                                                    board[i][1] = board[i][2];
                                                    board[i][2] = 0;
                                                    if (board[i][0] == 0) {
                                                        board[i][0] = board[i][1];
                                                        board[i][1] = 0;
                                                    } else if (board[i][0] == board[i][1]) {
                                                        board[i][0] += board[i][1];
                                                        board[i][1] = 0;
                                                    }
                                                } else if (board[i][1] == board[i][2]) {
                                                    board[i][1] += board[i][2];
                                                    board[i][2] = 0;
                                                }
                                            } else if (board[i][2] == board[i][3]) {
                                                board[i][2] += board[i][3];
                                                board[i][3] = 0;
                                            }
                                        }
                                    }

                                    for(int i=0;i<4;i++){
                                        for(int j=0;j<4;j++){
                                            getTextView(i*4+j+1).setText(String.valueOf(board[j][i]));
                                        }
                                    }
//                                    t1.setText(String.valueOf(board[0][0]));
//                                    t2.setText(String.valueOf(board[1][0]));
//                                    t3.setText(String.valueOf(board[2][0]));
//                                    t4.setText(String.valueOf(board[3][0]));
//                                    t5.setText(String.valueOf(board[0][1]));
//                                    t6.setText(String.valueOf(board[1][1]));
//                                    t7.setText(String.valueOf(board[2][1]));
//                                    t8.setText(String.valueOf(board[3][1]));
//                                    t9.setText(String.valueOf(board[0][2]));
//                                    t10.setText(String.valueOf(board[1][2]));
//                                    t11.setText(String.valueOf(board[2][2]));
//                                    t12.setText(String.valueOf(board[3][2]));
//                                    t13.setText(String.valueOf(board[0][3]));
//                                    t14.setText(String.valueOf(board[1][3]));
//                                    t15.setText(String.valueOf(board[2][3]));
//                                    t16.setText(String.valueOf(board[3][3]));
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "down swipe", Toast.LENGTH_SHORT).show();
                                    for(int i=0;i<4;i++){
                                        if(board[i][2]!=0){
                                            if(board[i][3]==0){
                                                board[i][3] = board[i][2];
                                                board[i][2] = 0;
                                            }
                                            else if(board[i][3]==board[i][2]){
                                                board[i][3] += board[i][2];
                                                board[i][2] = 0;
                                            }
                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[i][1] != 0) {
                                            if (board[i][2] == 0) {
                                                board[i][2] = board[i][1];
                                                board[i][1] = 0;
                                                if (board[i][3] == 0) {
                                                    board[i][3] = board[i][2];
                                                    board[i][2] = 0;
                                                } else if (board[i][3] == board[i][2]) {
                                                    board[i][3] += board[i][2];
                                                    board[i][2] = 0;
                                                }
                                            } else if (board[i][2] == board[i][1]) {
                                                board[i][2] += board[i][1];
                                                board[i][1] = 0;
                                            }

                                        }
                                    }
                                    for(int i=0;i<4;i++) {
                                        if (board[i][0] != 0) {
                                            if (board[i][1] == 0) {
                                                board[i][1] = board[i][0];
                                                board[i][0] = 0;
                                                if (board[i][2] == 0) {
                                                    board[i][2] = board[i][1];
                                                    board[i][1] = 0;
                                                    if (board[i][3] == 0) {
                                                        board[i][3] = board[i][2];
                                                        board[i][2] = 0;
                                                    } else if (board[i][3] == board[i][2]) {
                                                        board[i][3] += board[i][2];
                                                        board[i][2] = 0;
                                                    }
                                                } else if (board[i][2] == board[i][1]) {
                                                    board[i][2] += board[i][1];
                                                    board[i][1] = 0;
                                                }
                                            } else if (board[i][1] == board[i][0]) {
                                                board[i][1] += board[i][0];
                                                board[i][0] = 0;
                                            }
                                        }
                                    }

                                    for(int i=0;i<4;i++){
                                        for(int j=0;j<4;j++){
                                            getTextView(i*4+j+1).setText(String.valueOf(board[j][i]));
                                        }
                                    }

//                                    t1.setText(String.valueOf(board[0][0]));
//                                    t2.setText(String.valueOf(board[1][0]));
//                                    t3.setText(String.valueOf(board[2][0]));
//                                    t4.setText(String.valueOf(board[3][0]));
//                                    t5.setText(String.valueOf(board[0][1]));
//                                    t6.setText(String.valueOf(board[1][1]));
//                                    t7.setText(String.valueOf(board[2][1]));
//                                    t8.setText(String.valueOf(board[3][1]));
//                                    t9.setText(String.valueOf(board[0][2]));
//                                    t10.setText(String.valueOf(board[1][2]));
//                                    t11.setText(String.valueOf(board[2][2]));
//                                    t12.setText(String.valueOf(board[3][2]));
//                                    t13.setText(String.valueOf(board[0][3]));
//                                    t14.setText(String.valueOf(board[1][3]));
//                                    t15.setText(String.valueOf(board[2][3]));
//                                    t16.setText(String.valueOf(board[3][3]));
                                }
                            }

                        }
                        while(true){
                            int num1, num2;
                            num1 = rand.nextInt(4);
                            num2 = rand.nextInt(4);
                            if(board[num1][num2] == 0){
                                board[num1][num2] = 2;

                                for(int i=0;i<4;i++){
                                    for(int j=0;j<4;j++){
                                        getTextView(i*4+j+1).setText(String.valueOf(board[j][i]));
                                    }
                                }
//                                t1.setText(String.valueOf(board[0][0]));
//                                t2.setText(String.valueOf(board[1][0]));
//                                t3.setText(String.valueOf(board[2][0]));
//                                t4.setText(String.valueOf(board[3][0]));
//                                t5.setText(String.valueOf(board[0][1]));
//                                t6.setText(String.valueOf(board[1][1]));
//                                t7.setText(String.valueOf(board[2][1]));
//                                t8.setText(String.valueOf(board[3][1]));
//                                t9.setText(String.valueOf(board[0][2]));
//                                t10.setText(String.valueOf(board[1][2]));
//                                t11.setText(String.valueOf(board[2][2]));
//                                t12.setText(String.valueOf(board[3][2]));
//                                t13.setText(String.valueOf(board[0][3]));
//                                t14.setText(String.valueOf(board[1][3]));
//                                t15.setText(String.valueOf(board[2][3]));
//                                t16.setText(String.valueOf(board[3][3]));
                                break;
                            }
                        }
//                        if(board[0][0]==2) t1.setBackgroundColor(Color.RED);
//                        else t1.setBackgroundColor(Color.WHITE);
                        break;
                }

                return true;
            }
        });
    }

    public TextView getTextView(int num){
        switch (num){
            case 1:
                return findViewById(R.id.text1);
            case 2:
                return findViewById(R.id.text2);
            case 3:
                return findViewById(R.id.text3);
            case 4:
                return findViewById(R.id.text4);
            case 5:
                return findViewById(R.id.text5);
            case 6:
                return findViewById(R.id.text6);
            case 7:
                return findViewById(R.id.text7);
            case 8:
                return findViewById(R.id.text8);
            case 9:
                return findViewById(R.id.text9);
            case 10:
                return findViewById(R.id.text10);
            case 11:
                return findViewById(R.id.text11);
            case 12:
                return findViewById(R.id.text12);
            case 13:
                return findViewById(R.id.text13);
            case 14:
                return findViewById(R.id.text14);
            case 15:
                return findViewById(R.id.text15);
            case 16:
                return findViewById(R.id.text16);

            default: return findViewById(R.id.text1);
        }

    }

}
