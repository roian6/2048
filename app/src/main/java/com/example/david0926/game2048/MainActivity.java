package com.example.david0926.game2048;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends Activity {

    RelativeLayout table;
    float x1, x2, y1, y2, xMove, yMove;
    int board[][] = new int[4][4];
    int lastboard[][] = new int[4][4];
    int movedboard[][] = new int [4][4];
    int defaultNum1=0, defaultNum2=0;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Random rand = new Random();

        while (defaultNum1 == defaultNum2) {
            defaultNum1 = rand.nextInt(16);
            defaultNum2 = rand.nextInt(16);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (defaultNum1 == i * 4 + j || defaultNum2 == i * 4 + j) {
                    board[j][i] = 2;
                } else board[j][i] = 0;
                lastboard[j][i] = board[j][i];
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[j][i] == 0) getTextView(i * 4 + j + 1).setText("　");
                else getTextView(i * 4 + j + 1).setText(String.valueOf(board[j][i]));
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String[] theme = getApplicationContext().getResources().getStringArray(R.array.green);
                switch (board[j][i]) {
                    case 0:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.WHITE);
                        break;
                    case 2:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[0]));
                        break;
                    case 4:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[1]));
                        break;
                    case 8:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[2]));
                        break;
                    case 16:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[3]));
                        break;
                    case 32:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[4]));
                        break;
                    case 64:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[5]));
                        break;
                    case 128:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[6]));
                        break;
                    case 256:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[7]));
                        break;
                    case 512:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[8]));
                        break;
                    case 1024:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[9]));
                        break;
                    case 2048:
                        getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[10]));
                        break;

                }
            }
        }

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

                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    movedboard[j][i] = 0;
                                }
                            }

                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    lastboard[j][i] = board[j][i];
                                }
                            }

                            if (Math.abs(xMove) > Math.abs(yMove)) {
                                if (xMove > 0) {
                                    for (int i = 0; i < 4; i++) {
                                        if (board[2][i] != 0) {
                                            if (board[3][i] == 0) {
                                                board[3][i] = board[2][i];
                                                board[2][i] = 0;
                                            } else if (board[3][i] == board[2][i]) {
                                                if(movedboard[2][i] == 0 || movedboard[3][i] == 0){
                                                    board[3][i] += board[2][i];
                                                    board[2][i] = 0;
                                                    movedboard[2][i] = 1;
                                                    movedboard[3][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
                                        if (board[1][i] != 0) {
                                            if (board[2][i] == 0) {
                                                board[2][i] = board[1][i];
                                                board[1][i] = 0;
                                                if (board[3][i] == 0) {
                                                    board[3][i] = board[2][i];
                                                    board[2][i] = 0;
                                                } else if (board[3][i] == board[2][i]) {
                                                    if(movedboard[2][i] == 0 || movedboard[3][i] == 0){
                                                        board[3][i] += board[2][i];
                                                        board[2][i] = 0;
                                                        movedboard[2][i] = 1;
                                                        movedboard[3][i] = 1;
                                                    }
                                                }
                                            } else if (board[2][i] == board[1][i]) {
                                                if(movedboard[1][i] == 0 || movedboard[2][i] == 0){
                                                    board[2][i] += board[1][i];
                                                    board[1][i] = 0;
                                                    movedboard[1][i] = 1;
                                                    movedboard[2][i] = 1;
                                                }

                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
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
                                                        if(movedboard[2][i] == 0 || movedboard[3][i] == 0){
                                                            board[3][i] += board[2][i];
                                                            board[2][i] = 0;
                                                            movedboard[2][i] = 1;
                                                            movedboard[3][i] = 1;
                                                        }
                                                    }
                                                } else if (board[2][i] == board[1][i]) {
                                                    if(movedboard[1][i] == 0 || movedboard[2][i] == 0){
                                                        board[2][i] += board[1][i];
                                                        board[1][i] = 0;
                                                        movedboard[1][i] = 1;
                                                        movedboard[2][i] = 1;
                                                    }

                                                }
                                            } else if (board[1][i] == board[0][i]) {
                                                if(movedboard[0][i] == 0 || movedboard[1][i] == 0){
                                                    board[1][i] += board[0][i];
                                                    board[0][i] = 0;
                                                    movedboard[0][i] = 1;
                                                    movedboard[1][i] = 1;
                                                }

                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                            if (board[j][i] == 0)
                                                getTextView(i * 4 + j + 1).setText("");
                                            else
                                                getTextView(i * 4 + j + 1).setText(String.valueOf(board[j][i]));
                                        }
                                    }


                                } else {
                                    for (int i = 0; i < 4; i++) {
                                        if (board[1][i] != 0) {
                                            if (board[0][i] == 0) {
                                                board[0][i] = board[1][i];
                                                board[1][i] = 0;
                                            } else if (board[0][i] == board[1][i]) {
                                                if(movedboard[0][i] == 0 || movedboard[1][i] == 0){
                                                    board[0][i] += board[1][i];
                                                    board[1][i] = 0;
                                                    movedboard[0][i] = 1;
                                                    movedboard[1][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
                                        if (board[2][i] != 0) {
                                            if (board[1][i] == 0) {
                                                board[1][i] = board[2][i];
                                                board[2][i] = 0;
                                                if (board[0][i] == 0) {
                                                    board[0][i] = board[1][i];
                                                    board[1][i] = 0;
                                                } else if (board[0][i] == board[1][i]) {
                                                    if(movedboard[0][i] == 0 || movedboard[1][i] == 0){
                                                        board[0][i] += board[1][i];
                                                        board[1][i] = 0;
                                                        movedboard[0][i] = 1;
                                                        movedboard[1][i] = 1;
                                                    }
                                                }
                                            } else if (board[1][i] == board[2][i]) {
                                                if(movedboard[1][i] == 0 || movedboard[2][i] == 0){
                                                    board[1][i] += board[2][i];
                                                    board[2][i] = 0;
                                                    movedboard[1][i] = 1;
                                                    movedboard[2][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
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
                                                        if(movedboard[0][i] == 0 || movedboard[1][i] == 0){
                                                            board[0][i] += board[1][i];
                                                            board[1][i] = 0;
                                                            movedboard[0][i] = 1;
                                                            movedboard[1][i] = 1;
                                                        }
                                                    }
                                                } else if (board[1][i] == board[2][i]) {
                                                    if(movedboard[1][i] == 0 || movedboard[2][i] == 0){
                                                        board[1][i] += board[2][i];
                                                        board[2][i] = 0;
                                                        movedboard[1][i] = 1;
                                                        movedboard[2][i] = 1;
                                                    }

                                                }
                                            } else if (board[2][i] == board[3][i]) {
                                                if(movedboard[2][i] == 0 || movedboard[3][i] == 0){
                                                    board[2][i] += board[3][i];
                                                    board[3][i] = 0;
                                                    movedboard[2][i] = 1;
                                                    movedboard[3][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                            if (board[j][i] == 0)
                                                getTextView(i * 4 + j + 1).setText("");
                                            else
                                                getTextView(i * 4 + j + 1).setText(String.valueOf(board[j][i]));
                                        }
                                    }
                                }
                            } else if (Math.abs(xMove) < Math.abs(yMove)) {
                                if (yMove < 0) {
                                    for (int i = 0; i < 4; i++) {
                                        if (board[i][1] != 0) {
                                            if (board[i][0] == 0) {
                                                board[i][0] = board[i][1];
                                                board[i][1] = 0;
                                            } else if (board[i][0] == board[i][1]) {
                                                if(movedboard[i][0] == 0 || movedboard[i][1] == 0){
                                                    board[i][0] += board[i][1];
                                                    board[i][1] = 0;
                                                    movedboard[0][i] = 1;
                                                    movedboard[1][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
                                        if (board[i][2] != 0) {
                                            if (board[i][1] == 0) {
                                                board[i][1] = board[i][2];
                                                board[i][2] = 0;
                                                if (board[i][0] == 0) {
                                                    board[i][0] = board[i][1];
                                                    board[i][1] = 0;
                                                } else if (board[i][0] == board[i][1]) {
                                                    if(movedboard[i][0] == 0 || movedboard[i][1] == 0){
                                                        board[i][0] += board[i][1];
                                                        board[i][1] = 0;
                                                        movedboard[0][i] = 1;
                                                        movedboard[1][i] = 1;
                                                    }
                                                }
                                            } else if (board[i][1] == board[i][2]) {
                                                if(movedboard[i][1] == 0 || movedboard[i][2] == 0){
                                                    board[i][1] += board[i][2];
                                                    board[i][2] = 0;
                                                    movedboard[1][i] = 1;
                                                    movedboard[2][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
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
                                                        if(movedboard[i][0] == 0 || movedboard[i][1] == 0){
                                                            board[i][0] += board[i][1];
                                                            board[i][1] = 0;
                                                            movedboard[0][i] = 1;
                                                            movedboard[1][i] = 1;
                                                        }
                                                    }
                                                } else if (board[i][1] == board[i][2]) {
                                                    if(movedboard[i][1] == 0 || movedboard[i][2] == 0){
                                                        board[i][1] += board[i][2];
                                                        board[i][2] = 0;
                                                        movedboard[1][i] = 1;
                                                        movedboard[2][i] = 1;
                                                    }

                                                }
                                            } else if (board[i][2] == board[i][3]) {
                                                if(movedboard[i][2] == 0 || movedboard[i][3] == 0){
                                                    board[i][2] += board[i][3];
                                                    board[i][3] = 0;
                                                    movedboard[2][i] = 1;
                                                    movedboard[3][i] = 1;
                                                }
                                            }
                                        }
                                    }

                                    for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                            if (board[j][i] == 0)
                                                getTextView(i * 4 + j + 1).setText("");
                                            else
                                                getTextView(i * 4 + j + 1).setText(String.valueOf(board[j][i]));
                                        }
                                    }
                                } else {
                                    for (int i = 0; i < 4; i++) {
                                        if (board[i][2] != 0) {
                                            if (board[i][3] == 0) {
                                                board[i][3] = board[i][2];
                                                board[i][2] = 0;
                                            } else if (board[i][3] == board[i][2]) {
                                                if(movedboard[i][2] == 0 || movedboard[i][3] == 0){
                                                    board[i][3] += board[i][2];
                                                    board[i][2] = 0;
                                                    movedboard[2][i] = 1;
                                                    movedboard[3][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
                                        if (board[i][1] != 0) {
                                            if (board[i][2] == 0) {
                                                board[i][2] = board[i][1];
                                                board[i][1] = 0;
                                                if (board[i][3] == 0) {
                                                    board[i][3] = board[i][2];
                                                    board[i][2] = 0;
                                                } else if (board[i][3] == board[i][2]) {
                                                    if(movedboard[i][2] == 0 || movedboard[i][3] == 0){
                                                        board[i][3] += board[i][2];
                                                        board[i][2] = 0;
                                                        movedboard[2][i] = 1;
                                                        movedboard[3][i] = 1;
                                                    }
                                                }
                                            } else if (board[i][2] == board[i][1]) {
                                                if(movedboard[i][1] == 0 || movedboard[i][2] == 0){
                                                    board[i][2] += board[i][1];
                                                    board[i][1] = 0;
                                                    movedboard[1][i] = 1;
                                                    movedboard[2][i] = 1;
                                                }
                                            }
                                        }
                                    }
                                    for (int i = 0; i < 4; i++) {
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
                                                        if(movedboard[i][2] == 0 || movedboard[i][3] == 0){
                                                            board[i][3] += board[i][2];
                                                            board[i][2] = 0;
                                                            movedboard[2][i] = 1;
                                                            movedboard[3][i] = 1;
                                                        }
                                                    }
                                                } else if (board[i][2] == board[i][1]) {
                                                    if(movedboard[i][1] == 0 || movedboard[i][2] == 0){
                                                        board[i][2] += board[i][1];
                                                        board[i][1] = 0;
                                                        movedboard[1][i] = 1;
                                                        movedboard[2][i] = 1;
                                                    }
                                                }
                                            } else if (board[i][1] == board[i][0]) {
                                                if(movedboard[i][0] == 0 || movedboard[i][1] == 0){
                                                    board[i][1] += board[i][0];
                                                    board[i][0] = 0;
                                                    movedboard[0][i] = 1;
                                                    movedboard[1][i] = 1;
                                                }
                                            }
                                        }
                                    }

                                    for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                            if (board[j][i] == 0)
                                                getTextView(i * 4 + j + 1).setText("");
                                            else
                                                getTextView(i * 4 + j + 1).setText(String.valueOf(board[j][i]));
                                        }
                                    }
                                }
                            }


                            boolean moved = false;
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    if (lastboard[j][i] != board[j][i]) {
                                        moved = true;
                                    }
                                }
                            }

                            if (moved) {
                                while (true) { //랜덤 위치에 숫자 스폰
                                    int num1, num2, stack = 0;
                                    num1 = rand.nextInt(4);
                                    num2 = rand.nextInt(4);
                                    if (board[num1][num2] == 0) {
                                        if(rand.nextInt(6)==0) board[num1][num2] = 4;
                                        else board[num1][num2] = 2;

                                        for (int i = 0; i < 4; i++) {
                                            for (int j = 0; j < 4; j++) {
                                                if (board[j][i] == 0)
                                                    getTextView(i * 4 + j + 1).setText("");
                                                else
                                                    getTextView(i * 4 + j + 1).setText(String.valueOf(board[j][i]));
                                            }
                                        }
                                        break;
                                    }
                                    for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                            if (board[j][i] != 0) stack++;
                                        }
                                    }
                                    if (stack == 16) break;
                                }
                            }

                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    String[] theme = getApplicationContext().getResources().getStringArray(R.array.green);
                                    switch (board[j][i]) {
                                        case 0:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.WHITE);
                                            break;
                                        case 2:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[0]));
                                            break;
                                        case 4:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[1]));
                                            break;
                                        case 8:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[2]));
                                            break;
                                        case 16:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[3]));
                                            break;
                                        case 32:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[4]));
                                            break;
                                        case 64:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[5]));
                                            break;
                                        case 128:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[6]));
                                            break;
                                        case 256:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[7]));
                                            break;
                                        case 512:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[8]));
                                            break;
                                        case 1024:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[9]));
                                            break;
                                        case 2048:
                                            getTextView(i * 4 + j + 1).setBackgroundColor(Color.parseColor(theme[10]));
                                            break;

                                    }
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    public TextView getTextView(int num) {
        switch (num) {
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

            default:
                return findViewById(R.id.text1);
        }

    }

}
