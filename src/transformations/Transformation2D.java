package transformations;

import math.Matrix;

public class Transformation2D {
    public double[][] translation(double[][] objectMatrix, double translationX, double translationY) {
        double[][] translationMatrix = Matrix.translationMatrix2D(translationX, translationY);
        double[][] resultingMatrix = new double[translationMatrix.length][objectMatrix[0].length];
        
        resultingMatrix = Matrix.multiplication (translationMatrix, objectMatrix);
        return resultingMatrix;
    }

    public double[][] scaling (double[][] objectMatrix, double scalingX, double scalingY) {
        double[][] scalingMatrix = Matrix.scalingMatrix2D (scalingX, scalingY);
        double[][] resultingMatrix = new double[scalingMatrix.length][objectMatrix[0].length];


        // Ponto pivot 
        double pivotX = objectMatrix[0][0];
        double pivotY = objectMatrix[1][0];

        resultingMatrix = Matrix.multiplication (Matrix.translationMatrix2D(pivotX, pivotY), scalingMatrix);
        resultingMatrix = Matrix.multiplication (resultingMatrix, Matrix.translationMatrix2D (-pivotX, -pivotY));
        resultingMatrix = Matrix.multiplication (resultingMatrix, objectMatrix);

        return resultingMatrix;
    }

    public double[][] rotation (double[][] objectMatrix, double angle) {
        double[][] rotationMatrix = Matrix.rotationMatrix2D (angle);
        double[][] resultingMatrix = new double[rotationMatrix.length][objectMatrix[0].length];

        // Ponto pivot 
        double pivotX = objectMatrix[0][0];
        double pivotY = objectMatrix[1][0];

        resultingMatrix = Matrix.multiplication (Matrix.translationMatrix2D(pivotX, pivotY), rotationMatrix);
        resultingMatrix = Matrix.multiplication (resultingMatrix, Matrix.translationMatrix2D (-pivotX, -pivotY));
        resultingMatrix = Matrix.multiplication (resultingMatrix, objectMatrix);
        return resultingMatrix;
    }

    public double[][] reflection (double[][] objectMatrix, String axis) {
        double[][] reflectionMatrix = Matrix.reflectionMatrix2D (axis);
        double[][] resultingMatrix = new double[reflectionMatrix.length][objectMatrix[0].length];
        
        resultingMatrix = Matrix.multiplication (reflectionMatrix, objectMatrix);
        return resultingMatrix;
    }

    public double[][] shear (double[][] objectMatrix, double shearX, double shearY) {
        double[][] shearMatrix = Matrix.shearMatrix2D (shearX, shearY);
        double[][] resultingMatrix = new double[shearMatrix.length][objectMatrix[0].length];


        // Ponto pivot 
        double pivotX = objectMatrix[0][0];
        double pivotY = objectMatrix[1][0];

        resultingMatrix = Matrix.multiplication (Matrix.translationMatrix2D(pivotX, pivotY), shearMatrix);
        resultingMatrix = Matrix.multiplication (resultingMatrix, Matrix.translationMatrix2D(-pivotX, -pivotY));
        resultingMatrix = Matrix.multiplication (resultingMatrix, objectMatrix);
        return resultingMatrix;
    }
}
