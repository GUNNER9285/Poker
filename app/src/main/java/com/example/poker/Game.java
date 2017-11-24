package com.example.poker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poker.model.Card;
import com.example.poker.model.Deck;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Game extends AppCompatActivity {

    String nameOfCard1 = "";
    String nameOfCard2 = "";
    String nameOfCard3 = "";
    String nameOfCard4 = "";
    String nameOfCard5 = "";

    int Cardsuit1 = 0;
    int Cardsuit2 = 0;
    int Cardsuit3 = 0;
    int Cardsuit4 = 0;
    int Cardsuit5 = 0;
    int suit1 = 0;
    int suit2 = 0;
    int suit3 = 0;
    int suit4 = 0;
    int suit5 = 0;

    int Cardnum1 = 0;
    int Cardnum2 = 0;
    int Cardnum3 = 0;
    int Cardnum4 = 0;
    int Cardnum5 = 0;
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

    int coin = 0;
    String message = "Royal Straight Flush";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        drawCard();
        computeCoin();
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
        Cardsuit1 = suit1;
        Cardnum1 = num1;
        nameOfCard1 = "card_"+num1+suit1;
        ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));

        //Card 2
        suit2 = randSuit();
        num2 = randNum();
        while(suit2 == suit1 && num2 == num1){
            num2 = randNum();
        }
        Cardsuit2 = suit2;
        Cardnum2 = num2;
        nameOfCard2 = "card_"+num2+suit2;
        ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));

        //Card 3
        suit3 = randSuit();
        num3 = randNum();
        while((suit3 == suit1 && num3 == num1)||(suit3 == suit2 && num3 == num2)){
            num3 = randNum();
        }
        Cardsuit3 = suit3;
        Cardnum3 = num3;
        nameOfCard3 = "card_"+num3+suit3;
        ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));

        //Card 4
        suit4 = randSuit();
        num4 = randNum();
        while((suit4 == suit1 && num4 == num1)||(suit4 == suit2 && num4 == num2) || (suit4 == suit3 && num4 == num3)){
            num4 = randNum();
        }
        Cardsuit4 = suit4;
        Cardnum4 = num4;
        nameOfCard4 = "card_"+num4+suit4;
        ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));

        //Card 5
        suit5 = randSuit();
        num5 = randNum();
        while((suit5 == suit1 && num5 == num1)||(suit5 == suit2 && num5 == num2) || (suit5 == suit3 && num5 == num3) || (suit5 == suit4 && num5 == num4)){
            num5 = randNum();
        }
        Cardsuit5 = suit5;
        Cardnum5 = num5;
        nameOfCard5 = "card_"+num5+suit5;
        ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
    }

    public void pickCard1(){
        suit1 = randSuit();
        num1 = randNum();
        while((suit1 == suit2 && num1 == num2)||(suit1 == suit3 && num1 == num3) || (suit1 == suit4 && num1 == num4) || (suit1 == suit5 && num1 == num5)){
            num1 = randNum();
        }
        Cardsuit1 = suit1;
        Cardnum1 = num1;
        nameOfCard1 = "card_"+num1+suit1;
        ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));
    }
    public void pickCard2(){
        suit2 = randSuit();
        num2 = randNum();
        while((suit2 == suit1 && num1 == num1)||(suit2 == suit3 && num2 == num3) || (suit2 == suit4 && num2 == num4) || (suit2 == suit5 && num2 == num5)){
            num2 = randNum();
        }
        Cardsuit2 = suit2;
        Cardnum2 = num2;
        nameOfCard2 = "card_"+num2+suit2;
        ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));
    }
    public void pickCard3(){
        suit3 = randSuit();
        num3 = randNum();
        while((suit3 == suit1 && num3 == num1)||(suit3 == suit2 && num3 == num2) || (suit3 == suit4 && num3 == num4) || (suit3 == suit5 && num3 == num5)){
            num3 = randNum();
        }
        Cardsuit3 = suit3;
        Cardnum3 = num3;
        nameOfCard3 = "card_"+num3+suit3;
        ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));
    }
    public void pickCard4(){
        suit4 = randSuit();
        num4 = randNum();
        while((suit4 == suit1 && num4 == num1)||(suit4 == suit2 && num4 == num2) || (suit4 == suit3 && num4 == num3) || (suit4 == suit5 && num4 == num5)){
            num4 = randNum();
        }
        Cardsuit4 = suit4;
        Cardnum4 = num4;
        nameOfCard4 = "card_"+num4+suit4;
        ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));
    }
    public void pickCard5(){
        suit5 = randSuit();
        num5 = randNum();
        while((suit5 == suit1 && num5 == num1)||(suit5 == suit2 && num5 == num2) || (suit5 == suit3 && num5 == num3) || (suit5 == suit4 && num5 == num4)){
            num5 = randNum();
        }
        Cardsuit5 = suit5;
        Cardnum5 = num5;
        nameOfCard5 = "card_"+num5+suit5;
        ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
    }

    public void keepCard1(View v){
        if (!keep1){
            ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier("keep","drawable",getPackageName()));
            keep1 = true;
        }
        else{
            ((ImageView) findViewById(R.id.card1)).setImageResource(getResources().getIdentifier(nameOfCard1,"drawable",getPackageName()));
            keep1 = false;
        }
    }
    public void keepCard2(View v){
        if (!keep2){
            ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier("keep","drawable",getPackageName()));
            keep2 = true;
        }
        else{
            ((ImageView) findViewById(R.id.card2)).setImageResource(getResources().getIdentifier(nameOfCard2,"drawable",getPackageName()));
            keep2 = false;
        }
    }
    public void keepCard3(View v){
        if (!keep3){
            ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier("keep","drawable",getPackageName()));
            keep3 = true;
        }
        else{
            ((ImageView) findViewById(R.id.card3)).setImageResource(getResources().getIdentifier(nameOfCard3,"drawable",getPackageName()));
            keep3 = false;
        }
    }
    public void keepCard4(View v){
        if (!keep4){
            ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier("keep","drawable",getPackageName()));
            keep4 = true;
        }
        else{
            nameOfCard4 = "card_"+num4+suit4;
            ((ImageView) findViewById(R.id.card4)).setImageResource(getResources().getIdentifier(nameOfCard4,"drawable",getPackageName()));
            keep4 = false;
        }
    }
    public void keepCard5(View v){
        if (!keep5){
            ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier("keep","drawable",getPackageName()));
            keep5 = true;
        }
        else{
            ((ImageView) findViewById(R.id.card5)).setImageResource(getResources().getIdentifier(nameOfCard5,"drawable",getPackageName()));
            keep5 = false;
        }
    }

    public void computeCoin(){
        DecimalFormat form = new DecimalFormat("#,###");
        TextView coinTextView = (TextView)findViewById(R.id.coin);
        String cointText = "Coin : "+form.format(coin);
        coinTextView.setText(cointText);
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
        int num[] = new int[5];
        num[0] = num1;
        num[1] = num2;
        num[2] = num3;
        num[3] = num4;
        num[4] = num5;
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
        if(num[0] == num[3] || num[1] == num[4]){
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

    public void play(View v){
        keepCard();
        showCard();
        message = computeGame();
        ((TextView) findViewById(R.id.msg)).setText(message);
    }
}