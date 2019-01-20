import java.util.Scanner;
import java.util.*;

//TODO
// animation for claw closing
// make the game repeat/add ending 

public class Claw {
    public static int height = 400;
    public static int width = 600;
    public static int xOriginal = 200;
    public static int yOriginal = height-70;
    public static int xSize = 50;
    public static int ySize = 50;
    public static String background = "machine.png";
    
    public static int[] xcoord; // x position (m)
    public static int[] ycoord;
    public static String[] image;
    
    public static int N = 10;
    public static int num = 0;

    public static void main(String[] args) {
    	play();	
    }

    public static void play() {
    	int x = xOriginal;
        int y = yOriginal;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenColor(StdDraw.BLUE);
        //StdAudio.play("lastseconds.wav");

        Coin.coinDrag();
        pit(x,y);
        
        while (true) { //make this while statement the user quit key
        	control(x, y);
        	Coin.coinDrag();
        	draw(x, y, "claw1.png");
        }     
    }

    public static void control(int x, int y) {
    	int done = 0; 
    	x = xOriginal;
         y = yOriginal;
    	while (done !=1) {
        	if ( StdDraw.isKeyPressed(37) && x!=50) { //move left
            	x -= 5;
                draw(x, y, "claw1.png");    
            }
        	else if ( StdDraw.isKeyPressed(39) && x != 550) { //move right
            	x += 5;
                draw(x, y, "claw1.png");
        	}
        	else if ( StdDraw.isKeyPressed(32)) { //drop down with spacebar
            	drop(x, y);
            	done = 1;	          	
            }
        }
    }

    public static void drop(int x, int y) { //drop to candy
        while ((y > 200 && y != ycoord[0] + 50)  ) {
            y -= 5;
            draw(x, y, "claw1.png");
        }
        returnObject(x, y);
    }

    public static void returnObject(int x, int y) { // If there is a candy, will return claw
        if (checkCandy(x, y)) { //returns candy if it is under claw
            while (y < yOriginal) {
                y += 3;
                drawItem(x, y, num);
            }
            while (x > 70) {
                x -= 5;
                drawItem(x, y, num);
            }
            dropItem(x, y, image[0]);
            } 
        
        if (!checkCandy(x,y)) { //returns just the claw
            while (y < yOriginal) {
                y += 3;
                draw(x, y, "claw1.png");
            }
            while (x > 70) {
                x -= 5;
                draw(x, y, "claw1.png");
            }   
        }
        returnClaw(x,y);
    }

    public static void pit(int x, int y) { //creates the pit of candy
        xcoord = new int[N];
        ycoord = new int[N];
        image  = new String[N];
        					
        image[0] = "candy1.png"; //assigns images
        image[1] = "candy2.png";
        image[2] = "candy3.png";
        image[3] = "candy4.png";
        image[4] = "candy5.png";
        image[5] = "candy1.png";
        image[6] = "candy2.png";
        image[7] = "candy3.png";
        image[8] = "candy4.png";
        image[9] = "candy5.png";

        //upper limit of the number generator that bounds it to right at 530
        //lower limit that bounds it on the left
        int x_upper = width - 70;
        int x_lower = width / 3;

        int y_upper = 210;
        int y_lower = 170;

        //loop that generate random x values within the bounds
        for (int i = 0; i < N; i++) {
            xcoord[i] = (int)(Math.random() * (x_upper - (x_lower))) + (x_lower);
        }

        //loop that generate random y values within bounds
        for (int i = 0; i < N; i++) {
            ycoord[i] = (int)(Math.random() * (y_upper - (y_lower))) + (y_lower);
        }
       
        draw(x, y, "claw1.png"); //draws pit
    }

    public static boolean checkCandy(int x, int y) { //checks if candy is there
        int i = 0;
        num = 0;
        while (i < xcoord.length && num == 0) {
            if (xcoord[i] - 10 < x && x < xcoord[i] + 10) {
                num = i;
                xcoord[i]= 700;
                return true;  
            }
            i++;
        }
        return false;
    }

    public static void returnClaw (int x, int y) { //returns claw to original position
    	waitSec(1000);
    	while (x<=200 ) {
    		x+=3;
    		draw(x,y, "claw1.png");	
    		System.out.println(x);
    	}	
    	waitSec(1000);
    }
    
    public static void waitSec(long time) { //allows claw to wait for a second
    	try { 								
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    public static void drawItem(double x, double y, int num) { //draws claw with item
    	StdDraw.picture(width / 2, height / 2, background, 600, 400);
        StdDraw.picture(x, y, "claw1.png", xSize, ySize);
        StdDraw.picture(x, y-35, image[num], xSize, ySize);
        drawRemainingPit(20);
    }

    public static void dropItem(int x, int y, String pic) { //drops item into bin
        while (y > 50) {
            y -= 2;
            StdDraw.picture(width / 2, height / 2, background, 600, 400);
            StdDraw.picture(70, 332, "claw1.png", xSize, ySize);
            StdDraw.picture(x, y, image[num], 50, 50);
            drawRemainingPit(0);
        }
    }
    public static void draw(double x, double y, String pic) { //draws empty claw
        StdDraw.picture(width / 2, height / 2, background, 600, 400);
        StdDraw.picture(x, y, pic, xSize, ySize);
        drawRemainingPit(20);
    }
    
    public static void drawRemainingPit(int time) { //used to draw the pit and the show/clear
    	for (int i = 0; i < N; i++) {
            StdDraw.picture(xcoord[i], ycoord[i], image[i], 50, 50);
        }
        StdDraw.show(time);
        StdDraw.clear();
    }
}