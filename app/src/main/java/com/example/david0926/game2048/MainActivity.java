package com.example.david0926.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9 , t10, t11, t12, t13, t14, t15, t16;
    RelativeLayout table;
    float x1, x2, y1, y2, xMove, yMove;
    int board[][] = new int[4][4];
    int defaultNum1, defaultNum2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        defaultNum1 = rand.nextInt(16);
        defaultNum2 = rand.nextInt(16);
        if(defaultNum1==defaultNum2) Toast.makeText(this, "same", Toast.LENGTH_SHORT).show();
        //while (defaultNum1==defaultNum2) defaultNum2 = rand.nextInt(16);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(defaultNum1 == i*4+j+1 || defaultNum2 == i*4+j+1){
                    board[i][j] = 2;
                }
                else board[i][j] = 0;

            }
        }

        t1 = findViewById(R.id.text1);
        t1.setText(String.valueOf(board[0][0]));
        t2 = findViewById(R.id.text2);
        t2.setText(String.valueOf(board[0][1]));
        t3 = findViewById(R.id.text3);
        t3.setText(String.valueOf(board[0][2]));
        t4 = findViewById(R.id.text4);
        t4.setText(String.valueOf(board[0][3]));
        t5 = findViewById(R.id.text5);
        t5.setText(String.valueOf(board[1][0]));
        t6 = findViewById(R.id.text6);
        t6.setText(String.valueOf(board[1][1]));
        t7 = findViewById(R.id.text7);
        t7.setText(String.valueOf(board[1][2]));
        t8 = findViewById(R.id.text8);
        t8.setText(String.valueOf(board[1][3]));
        t9 = findViewById(R.id.text9);
        t9.setText(String.valueOf(board[2][0]));
        t10 = findViewById(R.id.text10);
        t10.setText(String.valueOf(board[2][1]));
        t11 = findViewById(R.id.text11);
        t11.setText(String.valueOf(board[2][2]));
        t12 = findViewById(R.id.text12);
        t12.setText(String.valueOf(board[2][3]));
        t13 = findViewById(R.id.text13);
        t13.setText(String.valueOf(board[3][0]));
        t14 = findViewById(R.id.text14);
        t14.setText(String.valueOf(board[3][1]));
        t15 = findViewById(R.id.text15);
        t15.setText(String.valueOf(board[3][2]));
        t16 = findViewById(R.id.text16);
        t16.setText(String.valueOf(board[3][3]));

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


                                    t1 = findViewById(R.id.text1);
                                    t1.setText(String.valueOf(board[0][0]));
                                    t2 = findViewById(R.id.text2);
                                    t2.setText(String.valueOf(board[0][1]));
                                    t3 = findViewById(R.id.text3);
                                    t3.setText(String.valueOf(board[0][2]));
                                    t4 = findViewById(R.id.text4);
                                    t4.setText(String.valueOf(board[0][3]));
                                    t5 = findViewById(R.id.text5);
                                    t5.setText(String.valueOf(board[1][0]));
                                    t6 = findViewById(R.id.text6);
                                    t6.setText(String.valueOf(board[1][1]));
                                    t7 = findViewById(R.id.text7);
                                    t7.setText(String.valueOf(board[1][2]));
                                    t8 = findViewById(R.id.text8);
                                    t8.setText(String.valueOf(board[1][3]));
                                    t9 = findViewById(R.id.text9);
                                    t9.setText(String.valueOf(board[2][0]));
                                    t10 = findViewById(R.id.text10);
                                    t10.setText(String.valueOf(board[2][1]));
                                    t11 = findViewById(R.id.text11);
                                    t11.setText(String.valueOf(board[2][2]));
                                    t12 = findViewById(R.id.text12);
                                    t12.setText(String.valueOf(board[2][3]));
                                    t13 = findViewById(R.id.text13);
                                    t13.setText(String.valueOf(board[3][0]));
                                    t14 = findViewById(R.id.text14);
                                    t14.setText(String.valueOf(board[3][1]));
                                    t15 = findViewById(R.id.text15);
                                    t15.setText(String.valueOf(board[3][2]));
                                    t16 = findViewById(R.id.text16);
                                    t16.setText(String.valueOf(board[3][3]));
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "left swipe", Toast.LENGTH_SHORT).show();
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

                                    t1 = findViewById(R.id.text1);
                                    t1.setText(String.valueOf(board[0][0]));
                                    t2 = findViewById(R.id.text2);
                                    t2.setText(String.valueOf(board[0][1]));
                                    t3 = findViewById(R.id.text3);
                                    t3.setText(String.valueOf(board[0][2]));
                                    t4 = findViewById(R.id.text4);
                                    t4.setText(String.valueOf(board[0][3]));
                                    t5 = findViewById(R.id.text5);
                                    t5.setText(String.valueOf(board[1][0]));
                                    t6 = findViewById(R.id.text6);
                                    t6.setText(String.valueOf(board[1][1]));
                                    t7 = findViewById(R.id.text7);
                                    t7.setText(String.valueOf(board[1][2]));
                                    t8 = findViewById(R.id.text8);
                                    t8.setText(String.valueOf(board[1][3]));
                                    t9 = findViewById(R.id.text9);
                                    t9.setText(String.valueOf(board[2][0]));
                                    t10 = findViewById(R.id.text10);
                                    t10.setText(String.valueOf(board[2][1]));
                                    t11 = findViewById(R.id.text11);
                                    t11.setText(String.valueOf(board[2][2]));
                                    t12 = findViewById(R.id.text12);
                                    t12.setText(String.valueOf(board[2][3]));
                                    t13 = findViewById(R.id.text13);
                                    t13.setText(String.valueOf(board[3][0]));
                                    t14 = findViewById(R.id.text14);
                                    t14.setText(String.valueOf(board[3][1]));
                                    t15 = findViewById(R.id.text15);
                                    t15.setText(String.valueOf(board[3][2]));
                                    t16 = findViewById(R.id.text16);
                                    t16.setText(String.valueOf(board[3][3]));
                                }
                            }
                            else if (Math.abs(xMove) < Math.abs(yMove)) {
                                if (yMove < 0) {
                                    Toast.makeText(getApplicationContext(), "up swipe", Toast.LENGTH_SHORT).show();
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
                                    t1 = findViewById(R.id.text1);
                                    t1.setText(String.valueOf(board[0][0]));
                                    t2 = findViewById(R.id.text2);
                                    t2.setText(String.valueOf(board[0][1]));
                                    t3 = findViewById(R.id.text3);
                                    t3.setText(String.valueOf(board[0][2]));
                                    t4 = findViewById(R.id.text4);
                                    t4.setText(String.valueOf(board[0][3]));
                                    t5 = findViewById(R.id.text5);
                                    t5.setText(String.valueOf(board[1][0]));
                                    t6 = findViewById(R.id.text6);
                                    t6.setText(String.valueOf(board[1][1]));
                                    t7 = findViewById(R.id.text7);
                                    t7.setText(String.valueOf(board[1][2]));
                                    t8 = findViewById(R.id.text8);
                                    t8.setText(String.valueOf(board[1][3]));
                                    t9 = findViewById(R.id.text9);
                                    t9.setText(String.valueOf(board[2][0]));
                                    t10 = findViewById(R.id.text10);
                                    t10.setText(String.valueOf(board[2][1]));
                                    t11 = findViewById(R.id.text11);
                                    t11.setText(String.valueOf(board[2][2]));
                                    t12 = findViewById(R.id.text12);
                                    t12.setText(String.valueOf(board[2][3]));
                                    t13 = findViewById(R.id.text13);
                                    t13.setText(String.valueOf(board[3][0]));
                                    t14 = findViewById(R.id.text14);
                                    t14.setText(String.valueOf(board[3][1]));
                                    t15 = findViewById(R.id.text15);
                                    t15.setText(String.valueOf(board[3][2]));
                                    t16 = findViewById(R.id.text16);
                                    t16.setText(String.valueOf(board[3][3]));
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "down swipe", Toast.LENGTH_SHORT).show();
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
                                    t1 = findViewById(R.id.text1);
                                    t1.setText(String.valueOf(board[0][0]));
                                    t2 = findViewById(R.id.text2);
                                    t2.setText(String.valueOf(board[0][1]));
                                    t3 = findViewById(R.id.text3);
                                    t3.setText(String.valueOf(board[0][2]));
                                    t4 = findViewById(R.id.text4);
                                    t4.setText(String.valueOf(board[0][3]));
                                    t5 = findViewById(R.id.text5);
                                    t5.setText(String.valueOf(board[1][0]));
                                    t6 = findViewById(R.id.text6);
                                    t6.setText(String.valueOf(board[1][1]));
                                    t7 = findViewById(R.id.text7);
                                    t7.setText(String.valueOf(board[1][2]));
                                    t8 = findViewById(R.id.text8);
                                    t8.setText(String.valueOf(board[1][3]));
                                    t9 = findViewById(R.id.text9);
                                    t9.setText(String.valueOf(board[2][0]));
                                    t10 = findViewById(R.id.text10);
                                    t10.setText(String.valueOf(board[2][1]));
                                    t11 = findViewById(R.id.text11);
                                    t11.setText(String.valueOf(board[2][2]));
                                    t12 = findViewById(R.id.text12);
                                    t12.setText(String.valueOf(board[2][3]));
                                    t13 = findViewById(R.id.text13);
                                    t13.setText(String.valueOf(board[3][0]));
                                    t14 = findViewById(R.id.text14);
                                    t14.setText(String.valueOf(board[3][1]));
                                    t15 = findViewById(R.id.text15);
                                    t15.setText(String.valueOf(board[3][2]));
                                    t16 = findViewById(R.id.text16);
                                    t16.setText(String.valueOf(board[3][3]));
                                }
                            }

                        }
                        break;
                }

                return true;
            }
        });
    }


}