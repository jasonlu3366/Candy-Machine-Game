//*******************************************************************
//
//   File: PlayHangman.java          Assignment No.: 5
//
//   Author: Jason Huishen Lu      Email: <huishen.lu@yale.edu>
//
//   Class: CS 112
//
//   Time spent on this problem: 5 hours
//   --------------------
//      This program executes the hangman game and provide users with a choice
//      to play.
//
//*******************************************************************
import java.util.Scanner;
//TODO
//make a draw class to reduce redundancy
public class Coin {
    public static int height = 400;
    public static int width = 600;
    public static int xSize = 50;
    public static int ySize = 50;
    public static String background = "machine.png";
    public static String text = "candystart2.png";
    public static int totalCoin = 10;

    public static void main(String[] args) {
   
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        coinDrag();   
    }
    
    public static void coinDrag () {
    	double x =200;
    	double y = 200;
    	double clickX = 200;
    	double clickY = 200;
    	draw(x,y);
    	
    	
        while  ((clickX <500 || clickX>525) || (clickY <56 || clickY>70))  {
        	 x = StdDraw.mouseX();
             y = StdDraw.mouseY();
   
            if (StdDraw.mousePressed()) {
            	clickX = x;
            	clickY = y;
	            draw(x,y);
            }
        }
        totalCoins();
        
    }
    
    public static void totalCoins() {
    	totalCoin--;
    }
    
    public static void draw(double x, double y) {
    	StdDraw.picture(width / 2, height / 2, background, 600, 400);
        StdDraw.rectangle(300, 300, 100, 20);
        
        StdDraw.text(300, 300,"Coins Left: " + totalCoin);
    	
        StdDraw.picture(x, y, "coin.png", 40, 40);
        StdDraw.show(10);
        StdDraw.clear();
    }
 } // end of class

  

