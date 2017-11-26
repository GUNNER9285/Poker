package com.example.poker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        betgame();
        newgame();
    }

    @Override
    public void onBackPressed() {}

    public void playGame(View v){
        if (stage == 1){
            ((TextView) findViewById(R.id.msg)).setText("");
            betgame();
        }
        else if(!isDeal && stage == 3){
            if(!keep1 || !keep2 || !keep3 || !keep4 || !keep5){
                keepCard();
                showCard();
            }
            message = computeGame();
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
        suit1 = randSuit();
        num1 = randNum();
        nameOfCard1 = "card_"+num1+suit1;
        ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));

        //Card 2
        suit2 = randSuit();
        num2 = randNum();
        while(suit2 == suit1 && num2 == num1){
            suit2 = randSuit();
            num2 = randNum();
        }
        nameOfCard2 = "card_"+num2+suit2;
        ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));

        //Card 3
        suit3 = randSuit();
        num3 = randNum();
        while((suit3 == suit1 && num3 == num1)||(suit3 == suit2 && num3 == num2)){
            suit3 = randSuit();
            num3 = randNum();
        }
        nameOfCard3 = "card_"+num3+suit3;
        ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));

        //Card 4
        suit4 = randSuit();
        num4 = randNum();
        while((suit4 == suit1 && num4 == num1)||(suit4 == suit2 && num4 == num2) || (suit4 == suit3 && num4 == num3)){
            suit4 = randSuit();
            num4 = randNum();
        }
        nameOfCard4 = "card_"+num4+suit4;
        ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));

        //Card 5
        suit5 = randSuit();
        num5 = randNum();
        while((suit5 == suit1 && num5 == num1)||(suit5 == suit2 && num5 == num2) || (suit5 == suit3 && num5 == num3) || (suit5 == suit4 && num5 == num4)){
            suit5 = randSuit();
            num5 = randNum();
        }
        nameOfCard5 = "card_"+num5+suit5;
        ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
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