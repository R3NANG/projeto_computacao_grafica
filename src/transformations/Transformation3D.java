package transformations;

import math.Matrix;

public class Transformation3D {
    public double[][] translation(double[][] objectMatrix, double translationX, double translationY, double translationZ) {
        double[][] translationMatrix = Matrix.translationMatrix3D(translationX, translationY, translationZ);
        double[][] resultingMatrix = new double[translationMatrix.length][objectMatrix[0].length];

        resultingMatrix = Matrix.multiplication (translationMatrix, objectMatrix);
        return resultingMatrix;
    }

    public double[][] scaling(double[][] objectMatrix, double scalingX, double scalingY, double scalingZ) {
        double[][] scalingMatrix = Matrix.scalingMatrix3D(scalingX, scalingY, scalingZ);
        double[][] resultingMatrix = new double[scalingMatrix.length][objectMatrix[0].length];

        double pivoltX = objectMatrix[0][0];
        double pivoltY = objectMatrix[1][0];
        double pivoltZ = objectMatrix[2][0];

        resultingMatrix = Matrix.multiplication(Matrix.translationMatrix3D(pivoltX, pivoltY, pivoltZ), scalingMatrix);
        resultingMatrix = Matrix.multiplication(resultingMatrix, Matrix.translationMatrix3D(-pivoltX, -pivoltY, -pivoltZ));
        resultingMatrix = Matrix.multiplication (resultingMatrix, objectMatrix);
        return resultingMatrix;
    }

    public double[][] rotation(double[][] objectMatrix, double angle, String axis) {
        double[][] rotationMatrix = Matrix.rotationMatrix3DX(angle);
        double[][] resultingMatrix = new double[rotationMatrix.length][objectMatrix[0].length];

        double pivoltX = objectMatrix[0][0];
        double pivoltY = objectMatrix[1][0];
        double pivoltZ = objectMatrix[2][0];

        if (axis.equalsIgnoreCase("y")) {
            rotationMatrix = Matrix.rotationMatrix3DY(angle);
        } else if (axis.equalsIgnoreCase("z")) {
            rotationMatrix = Matrix.rotationMatrix3DZ(angle);
        }


        resultingMatrix = Matrix.multiplication(Matrix.translationMatrix3D(pivoltX, pivoltY, pivoltZ), rotationMatrix);
        resultingMatrix = Matrix.multiplication(resultingMatrix, Matrix.translationMatrix3D(-pivoltX, -pivoltY, -pivoltZ));
        resultingMatrix = Matrix.multiplication(resultingMatrix, objectMatrix);
        return resultingMatrix;
    }

    public double[][] reflection(double[][] objectMatrix, String axis) {
        double[][] reflectionMatrix = Matrix.reflectionMatrix3D(axis);
        double[][] resultingMatrix = new double[reflectionMatrix.length][objectMatrix[0].length];

        resultingMatrix = Matrix.multiplication(reflectionMatrix, objectMatrix);
        return resultingMatrix;
    }

    public double[][] shear(double[][] objectMatrix,
                            double shearYx, double shearZx,
                            double shearXy, double shearZy,
                            double shearXz, double shearYz){

        double[][] shearMatrix = Matrix.shearMatrix3D(shearYx, shearZx,
                                                      shearXy, shearZy, 
                                                      shearXz, shearYz);
        double[][] resultingMatrix = new double[shearMatrix.length][objectMatrix[0].length];

        double pivoltX = objectMatrix[0][0];
        double pivoltY = objectMatrix[1][0];
        double pivoltZ = objectMatrix[2][0];

        resultingMatrix = Matrix.multiplication(Matrix.translationMatrix3D(pivoltX, pivoltY, pivoltZ), shearMatrix);
        resultingMatrix = Matrix.multiplication(resultingMatrix, Matrix.translationMatrix3D(-pivoltX, -pivoltY, -pivoltZ));
        resultingMatrix = Matrix.multiplication(resultingMatrix, objectMatrix);
        return resultingMatrix;
    }
}
