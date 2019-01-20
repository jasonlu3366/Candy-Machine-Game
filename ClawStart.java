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

public class ClawStart {
    public static int height = 400;
    public static int width = 600;
    public static int xSize = 50;
    public static int ySize = 50;
    public static String background = "candystart.png";
    public static String text = "candystart2.png";

    public static void main(String[] args) {
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        draw();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 's') { //move left
                	Claw.play();
                }
            }
        } 
    } 

    public static void draw() {
        StdAudio.loop("clawmusic.wav");
        StdDraw.picture(width/2, height/2, background, 600, 400);
        StdDraw.picture(width/2, height/2 - 100, text, 200, 70);
        StdDraw.show(200);
        StdDraw.clear();
    }
}
