package com.example.poker;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Game extends AppCompatActivity {
    String nameOfCard1 = ""; // เก็บชื่อไพ่ ใบที่ 1
    String nameOfCard2 = ""; // เก็บชื่อไพ่ ใบที่ 2
    String nameOfCard3 = ""; // เก็บชื่อไพ่ ใบที่ 3
    String nameOfCard4 = ""; // เก็บชื่อไพ่ ใบที่ 4
    String nameOfCard5 = ""; // เก็บชื่อไพ่ ใบที่ 5

    int suit1 = 0; // เก็บดอกไพ่ ใบที่ 1
    int suit2 = 0; // เก็บดอกไพ่ ใบที่ 2
    int suit3 = 0; // เก็บดอกไพ่ ใบที่ 3
    int suit4 = 0; // เก็บดอกไพ่ ใบที่ 4
    int suit5 = 0; // เก็บดอกไพ่ ใบที่ 5

    int num1 = 0; // เก็บเลขไพ่ ใบที่ 1
    int num2 = 0; // เก็บเลขไพ่ ใบที่ 2
    int num3 = 0; // เก็บเลขไพ่ ใบที่ 3
    int num4 = 0; // เก็บเลขไพ่ ใบที่ 4
    int num5 = 0; // เก็บเลขไพ่ ใบที่ 5

    boolean keep1 = false; // เก็บสถานะ keepไพ่ ใบที่ 1
    boolean keep2 = false; // เก็บสถานะ keepไพ่ ใบที่ 2
    boolean keep3 = false; // เก็บสถานะ keepไพ่ ใบที่ 3
    boolean keep4 = false; // เก็บสถานะ keepไพ่ ใบที่ 4
    boolean keep5 = false; // เก็บสถานะ keepไพ่ ใบที่ 5

    String message = "";  // เก็บข้อความเพื่อแสดง ผลลัพธ์ของเกม
    boolean isDeal = true; // เก็บสถานะช่วง Deal
    int stage = 1; // เก็บสถานะว่า อยู่ขั้นตอนใดของการเล่น
    private final ChipsData chipsData = ChipsData.getInstance();   // ตัวแปร global ของค่าChip และฺBet

    ImageView card1; // เก็บ ImageView ของไพ่ ใบที่ 1
    ImageView card2; // เก็บ ImageView ของไพ่ ใบที่ 2
    ImageView card3; // เก็บ ImageView ของไพ่ ใบที่ 3
    ImageView card4; // เก็บ ImageView ของไพ่ ใบที่ 4
    ImageView card5; // เก็บ ImageView ของไพ่ ใบที่ 5
    RelativeLayout background; // View ของ Layout ที่ใช้
    AccelerateDecelerateInterpolator accelerateDecelerateInterpolator; // Animation Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        background = (RelativeLayout) findViewById(R.id.background);
        card1 = ((ImageView) findViewById(R.id.card1));
        card2 = ((ImageView) findViewById(R.id.card2));
        card3 = ((ImageView) findViewById(R.id.card3));
        card4 = ((ImageView) findViewById(R.id.card4));
        card5 = ((ImageView) findViewById(R.id.card5));
        betgame();  // เข้าสู่ช่วง bet
        newgame(); // set ค่า Chip กับ Bet เมื่อเริ่มเกม
    }

    // ป้องกันไม่ให้ผู้เล่นกด back
    @Override
    public void onBackPressed() {}

    // เมื่อกดปุ่ม Bet / OK / Deal เมธอดนี้ จะทำงาน
    public void playGame(View v){
        if (stage == 1){ // stage 1 คือ ช่วงวางเดิมพันของเกม
            ((TextView) findViewById(R.id.msg)).setText("");
            betgame();
            dealCard(accelerateDecelerateInterpolator);
            dealCardFromLeft(accelerateDecelerateInterpolator);
            dealCardFromRight(accelerateDecelerateInterpolator);
        }
        else if(!isDeal && stage == 3){ // stage 3 คือ ช่วงแสดงผลของเกม
            if(!keep1 || !keep2 || !keep3 || !keep4 || !keep5){ // เปลี่ยนไพ่ใบที่ไม่ได้เลือกไว้
                keepCard();
                showCard();
            }
            message = computeGame(); // set ผลลัพธ์ของเกม
            isDeal = true;
            calPoint(); // คำนวน Chip ที่ได้เมื่อจบเกม
            ((TextView) findViewById(R.id.msg)).setText(message);
            ((Button)findViewById(R.id.btnPlay)).setText("DEAL");
            ((Button)findViewById(R.id.btn1000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn2000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn3000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn5000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn10000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btnEnd)).setVisibility(Button.VISIBLE);
            stage = 1;

        }
        else if(stage == 2){ // stage 2 คือ ช่วงเลือกว่าจะเก็บไพ่ใบไหนไว้
            isDeal = false;
            drawCard(); // สุ่มไพ่ที่จะได้ครั้งแรก
            ((TextView) findViewById(R.id.msg)).setText("");
            ((Button)findViewById(R.id.btnPlay)).setText("OK");
            ((Button)findViewById(R.id.btn1000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn2000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn3000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn5000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btn10000)).setVisibility(Button.INVISIBLE);
            ((Button)findViewById(R.id.btnEnd)).setVisibility(Button.INVISIBLE);
            stage = 3;
        }
    }

    // ช่วง bet ของเกม
    public void betgame(){
        if(stage == 1){
            ((TextView) findViewById(R.id.msg)).setText("Place your bets.");
            ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier("cover","drawable",getPackageName()));
            ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier("cover","drawable",getPackageName()));
            ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier("cover","drawable",getPackageName()));
            ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier("cover","drawable",getPackageName()));
            ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier("cover","drawable",getPackageName()));

            ((Button)findViewById(R.id.btn1000)).setVisibility(Button.VISIBLE);
            ((Button)findViewById(R.id.btn2000)).setVisibility(Button.VISIBLE);
            ((Button)findViewById(R.id.btn3000)).setVisibility(Button.VISIBLE);
            ((Button)findViewById(R.id.btn5000)).setVisibility(Button.VISIBLE);
            ((Button)findViewById(R.id.btn10000)).setVisibility(Button.VISIBLE);
            ((Button)findViewById(R.id.btnPlay)).setText("BET");
            ((Button)findViewById(R.id.btnEnd)).setVisibility(Button.INVISIBLE);
            stage = 2;
        }
    }
    // set ค่าเริ่มเกม เมื่อเปิดหน้านี้
    public void newgame(){
        chipsData.point = 4000;
        chipsData.bet = 1000;
        isDeal = true;
    }
    // เมื่อกดปุ่ม End Game
    public void endGame(View v){
        int point = chipsData.point + chipsData.bet; // เก็บค่า Chip + ฺBet เพื่อนำไปเป็นคะแนน
        Intent intent = new Intent(Game.this, HighScore.class);
        intent.putExtra("score", point);
        startActivity(intent);
    }
    // สุ่มเลขไพ่
    public int randNum(){
        return 1 + (int) Math.round(Math.random() * (13 - 1));
    }
    // สุ่มดอกไพ่
    public int randSuit(){
        return 1 + (int) Math.round(Math.random() * (4 - 1));
    }
    // สุ่มไพ่ครั้งแรก
    public void drawCard(){
        //สุ่มไพ่ ใบที่ 1
        suit1 = randSuit();
        num1 = randNum();
        nameOfCard1 = "card_"+num1+suit1;

        //สุ่มไพ่ ใบที่ 2
        suit2 = randSuit();
        num2 = randNum();
        while(suit2 == suit1 && num2 == num1){
            suit2 = randSuit();
            num2 = randNum();
        }
        nameOfCard2 = "card_"+num2+suit2;

        //สุ่มไพ่ ใบที่ 3
        suit3 = randSuit();
        num3 = randNum();
        while((suit3 == suit1 && num3 == num1)||
                (suit3 == suit2 && num3 == num2)){
            suit3 = randSuit();
            num3 = randNum();
        }
        nameOfCard3 = "card_"+num3+suit3;

        //สุ่มไพ่ ใบที่ 4
        suit4 = randSuit();
        num4 = randNum();
        while((suit4 == suit1 && num4 == num1)||
                (suit4 == suit2 && num4 == num2) ||
                (suit4 == suit3 && num4 == num3)){
            suit4 = randSuit();
            num4 = randNum();
        }
        nameOfCard4 = "card_"+num4+suit4;

        //สุ่มไพ่ ใบที่ 5
        suit5 = randSuit();
        num5 = randNum();
        while((suit5 == suit1 && num5 == num1)||
                (suit5 == suit2 && num5 == num2) ||
                (suit5 == suit3 && num5 == num3) ||
                (suit5 == suit4 && num5 == num4)){
            suit5 = randSuit();
            num5 = randNum();
        }
        nameOfCard5 = "card_"+num5+suit5;

        //make card1 fade out then fade in by setting duration
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(400);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //not do anything in this period
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //when fade out end set new image to card1 then start fade in
                card1.setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(700);
                fadeIn.setFillAfter(true);
                card1.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do just one time so this period is not necessary
            }
        });
        card1.startAnimation(fadeOut);

        //make card2 fade out then fade in by setting duration
        fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(600);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //not do anything in this period
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //when fade out end set new image to card2 then start fade in
                card2.setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(900);
                fadeIn.setFillAfter(true);
                card2.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do just one time so this period is not necessary
            }
        });
        card2.startAnimation(fadeOut);

        //make card3 fade out then fade in by setting duration
        fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(200);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //not do anything in this period
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //when fade out end set new image to card3 then start fade in
                card3.setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(500);
                fadeIn.setFillAfter(true);
                card3.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do just one time so this period is not necessary
            }
        });
        card3.startAnimation(fadeOut);

        //make card4 fade out then fade in by setting duration
        fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(800);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //not do anything in this period
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //when fade out end set new image to card1 then start fade in
                card4.setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(1100);
                fadeIn.setFillAfter(true);
                card4.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do just one time so this period is not necessary
            }
        });
        card4.startAnimation(fadeOut);

        //make card5 fade out then fade in by setting duration
        fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(0);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //not do anything in this period
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //when fade out end set new image to card1 then start fade in
                card5.setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(300);
                fadeIn.setFillAfter(true);
                card5.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do just one time so this period is not necessary
            }
        });
        card5.startAnimation(fadeOut);
    }

    //แสดง Animation ในส่วนของการแจกไพ่ในการเคลื่อนที่ในเเนวเเกน Y (ไพ่ใบที่1)
    public void dealCard(TimeInterpolator timeInterpolator)
    {
        //ความสูงของ Layout
        float h = (float)background.getHeight();
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ของไพ่ใบที่1
        float card1PropertyEnd = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ของไพ่ใบที่1
        float card1PropertyStart = -(h/2 - (float)card1.getHeight()/2);
        //กำหนดการเคลื่อนที่เป็นแนวแกน Y
        String propertyYname = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card1, propertyYname, card1PropertyStart, card1PropertyEnd);
        //กำหนดระยะเวลาการทำ Animation ของไพ่ใบที่1
        objectAnimator.setDuration(400);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
    }

    //แสดง Animation ในส่วนของการแจกไพ่ในการเคลื่อนที่ในเเนวเเกน X,Y ที่มีค่าเป็นลบ (ไพ่ใบที่3,5)
    public void dealCardFromLeft(TimeInterpolator timeInterpolator)
    {
        //ความกว้างของ Layout
        float w = (float)background.getWidth();
        //ความสูงของ Layout
        float h = (float)background.getHeight();
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่3
        float card3PropertyEnd = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่3
        float card3PropertyStart = -(h/2 - (float)card3.getHeight()/2);
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่3
        float card3PropertyEndX = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่3
        float card3PropertyStartX = -(w/5 - (float)card3.getWidth()/5);
        //กำหนดการเคลื่อนที่เป็นแนวแกน Y
        String propertyYname = "translationY";
        //กำหนดการเคลื่อนที่เป็นแนวแกน X
        String propertyXname = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card3, propertyYname, card3PropertyStart, card3PropertyEnd);
        ObjectAnimator objectAnimatorX
                = ObjectAnimator.ofFloat(card3, propertyXname,card3PropertyStartX,card3PropertyEndX );
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน Y ของไพ่ใบที่3
        objectAnimator.setDuration(400);
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน X ของไพ่ใบที่3
        objectAnimatorX.setDuration(400);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimatorX.setInterpolator(timeInterpolator);
        objectAnimator.start();
        objectAnimatorX.start();

        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่5
        float card5PropertyEnd = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่5
        float card5PropertyStart = -(h/2 - (float)card5.getHeight()/2);
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่5
        float card5PropertyEndX = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่5
        float card5PropertyStartX = -(w/2 - (float)card5.getWidth()/2);

        ObjectAnimator objectAnimator2
                = ObjectAnimator.ofFloat(card5, propertyYname, card5PropertyStart, card5PropertyEnd);
        ObjectAnimator objectAnimatorX2
                = ObjectAnimator.ofFloat(card5, propertyXname,card5PropertyStartX,card5PropertyEndX );
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน Y ของไพ่ใบที่5
        objectAnimator2.setDuration(400);
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน X ของไพ่ใบที่5
        objectAnimatorX2.setDuration(400);
        objectAnimator2.setInterpolator(timeInterpolator);
        objectAnimatorX2.setInterpolator(timeInterpolator);
        objectAnimator2.start();
        objectAnimatorX2.start();
    }

    //แสดง Animation ในส่วนของการแจกไพ่ในการเคลื่อนที่ในเเนวเเกน X,Y ที่มีค่าเป็นบวก (ไพ่ใบที่2,4)
    public void dealCardFromRight(TimeInterpolator timeInterpolator)
    {
        //ความกว้างของ Layout
        float w = (float)background.getWidth();
        //ความสูงของ Layout
        float h = (float)background.getHeight();
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่2
        float card2PropertyEnd = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่2
        float card2PropertyStart = -(h/2 - (float)card2.getHeight()/2);
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่2
        float card2PropertyEndX = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่2
        float card2PropertyStartX = (w/5 - (float)card2.getWidth()/5);
        //กำหนดการเคลื่อนที่เป็นแนวแกน Y
        String propertyYname = "translationY";
        //กำหนดการเคลื่อนที่เป็นแนวแกน X
        String propertyXname = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card2, propertyYname, card2PropertyStart, card2PropertyEnd);
        ObjectAnimator objectAnimatorX
                = ObjectAnimator.ofFloat(card2, propertyXname,card2PropertyStartX,card2PropertyEndX );
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน Y ของไพ่ใบที่2
        objectAnimator.setDuration(400);
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน X ของไพ่ใบที่2
        objectAnimatorX.setDuration(400);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimatorX.setInterpolator(timeInterpolator);
        objectAnimator.start();
        objectAnimatorX.start();
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่4
        float card4PropertyEnd = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน Y ของไพ่ใบที่4
        float card4PropertyStart = -(h/2 - (float)card4.getHeight()/2);
        //ตำแหน่งสิ้นสุดของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่4
        float card4PropertyEndX = 0f;
        //ตำแหน่งเริ่มต้นของการเเสดง Animation ในแนวแกน X ของไพ่ใบที่4
        float card4PropertyStartX = (w/2 - (float)card4.getWidth()/2);

        ObjectAnimator objectAnimator2
                = ObjectAnimator.ofFloat(card4, propertyYname, card4PropertyStart, card4PropertyEnd);
        ObjectAnimator objectAnimatorX2
                = ObjectAnimator.ofFloat(card4, propertyXname,card4PropertyStartX,card4PropertyEndX );
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน Y ของไพ่ใบที่2
        objectAnimator2.setDuration(400);
        //กำหนดระยะเวลาการทำ Animation ในแนวแกน X ของไพ่ใบที่2
        objectAnimatorX2.setDuration(400);
        objectAnimator2.setInterpolator(timeInterpolator);
        objectAnimatorX2.setInterpolator(timeInterpolator);
        objectAnimator2.start();
        objectAnimatorX2.start();
    }

    // สุ่มไพ่ใบที่ 1 ใหม่
    public void pickCard1(){
        suit1 = randSuit();
        num1 = randNum();
        while((suit1 == suit2 && num1 == num2) ||
                (suit1 == suit3 && num1 == num3) ||
                (suit1 == suit4 && num1 == num4) ||
                (suit1 == suit5 && num1 == num5))
        {
            suit1 = randSuit();
            num1 = randNum();
        }
        nameOfCard1 = "card_"+num1+suit1;
        ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));
    }
    // สุ่มไพ่ใบที่ 2 ใหม่
    public void pickCard2(){
        suit2 = randSuit();
        num2 = randNum();
        while((suit2 == suit1 && num2 == num1) ||
                (suit2 == suit3 && num2 == num3) ||
                (suit2 == suit4 && num2 == num4) ||
                (suit2 == suit5 && num2 == num5))
        {
            suit2 = randSuit();
            num2 = randNum();
        }
        nameOfCard2 = "card_"+num2+suit2;
        ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));
    }
    // สุ่มไพ่ใบที่ 3 ใหม่
    public void pickCard3(){
        suit3 = randSuit();
        num3 = randNum();
        while((suit3 == suit1 && num3 == num1) ||
                (suit3 == suit2 && num3 == num2) ||
                (suit3 == suit4 && num3 == num4) ||
                (suit3 == suit5 && num3 == num5))
        {
            suit3 = randSuit();
            num3 = randNum();
        }
        nameOfCard3 = "card_"+num3+suit3;
        ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));
    }
    // สุ่มไพ่ใบที่ 4 ใหม่
    public void pickCard4(){
        suit4 = randSuit();
        num4 = randNum();
        while((suit4 == suit1 && num4 == num1) ||
                (suit4 == suit2 && num4 == num2) ||
                (suit4 == suit3 && num4 == num3) ||
                (suit4 == suit5 && num4 == num5))
        {
            suit4 = randSuit();
            num4 = randNum();
        }
        nameOfCard4 = "card_"+num4+suit4;
        ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));
    }
    // สุ่มไพ่ใบที่ 5 ใหม่
    public void pickCard5(){
        suit5 = randSuit();
        num5 = randNum();
        while((suit5 == suit1 && num5 == num1) ||
                (suit5 == suit2 && num5 == num2) ||
                (suit5 == suit3 && num5 == num3) ||
                (suit5 == suit4 && num5 == num4))
        {
            suit5 = randSuit();
            num5 = randNum();
        }
        nameOfCard5 = "card_"+num5+suit5;
        ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
    }

    // เมื่อแตะที่ไพ่ ให้ไพ่ใบที่ 1 ขึ้น KEEP
    public void keepCard1(View v){
        if(!isDeal){
            if (!keep1){
                ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1+"k","drawable",getPackageName()));
                keep1 = true;
            }
            else{
                ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));
                keep1 = false;
            }
        }
    }
    // เมื่อแตะที่ไพ่ ให้ไพ่ใบที่ 2 ขึ้น KEEP
    public void keepCard2(View v){
        if(!isDeal){
            if (!keep2){
                ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2+"k","drawable",getPackageName()));
                keep2 = true;
            }
            else{
                ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));
                keep2 = false;
            }
        }
    }
    // เมื่อแตะที่ไพ่ ให้ไพ่ใบที่ 3 ขึ้น KEEP
    public void keepCard3(View v){
        if(!isDeal){
            if (!keep3){
                ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3+"k","drawable",getPackageName()));
                keep3 = true;
            }
            else{
                ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));
                keep3 = false;
            }
        }
    }
    // เมื่อแตะที่ไพ่ ให้ไพ่ใบที่ 4 ขึ้น KEEP
    public void keepCard4(View v){
        if(!isDeal){
            if (!keep4){
                ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4+"k","drawable",getPackageName()));
                keep4 = true;
            }
            else{
                nameOfCard4 = "card_"+num4+suit4;
                ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));
                keep4 = false;
            }
        }
    }
    // เมื่อแตะที่ไพ่ ให้ไพ่ใบที่ 5 ขึ้น KEEP
    public void keepCard5(View v){
        if(!isDeal){
            if (!keep5){
                ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5+"k","drawable",getPackageName()));
                keep5 = true;
            }
            else{
                ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
                keep5 = false;
            }
        }
    }

    // ตรวจสอบไพ่แต่ละใบว่า ได้กด KEEP ไว้หรือไม่
    public void keepCard(){
        if(!keep1){pickCard1();}
        if(!keep2){pickCard2();}
        if(!keep3){pickCard3();}
        if(!keep4){pickCard4();}
        if(!keep5){pickCard5();}
    }
    // แสดงไพ่ที่ได้ตอนสิ้นสุดเกม
    public void showCard(){
        if(keep1){
            ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));
            keep1 = false;
        }
        if(keep2){
            ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));
            keep2 = false;
        }
        if(keep3){
            ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));
            keep3 = false;
        }
        if(keep4){
            ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));
            keep4 = false;
        }
        if(keep5){
            ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
            keep5 = false;
        }
    }
    // วิเคราะห์ไพ่ที่ได้อยู่ Hand Ranks ระดับใด
    public String computeGame(){
        int num[] = {num1, num2, num3, num4, num5};
        Arrays.sort(num);
        if(suit1 == suit2 && suit2 == suit3 && suit3 == suit4 && suit4 == suit5){
            if(num[0] == 1 && num[1] == 10 && num[2] == 11 && num[3] == 12 && num[4] == 13){
                return "Royal Straight Flush";
            }
            else if(num[4]-num[3] == 1 && num[3]-num[2] == 1 && num[2]-num[1] == 1 && num[1]-num[0] == 1){
                return "Straight Flush";
            }
        }
        else if(num[0] == num[3] && num[1] != num[4]){
            return "Four of a kind";
        }
        int FullHouse = 0;
        for(int i=1; i<5; ++i){
            if(num[i]==num[i-1]){
                ++FullHouse;
            }
        }
        if(FullHouse == 3){
            return "Full House";
        }
        if(suit1 == suit2 && suit2 == suit3 && suit3 == suit4 && suit4 == suit5){
            return "Flush";
        }
        else if(num[4]-num[3] == 1 && num[3]-num[2] == 1 && num[2]-num[1] == 1 && num[1]-num[0] == 1){
            return "Straight";
        }
        if(num[0] == num[2] || num[1] == num[3] || num[2] == num[4]){
            return "Three of a kind";
        }
        int TwoPair = 0;
        for(int i=1; i<5; ++i){
            if(num[i]==num[i-1]){
                ++TwoPair;
            }
        }
        if(TwoPair == 2){
            return "Two pair";
        }
        int OnePair = 0;
        for(int i=1; i<5; ++i){
            if(num[i]==num[i-1]){
                ++OnePair;
            }
        }
        if(OnePair == 1){
            return "One pair";
        }
        return "High card";

    }

    // คำนวณ Chip กับ Bet หลังเล่นจบ 1 รอบ
    public void computeChips(){
        if(chipsData.point < 0){
            chipsData.point = -1;
        }
        checkPoint();
        if(chipsData.point < 0){
            chipsData.point = 0;
        }
        DecimalFormat form = new DecimalFormat("#,###");
        TextView pointTextView = (TextView)findViewById(R.id.txtPoint);
        String pointText = "Chip : "+form.format(chipsData.point);
        pointTextView.setText(pointText);

        TextView betTextView = (TextView)findViewById(R.id.txtBet);
        String betText = "Bet : "+form.format(chipsData.bet);
        betTextView.setText(betText);
    }
    // เมื่อกดปุ่ม +1,000 จะหัก Chip ลง 1000 และไปเพิ่มค่า Bet 1000
    public void bet1000(View v){
        if(chipsData.point < 1000){
            return;
        }
        if(isDeal){
            chipsData.bet += 1000;
            chipsData.point -= 1000;
        }
        computeChips();
    }
    // เมื่อกดปุ่ม +2,000 จะหัก Chip ลง 2000 และไปเพิ่มค่า Bet 2000
    public void bet2000(View v){
        if(chipsData.point < 2000){
            return;
        }
        if(isDeal){
            chipsData.bet += 2000;
            chipsData.point -= 2000;
        }
        computeChips();
    }
    // เมื่อกดปุ่ม +3,000 จะหัก Chip ลง 3000 และไปเพิ่มค่า Bet 3000
    public void bet3000(View v){
        if(chipsData.point < 3000){
            return;
        }
        if(isDeal){
            chipsData.bet += 3000;
            chipsData.point -= 3000;
        }
        computeChips();
    }
    // เมื่อกดปุ่ม +5,000 จะหัก Chip ลง 5000 และไปเพิ่มค่า Bet 5000
    public void bet5000(View v){
        if(chipsData.point < 5000){
            return;
        }
        if(isDeal){
            chipsData.bet += 5000;
            chipsData.point -= 5000;
        }
        computeChips();
    }
    // เมื่อกดปุ่ม +10,000 จะหัก Chip ลง 10000 และไปเพิ่มค่า Bet 10000
    public void bet10000(View v){
        if(chipsData.point < 10000){
            return;
        }
        if(isDeal){
            chipsData.bet += 10000;
            chipsData.point -= 10000;
        }
        computeChips();
    }
    // คำนวณ Chip ที่ได้หลังจบเกม 1 รอบ
    public void calPoint(){
        int point = chipsData.point;
        int bet = chipsData.bet;
        if(message.equals("One pair") || message.equals("High card")){
            point -= 1000;
            chipsData.point = point;
        }
        else{
            point += bet;
            if(message.equals("Royal Straight Flush")){
                point += (bet * 250);
            }
            else if (message.equals("Straight Flush")){
                point += (bet * 60);
            }
            else if (message.equals("Four of a kind")){
                point += (bet * 25);
            }
            else if (message.equals("Full House")){
                point += (bet * 20);
            }
            else if (message.equals("Flush")){
                point += (bet * 10);
            }
            else if (message.equals("Straight")){
                point += (bet * 5);
            }
            else if (message.equals("Three of a kind")){
                point += (bet * 3);
            }
            else if(message.equals("Two pair")){
                point += bet;
            }
            chipsData.point = point-1000;
        }
        if(point < 0){
            point = 0;
        }
        chipsData.bet = 1000;
        computeChips();
    }
    // เช็คว่าจำนวนของ Chip หมดรึยัง
    public void checkPoint(){
        if(chipsData.point < 0){
            ((Button)findViewById(R.id.btnPlay)).setVisibility(Button.INVISIBLE);
            chipsData.bet = 0;
            AlertDialog.Builder dialog = new AlertDialog.Builder(Game.this);
            dialog.setTitle("GAME OVER");
            dialog.setMessage("จำนวน Chip ของคุณหมดแล้ว");
            dialog.show();

        }
    }

}