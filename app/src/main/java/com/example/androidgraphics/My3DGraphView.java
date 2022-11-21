package com.example.androidgraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * created on 20/11/2022
 *
 * @author Lucas Goncalves
 */
public class My3DGraphView extends View {

    private final Paint redPaint;
    private final Paint bluePaint;
    private final Paint greenPaint; //paint object for drawing the lines
    private final Paint yellowPaint; //paint object for drawing the lines
    private final Coordinate[] cube_vertices;//the vertices of a 3D cube
    private Coordinate[] drawHeadVertices, drawBodyVertices, drawNeckVertices;
    private Coordinate[] drawLeftArmVertices, drawRightArmVertices;
    private Coordinate[] drawLeftLegVertices, drawRightLegVertices;
    private Coordinate[] drawLeftHandVertices, drawRightHandVertices;
    private Coordinate[] drawLeftFeetVertices, drawRightFeetVertices;

    Timer timer = new Timer();

    public My3DGraphView(Context context) {
        super(context, null);
        final My3DGraphView thisview = this;
        //create the paint object
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//Stroke
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(4);

        //bluePaint
        bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint.setStyle(Paint.Style.FILL);
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStrokeWidth(4);

        //greenPaint
        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        greenPaint.setStyle(Paint.Style.FILL);
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStrokeWidth(4);

        //greenPaint
        yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        yellowPaint.setStyle(Paint.Style.FILL);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setStrokeWidth(4);


        //create a 3D cube
        cube_vertices = new Coordinate[8];
        cube_vertices[0] = new Coordinate(-1, -1, -1, 1);
        cube_vertices[1] = new Coordinate(-1, -1, 1, 1);
        cube_vertices[2] = new Coordinate(-1, 1, -1, 1);
        cube_vertices[3] = new Coordinate(-1, 1, 1, 1);
        cube_vertices[4] = new Coordinate(1, -1, -1, 1);
        cube_vertices[5] = new Coordinate(1, -1, 1, 1);
        cube_vertices[6] = new Coordinate(1, 1, -1, 1);
        cube_vertices[7] = new Coordinate(1, 1, 1, 1);


        //Head Of the Robot
        drawRobotHead();
        //Head Of the Robot
        drawRobotNeck();
        //Body Of the Robot
        drawRobotBody();
        //Arms Of the Robot
        drawArms();
        //Hands Of the Robot
        drawHands();
        //Legs Of the Robot
        drawLegs();
        //Foots Of the Robot
        drawFoots();

        thisview.invalidate();//update the view

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int i = 25;
                //add your code to rotate the object about the axis
                long thetaY = this.scheduledExecutionTime() / 1000000;
                timeToDance(0, i, 0);
                //System.out.println(thetaY);
                System.out.println(i);
                System.out.println(drawBodyVertices.length);
                thisview.invalidate();//update the view
            }
        };
        timer.scheduleAtFixedRate(task, 100, 600);

    }

    //region Draw Robot
    private void drawRobotHead() {
        drawHeadVertices = translate(cube_vertices, 7, 3, 5);
        drawHeadVertices = scale(drawHeadVertices, 80, 80, 80);
    }

    private void drawRobotNeck() {
        drawNeckVertices = translate(cube_vertices, 13.9, 9.1, 5);
        drawNeckVertices = scale(drawNeckVertices, 40, 40, 80);
    }

    private void drawRobotBody() {
        drawBodyVertices = translate(cube_vertices, 3.5, 3.0, 5);
        drawBodyVertices = scale(drawBodyVertices, 160, 200, 80);
    }

    private void drawArms() {
        //left Arm
        drawLeftArmVertices = translate(cube_vertices, 5.6, 3.36, 5);
        drawLeftArmVertices = scale(drawLeftArmVertices, 60, 170, 80);

        //Right Arm
        drawRightArmVertices = translate(cube_vertices, 13.0, 3.36, 5);
        drawRightArmVertices = scale(drawRightArmVertices, 60, 170, 80);
    }

    private void drawHands() {
        //left Arm
        drawLeftHandVertices = translate(cube_vertices, 5.6, 15.9, 5);
        drawLeftHandVertices = scale(drawLeftHandVertices, 60, 50, 80);

        //Right Arm
        drawRightHandVertices = translate(cube_vertices, 13.0, 15.9, 5);
        drawRightHandVertices = scale(drawRightHandVertices, 60, 50, 80);
    }

    private void drawLegs() {
        //left Arm
        drawLeftLegVertices = translate(cube_vertices, 7.6, 5.5, 5);
        drawLeftLegVertices = scale(drawLeftLegVertices, 60, 180, 80);

        //Right Arm
        drawRightLegVertices = translate(cube_vertices, 11.0, 5.5, 5);
        drawRightLegVertices = scale(drawRightLegVertices, 60, 180, 80);
    }

    private void drawFoots() {
        //left Arm
        drawLeftFeetVertices = translate(cube_vertices, 7.6, 24.3, 5);
        drawLeftFeetVertices = scale(drawLeftFeetVertices, 60, 50, 80);

        //Right Arm
        drawRightFeetVertices = translate(cube_vertices, 11.0, 24.3, 5);
        drawRightFeetVertices = scale(drawRightFeetVertices, 60, 50, 80);
    }
    //endregion

    public void timeToDance(double thetaX, double thetaY, double thetaZ) {

        //Head
        drawHeadVertices = quaternionNormalize(drawHeadVertices, thetaX, 1,0,0);
        drawHeadVertices = quaternionNormalize(drawHeadVertices, thetaY, 0,1,0);
        drawHeadVertices = quaternionNormalize(drawHeadVertices, thetaZ, 1,0,1);

        //Neck
        drawNeckVertices = quaternionNormalize(drawNeckVertices, thetaX, 1,0,0);
        drawNeckVertices = quaternionNormalize(drawNeckVertices, thetaY, 0,1,0);
        drawNeckVertices = quaternionNormalize(drawNeckVertices, thetaZ, 1,0,1);

        //Body
        drawBodyVertices = quaternionNormalize(drawBodyVertices, thetaX, 1,0,0);
        drawBodyVertices = quaternionNormalize(drawBodyVertices, thetaY, 0,1,0);
        drawBodyVertices = quaternionNormalize(drawBodyVertices, thetaZ, 1,0,1);

        //Left Arm
        drawLeftArmVertices = quaternionNormalize(drawLeftArmVertices, thetaX, 1,0,0);
        drawLeftArmVertices = quaternionNormalize(drawLeftArmVertices, thetaY, 0,1,0);
        drawLeftArmVertices = quaternionNormalize(drawLeftArmVertices, thetaZ, 1,0,1);

        //Right Arm
        drawRightArmVertices = quaternionNormalize(drawRightArmVertices, thetaX, 1,0,0);
        drawRightArmVertices = quaternionNormalize(drawRightArmVertices, thetaY, 0,1,0);
        drawRightArmVertices = quaternionNormalize(drawRightArmVertices, thetaZ, 1,0,1);

        //Left Hand
        drawLeftHandVertices = quaternionNormalize(drawLeftHandVertices, thetaX, 1,0,0);
        drawLeftHandVertices = quaternionNormalize(drawLeftHandVertices, thetaY, 0,1,0);
        drawLeftHandVertices = quaternionNormalize(drawLeftHandVertices, thetaZ, 1,0,1);

        //Right Hand
        drawRightHandVertices = quaternionNormalize(drawRightHandVertices, thetaX, 1,0,0);
        drawRightHandVertices = quaternionNormalize(drawRightHandVertices, thetaY, 0,1,0);
        drawRightHandVertices = quaternionNormalize(drawRightHandVertices, thetaZ, 1,0,1);

        //Left Leg
        drawLeftLegVertices = quaternionNormalize(drawLeftLegVertices, thetaX, 1,0,0);
        drawLeftLegVertices = quaternionNormalize(drawLeftLegVertices, thetaY, 0,1,0);
        drawLeftLegVertices = quaternionNormalize(drawLeftLegVertices, thetaZ, 1,0,1);

        //Right Leg
        drawRightLegVertices = quaternionNormalize(drawRightLegVertices, thetaX, 1,0,0);
        drawRightLegVertices = quaternionNormalize(drawRightLegVertices, thetaY, 0,1,0);
        drawRightLegVertices = quaternionNormalize(drawRightLegVertices, thetaZ, 1,0,1);

        //Left Feet
        drawLeftFeetVertices = quaternionNormalize(drawLeftFeetVertices, thetaX, 1,0,0);
        drawLeftFeetVertices = quaternionNormalize(drawLeftFeetVertices, thetaY, 0,1,0);
        drawLeftFeetVertices = quaternionNormalize(drawLeftFeetVertices, thetaZ, 1,0,1);

        //Right Feet
        drawRightFeetVertices = quaternionNormalize(drawRightFeetVertices, thetaX, 1,0,0);
        drawRightFeetVertices = quaternionNormalize(drawRightFeetVertices, thetaY, 0,1,0);
        drawRightFeetVertices = quaternionNormalize(drawRightFeetVertices, thetaZ, 1,0,1);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //draw objects on the screen
        super.onDraw(canvas);
        DrawCube(canvas, drawHeadVertices, redPaint);
        DrawCube(canvas, drawNeckVertices, yellowPaint);
        DrawCube(canvas, drawBodyVertices, greenPaint);

        DrawCube(canvas, drawLeftArmVertices, redPaint);
        DrawCube(canvas, drawRightArmVertices, bluePaint);

        DrawCube(canvas, drawLeftHandVertices, bluePaint);
        DrawCube(canvas, drawRightHandVertices, redPaint);

        DrawCube(canvas, drawLeftLegVertices, redPaint);
        DrawCube(canvas, drawRightLegVertices, bluePaint);

        DrawCube(canvas, drawLeftFeetVertices, bluePaint);
        DrawCube(canvas, drawRightFeetVertices, redPaint);
    }

    private void DrawLinePairs(Canvas canvas, Coordinate[] vertices, int start, int end, Paint paint) {//draw a line connecting 2 points
        //canvas - canvas of the view
        //points - array of points
        //start - index of the starting point
        //end - index of the ending point
        //paint - the paint of the line
        canvas.drawLine((int) vertices[start].x, (int) vertices[start].y, (int) vertices[end].x, (int) vertices[end].y, paint);
    }

    private void DrawCube(Canvas canvas, Coordinate[] drawCubeVertices, Paint paint) {//draw a cube on the screen
        DrawLinePairs(canvas, drawCubeVertices, 0, 1, paint);
        DrawLinePairs(canvas, drawCubeVertices, 1, 3, paint);
        DrawLinePairs(canvas, drawCubeVertices, 3, 2, paint);
        DrawLinePairs(canvas, drawCubeVertices, 2, 0, paint);
        DrawLinePairs(canvas, drawCubeVertices, 4, 5, paint);
        DrawLinePairs(canvas, drawCubeVertices, 5, 7, paint);
        DrawLinePairs(canvas, drawCubeVertices, 7, 6, paint);
        DrawLinePairs(canvas, drawCubeVertices, 6, 4, paint);
        DrawLinePairs(canvas, drawCubeVertices, 0, 4, paint);
        DrawLinePairs(canvas, drawCubeVertices, 1, 5, paint);
        DrawLinePairs(canvas, drawCubeVertices, 2, 6, paint);
        DrawLinePairs(canvas, drawCubeVertices, 3, 7, paint);
    }

    //*********************************
    //matrix and transformation functions
    public double[] GetIdentityMatrix() {//return an 4x4 identity matrix
        double[] matrix = new double[16];
        matrix[0] = 1;
        matrix[1] = 0;
        matrix[2] = 0;
        matrix[3] = 0;
        matrix[4] = 0;
        matrix[5] = 1;
        matrix[6] = 0;
        matrix[7] = 0;
        matrix[8] = 0;
        matrix[9] = 0;
        matrix[10] = 1;
        matrix[11] = 0;
        matrix[12] = 0;
        matrix[13] = 0;
        matrix[14] = 0;
        matrix[15] = 1;
        return matrix;
    }

    public Coordinate Transformation(Coordinate vertex, double[] matrix) {//affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        Coordinate result = new Coordinate();
        result.x = matrix[0] * vertex.x + matrix[1] * vertex.y + matrix[2] * vertex.z + matrix[3];
        result.y = matrix[4] * vertex.x + matrix[5] * vertex.y + matrix[6] * vertex.z + matrix[7];
        result.z = matrix[8] * vertex.x + matrix[9] * vertex.y + matrix[10] * vertex.z + matrix[11];
        result.w = matrix[12] * vertex.x + matrix[13] * vertex.y + matrix[14] * vertex.z + matrix[15];
        return result;
    }

    public Coordinate[] Transformation(Coordinate[] vertices, double[] matrix) {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix
        Coordinate[] result = new Coordinate[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            result[i] = Transformation(vertices[i], matrix);
            result[i].normalise();
        }
        return result;
    }

    //***********************************************************
    //Affine transformation
    public Coordinate[] translate(Coordinate[] vertices, double tx, double ty, double tz) {
        double[] matrix = GetIdentityMatrix();
        matrix[3] = tx;
        matrix[7] = ty;
        matrix[11] = tz;
        return Transformation(vertices, matrix);
    }

    private Coordinate[] scale(Coordinate[] vertices, double sx, double sy, double sz) {
        double[] matrix = GetIdentityMatrix();
        matrix[0] = sx;
        matrix[5] = sy;
        matrix[10] = sz;
        return Transformation(vertices, matrix);
    }

    //https://stackoverflow.com/questions/6280586/how-does-this-quaternion-rotation-code-work
    //https://github.com/jherico/java-math/blob/master/src/main/java/org/saintandreas/math/Quaternion.java
    //https://gist.github.com/halfninja/572585
    private Coordinate[] quaternionNormalize(Coordinate[] vertices, double theta, int abtX, int abtY, int abtZ) {
        double w = Math.cos(Math.toRadians(theta / 2));
        double x = Math.sin(Math.toRadians(theta / 2)) * abtX;
        double y = Math.sin(Math.toRadians(theta / 2)) * abtY;
        double z = Math.sin(Math.toRadians(theta / 2)) * abtZ;

        double[] matrix = GetIdentityMatrix();

        matrix[3] = 0.0f;
        matrix[7] = 0.0f;
        matrix[11] = 0.0f;
        matrix[12] = 0.0f;
        matrix[13] = 0.0f;
        matrix[14] = 0.0f;
        matrix[15] = 1.0f;

        matrix[0] = (float) (1.0f - (2.0f * ((y * y) + (z * z))));
        matrix[1] = (float) (2.0f * ((x * y) - (z * w)));
        matrix[2] = (float) (2.0f * ((x * z) + (y * w)));

        matrix[4] = (float) (2.0f * ((x * y) + (z * w)));
        matrix[5] = (float) (1.0f - (2.0f * ((x * x) + (z * z))));
        matrix[6] = (float) (2.0f * ((y * z) - (x * w)));

        matrix[8] = (float) (2.0f * ((x * z) - (y * w)));
        matrix[9] = (float) (2.0f * ((y * z) + (x * w)));
        matrix[10] = (float) (1.0f - (2.0f * ((x * x) + (y * y))));
/*
        matrix[3] = 0;
        matrix[7] = 0;
        matrix[11] = 0;
        matrix[12] = 0;
        matrix[13] = 0;
        matrix[14] = 0;
        matrix[15] = 1;


        matrix[0] = w * w + x * x - y * y - z * z;
        matrix[1] = 2 * x * y - 2 * w * z;
        matrix[2] = 2 * x * z + 2 * w * y;
        matrix[4] = 2 * x * y + 2 * w * z;
        matrix[5] = w * w + y * y - x * x - z * z;
        matrix[6] = 2 * y * z - 2 * w * x;
        matrix[8] = 2 * x * z - 2 * w * y;
        matrix[9] = 2 * y * z + 2 * w * x;
        matrix[10] = w * w + z * z - x * x - y * y;*/
        Coordinate coordinate = new Coordinate();

        return Transformation(vertices, matrix);
    }

}