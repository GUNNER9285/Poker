package com.example.poker;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.poker.model.Chips;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Game extends AppCompatActivity {
    String nameOfCard1 = "";
    String nameOfCard2 = "";
    String nameOfCard3 = "";
    String nameOfCard4 = "";
    String nameOfCard5 = "";

    int suit1 = 0;
    int suit2 = 0;
    int suit3 = 0;
    int suit4 = 0;
    int suit5 = 0;

    int num1 = 0;
    int num2 = 0;
    int num3 = 0;
    int num4 = 0;
    int num5 = 0;

    boolean keep1 = false;
    boolean keep2 = false;
    boolean keep3 = false;
    boolean keep4 = false;
    boolean keep5 = false;

    String message = "";
    boolean isDeal = true;
    int stage = 1;
    private final ChipsData chipsData = ChipsData.getInstance();   // global

    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    ImageView card5;
    RelativeLayout background;
    AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        background = (RelativeLayout) findViewById(R.id.background);
        betgame();
        newgame();
    }

    @Override
    public void onBackPressed() {}

    public void playGame(View v){
        if (stage == 1){
            ((TextView) findViewById(R.id.msg)).setText("");
            betgame();
            dealCard(accelerateDecelerateInterpolator);
            dealCardFromLeft(accelerateDecelerateInterpolator);
            dealCardFromRight(accelerateDecelerateInterpolator);
        }
        else if(!isDeal && stage == 3){
            if(!keep1 || !keep2 || !keep3 || !keep4 || !keep5){
                keepCard();
                showCard();
            }
            message = computeGame();
            isDeal = true;
            calPoint();
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
        else if(stage == 2){
            isDeal = false;
            drawCard();
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

    public void betgame(){
        if(stage == 1){
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
    public void newgame(){
        chipsData.point = 4000;
        chipsData.bet = 1000;
        isDeal = true;
    }
    public void endGame(View v){
        Intent end = new Intent(Game.this, HighScore.class);
        startActivity(end);
    }

    public int randNum(){
        return 1 + (int) Math.round(Math.random() * (13 - 1));
    }
    public int randSuit(){
        return 1 + (int) Math.round(Math.random() * (4 - 1));
    }

    public void drawCard(){
        //Card 1
        card1 = ((ImageView) findViewById(R.id.card1));
        suit1 = randSuit();
        num1 = randNum();
        nameOfCard1 = "card_"+num1+suit1;

        //Card 2
        card2 = ((ImageView) findViewById(R.id.card2));
        suit2 = randSuit();
        num2 = randNum();
        while(suit2 == suit1 && num2 == num1){
            suit2 = randSuit();
            num2 = randNum();
        }
        nameOfCard2 = "card_"+num2+suit2;

        //Card 3
        card3 = ((ImageView) findViewById(R.id.card3));
        suit3 = randSuit();
        num3 = randNum();
        while((suit3 == suit1 && num3 == num1)||(suit3 == suit2 && num3 == num2)){
            suit3 = randSuit();
            num3 = randNum();
        }
        nameOfCard3 = "card_"+num3+suit3;

        //Card 4
        card4 = ((ImageView) findViewById(R.id.card4));
        suit4 = randSuit();
        num4 = randNum();
        while((suit4 == suit1 && num4 == num1)||(suit4 == suit2 && num4 == num2) || (suit4 == suit3 && num4 == num3)){
            suit4 = randSuit();
            num4 = randNum();
        }
        nameOfCard4 = "card_"+num4+suit4;

        //Card 5
        card5 = ((ImageView) findViewById(R.id.card5));
        suit5 = randSuit();
        num5 = randNum();
        while((suit5 == suit1 && num5 == num1)||(suit5 == suit2 && num5 == num2) || (suit5 == suit3 && num5 == num3) || (suit5 == suit4 && num5 == num4)){
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

    //animation that show dealing card that process only Y axis

    public void dealCard(TimeInterpolator timeInterpolator)
    {
        //float w = (float)background.getWidth();
        float h = (float)background.getHeight();
        float card1PropertyEnd = 0f;
        float card1PropertyStart = -(h/2 - (float)card1.getHeight()/2);
        String propertyYname = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card1, propertyYname, card1PropertyStart, card1PropertyEnd);
        objectAnimator.setDuration(400);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
    }

    //animation that show dealing card that process X,Y axis that be minus value

    public void dealCardFromLeft(TimeInterpolator timeInterpolator)
    {
        float w = (float)background.getWidth();
        float h = (float)background.getHeight();
        float card3PropertyEnd = 0f;
        float card3PropertyStart = -(h/2 - (float)card3.getHeight()/2);
        float card3PropertyEndX = 0f;
        float card3PropertyStartX = -(w/5 - (float)card3.getWidth()/5);
        String propertyYname = "translationY";
        String propertyXname = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card3, propertyYname, card3PropertyStart, card3PropertyEnd);
        ObjectAnimator objectAnimatorX
                = ObjectAnimator.ofFloat(card3, propertyXname,card3PropertyStartX,card3PropertyEndX );
        objectAnimator.setDuration(400);
        objectAnimatorX.setDuration(400);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimatorX.setInterpolator(timeInterpolator);
        objectAnimator.start();
        objectAnimatorX.start();

        float card5PropertyEnd = 0f;
        float card5PropertyStart = -(h/2 - (float)card5.getHeight()/2);
        float card5PropertyEndX = 0f;
        float card5PropertyStartX = -(w/2 - (float)card5.getWidth()/2);

        ObjectAnimator objectAnimator2
                = ObjectAnimator.ofFloat(card5, propertyYname, card5PropertyStart, card5PropertyEnd);
        ObjectAnimator objectAnimatorX2
                = ObjectAnimator.ofFloat(card5, propertyXname,card5PropertyStartX,card5PropertyEndX );
        objectAnimator2.setDuration(400);
        objectAnimatorX2.setDuration(400);
        objectAnimator2.setInterpolator(timeInterpolator);
        objectAnimatorX2.setInterpolator(timeInterpolator);
        objectAnimator2.start();
        objectAnimatorX2.start();
    }

    //animation that show dealing card that process X,Y axis that be plus value

    public void dealCardFromRight(TimeInterpolator timeInterpolator)
    {
        float w = (float)background.getWidth();
        float h = (float)background.getHeight();
        float card2PropertyEnd = 0f;
        float card2PropertyStart = -(h/2 - (float)card2.getHeight()/2);
        float card2PropertyEndX = 0f;
        float card2PropertyStartX = (w/5 - (float)card2.getWidth()/5);
        String propertyYname = "translationY";
        String propertyXname = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card2, propertyYname, card2PropertyStart, card2PropertyEnd);
        ObjectAnimator objectAnimatorX
                = ObjectAnimator.ofFloat(card2, propertyXname,card2PropertyStartX,card2PropertyEndX );
        objectAnimator.setDuration(400);
        objectAnimatorX.setDuration(400);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimatorX.setInterpolator(timeInterpolator);
        objectAnimator.start();
        objectAnimatorX.start();

        float card4PropertyEnd = 0f;
        float card4PropertyStart = -(h/2 - (float)card4.getHeight()/2);
        float card4PropertyEndX = 0f;
        float card4PropertyStartX = (w/2 - (float)card4.getWidth()/2);

        ObjectAnimator objectAnimator2
                = ObjectAnimator.ofFloat(card4, propertyYname, card4PropertyStart, card4PropertyEnd);
        ObjectAnimator objectAnimatorX2
                = ObjectAnimator.ofFloat(card4, propertyXname,card4PropertyStartX,card4PropertyEndX );
        objectAnimator2.setDuration(400);
        objectAnimatorX2.setDuration(400);
        objectAnimator2.setInterpolator(timeInterpolator);
        objectAnimatorX2.setInterpolator(timeInterpolator);
        objectAnimator2.start();
        objectAnimatorX2.start();
    }

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

    public void keepCard(){
        if(!keep1){pickCard1();}
        if(!keep2){pickCard2();}
        if(!keep3){pickCard3();}
        if(!keep4){pickCard4();}
        if(!keep5){pickCard5();}
    }
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
    public void bet1000(View v){
        if(isDeal){
            chipsData.bet += 1000;
            chipsData.point -= 1000;
        }
        computeChips();
    }
    public void bet2000(View v){
        if(isDeal){
            chipsData.bet += 2000;
            chipsData.point -= 2000;
        }
        computeChips();
    }
    public void bet3000(View v){
        if(isDeal){
            chipsData.bet += 3000;
            chipsData.point -= 3000;
        }
        computeChips();
    }
    public void bet5000(View v){
        if(isDeal){
            chipsData.bet += 5000;
            chipsData.point -= 5000;
        }
        computeChips();
    }
    public void bet10000(View v){
        if(isDeal){
            chipsData.bet += 10000;
            chipsData.point -= 10000;
        }
        computeChips();
    }
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
    public void checkPoint(){
        if(chipsData.point < 0){
            Intent end = new Intent(Game.this, HighScore.class);
            startActivity(end);
        }
    }

}