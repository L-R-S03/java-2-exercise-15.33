/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1533;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

class BeanPane extends Pane {
        //makes array for the pegs and 2d array to hold all the cirlces in the machine
        Line pegs[] = new Line[7];
        Circle circles[][] = new Circle[7][7];
        int slots[] = new int[7];
        PathTransition paths[]=new PathTransition[10]; 
    /**
     * ******************************************************
     * CONSTRUCTOR FOR THE PANE WHICH LOADS MACHINE IN 
********************************************************
     */
    public BeanPane() {
        //makes a polyline to make the outside of the machine 
        Polyline container = new Polyline();
        //adds all the points to the machine starting at the top left, then goes 
        //down and then over until up to top right
        container.getPoints().addAll(new Double[]{
            110.0, 20.0,
            110.0, 55.0,
            50.0, 190.0,
            50.0, 225.0,
            200.0, 225.0,
            200.0, 190.0,
            140.0, 55.0,
            140.0, 20.0,});
        //adds container to the pane 
        getChildren().add(container);
        //loops through needed circle and peg amount
        for (int i = 0; i < 7; i++) {
            //sets pegs at same heights but x adjusted by i for spacing
            Line a = new Line(65 + i * 20, 225, 65 + i * 20, 190);
            //adds peg to the array
            pegs[i] = a;
            //sets circles at same y, x is adjusted based on i so they're evenely spaced
            Circle c = new Circle(65 + i * 20, 190, 4);
            //adds circle to array in row 0 since it's the first row of circles
            circles[0][i] = c;
            //adds circles and pegs to pane 
            getChildren().addAll(pegs[i], circles[0][i]);
        }
        //loop with the amount of needed circles for that row
        for (int i = 0; i < 6; i++) {
            Circle circle = new Circle();
            if (i == 0) {
                //if i=0 move it further out so there's space from the outline of the machine 
                circle = new Circle(pegs[i].getEndX() + 10, 170, 4);
            } else {
                //loads rest of circles same distance away from corresponding peg
                circle = new Circle(pegs[i].getEndX() + 8, 170, 4);
            }
            //adds circle to roe 1 of 2d array
            circles[1][i] = circle;
            //adds circle to the pane 
            getChildren().add(circles[1][i]);
        }
        //lines 75-139 do the same sort of thing as above just with loop numbers 
        //changing based on number of balls needed in that row and the space away
        //from the peg 
        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle();
            if (i == 0) {
                circle = new Circle(pegs[i].getEndX() + 15, 150, 4);
            } else {
                circle = new Circle(pegs[i].getEndX() + 14, 150, 4);
            }

            circles[2][i] = circle;
            getChildren().add(circles[2][i]);
        }

        for (int i = 0; i < 4; i++) {
            Circle circle = new Circle();
            if (i == 0) {
                circle = new Circle(pegs[i].getEndX() + 25, 130, 4);
            } else {
                circle = new Circle(pegs[i].getEndX() + 25, 130, 4);
            }

            circles[3][i] = circle;
            getChildren().add(circles[3][i]);
        }

        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle();
            if (i == 0) {
                circle = new Circle(pegs[i].getEndX() + 35, 110, 4);
            } else {
                circle = new Circle(pegs[i].getEndX() + 36, 110, 4);
            }

            circles[4][i] = circle;
            getChildren().add(circles[4][i]);
        }

        for (int i = 0; i < 2; i++) {
            Circle circle = new Circle();
            if (i == 0) {
                circle = new Circle(pegs[i].getEndX() + 45, 90, 4);
            } else {
                circle = new Circle(pegs[i].getEndX() + 47, 90, 4);
            }

            circles[5][i] = circle;
            getChildren().add(circles[5][i]);
        }
        //makes circle at very top of loop as its only one on that row
        Circle circle = new Circle(pegs[0].getEndX() + 55, 70, 4);
        //adds it to the pane and the last row of the 2d array
        getChildren().add(circle);
        circles[6][0] = circle;
    }

    /**
     * ****************************************************
     * METHOD THAT GENERATES LEFT RIGHT PATH FOR BALL TO TAKE*
     ******************************************************
     */
    public char[] path() {
        //makes char array to store the generated path
        char[] path = new char[7];
        //inits var used to get random number
        int num = 0;
        //loops through array so whole path is generated 
        for (int i = 0; i < path.length; i++) {
            //generates 1 or 2 
            num = (int) (Math.random() * 2) + 1;
            //if it's one, the ball goes to the left so L is added to the array
            if (num == 1) {
                path[i] = 'L';
            } //otherwise it's 2 and moves right so right gets added to the array
            else {
                path[i] = 'R';
            }
        }
        //return the array with the path
        return path;
    }

    /**
     * ***********************************************************
     * MAKES POLYLINE OF THE PATH TAKEN TO LATER BE ANIMATED: THIS DOESN'T *
     * ANIMATE IT YET, JUST MAKES PATH FOR ANIMATION TO FOLLOW**
     ***********************************************************
     */
    public Polyline drop() {
        //runs the path method and saves it to an array to make the path of the ball
        char[] direction = path();
        //sets startX near where the top ball is where the ball would be dropped
        double startX = 120;
        //sets that to currentX for later reference 
        double currentX = startX;
        //does the same with y as x above 
        double startY = 65;
        double currentY = startY;
        //makes var to track number of rights to see what slot the ball falls in
        int slotNum = 0;
        //makes a polyLine
        Polyline pathLine = new Polyline();
        //makes an oList using getPoints() from the polyline to add points 
        //to the polyline one at a time (shout out to Whitney for telling me about OList)
        ObservableList<Double> pathPoints = pathLine.getPoints();
        //adds start x and start y to the OList as first points
        pathPoints.add(startX);
        pathPoints.add(startY);
        //loops through path array so each movement is done
        for (int i = 0; i < direction.length; i++) {
            if (direction[i] == 'R') {
                //if the direction is right move 12 right from current X 
                pathPoints.add(currentX + 10);
                //move down 15 to move near next row 
                pathPoints.add(currentY + 15);
                //update currentX and currentY for next movement 
                currentX += 10;
                currentY += 15;
                //increment slotNumber ball would fall in since it moved right
                slotNum++;
            } //if left 
            else {
                //move left from current position 12 
                pathPoints.add(currentX - 10);
                //move down to next row 
                pathPoints.add(currentY + 15);
                //update current x and current y
                currentX -= 10;
                currentY += 15;
            }
        }
        //if the final movement is right
        if (direction[6] == 'R') 
        {
            //increment slot number
            slotNum++;
            //adds one in the slots array at the index of the slot ball will be in
            //to show there's a ball in that slot
            slots[slotNum] += 1;
            //if there's more than one ball there adjust y so they're on top of 
            //each other
            if (slots[slotNum] > 1) {
                //updates current X, not dependent on how full the slot is  
                currentX += 10;
                //updates Y to be the normal distance of 15 plus 5 for each ball
                currentY += 25 + (5 * (slots[slotNum]));
            } else {
                //if the slot doesn't already have a ball in it just set x and y 
                //to preset values
                currentX += 10;
                currentY += 50;
            }
        } else {
            if (slots[slotNum] > 1) {
                //updates current X, not dependent on how full the slot is  
                currentX -= 10;
                //updates Y to be the normal distance of 15 plus 5 for each ball
                currentY += 25 + (5 * (slots[slotNum]));
            } else {
                //if slot is empty just sets x and y to default values
                currentX -= 10;
                currentY += 50;
            }
        }
        //adds last x and y to the OList 
        pathPoints.add(currentX);
        pathPoints.add(currentY);
        //makes pathLine transparent so it's not seen 
        pathLine.setStroke(javafx.scene.paint.Color.TRANSPARENT);
        //adds pathLine to the pane 
        getChildren().add(pathLine);
        //returns the pathLine to use for the animation 
        return pathLine; 
    }

    public void animation() {
        //loops 10 times for the 10 balls to be dropped 
        for(int i=0; i<10; i++) 
        {
            //makes circle for the ball
            Circle ball = new Circle(5, 10, 4);
            //Makes ball white with a black outline so it doesn't blend in 
            //with the machiene 
            ball.setFill(javafx.scene.paint.Color.WHITE);
            ball.setStroke(javafx.scene.paint.Color.BLACK); 
            //adds the ball to the pane 
            getChildren().add(ball); 
            //generates path and movement line from returned polyline from drop method
            Polyline pathLine=drop();
            //makes PathTransition using generated pathline and the ball and adds it
            //to transition path array
            paths[i]=new PathTransition(Duration.millis(5000),pathLine,ball); 
        }
        //makes a sequentialtransition out of the paths array so all the balls drop
        SequentialTransition fullGame=new SequentialTransition(paths); 
        //plays the sequentialtrnsition 
        fullGame.play(); 
        
    }
}
