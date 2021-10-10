package transformations;

public class Matrix {
    public static void show (double[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                System.out.printf("%f ", matrix[i][j]);
            }
            System.out.println("");
        }
    }

    public static double[][] multiplication (double[][] matrixA, double[][] matrixB) {
        double[][] resultingMatrix = new double[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++)
        {
            for (int j = 0; j < matrixB[0].length; j++)
            {
                for (int k = 0; k < matrixA[0].length; k++)
                {
                    resultingMatrix[i][j] += matrixA[i][k]*matrixB[k][j];
                }
            }
        }
        return resultingMatrix;
    }

    public static double[][] translationMatrix2D (double translationX, double translationY) {
        double[][] matrix = new double[][]{
            {1, 0, translationX},
            {0, 1, translationY},
            {0, 0, 1}
        };
        return matrix;
    }

    public static double[][] scalingMatrix2D (double scalingX, double scalingY) {
        scalingX = (scalingX == 0) ? 1 : scalingX;
        scalingY = (scalingY == 0) ? 1 : scalingY;
        double[][] matrix = new double[][]{
            {scalingX, 0, 0},
            {0, scalingY, 0},
            {0, 0, 1}
        };
        return matrix;
    }

    public static double[][] rotationMatrix2D (double angle) {
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));
        double[][] matrix = new double[][]{
            {cos, -sin, 0},
            {sin, cos, 0},
            {0, 0, 1}
        };
        return matrix;
    }

    public static double[][] reflectionMatrix2D (String axis) {
        double position00;
        double position11;
        double position22;
        if (axis.equalsIgnoreCase("x")) {
            position00 = 1;
            position11 = -1;
            position22 = 1;
        } else if (axis.equalsIgnoreCase("y")) {
            position00 = -1;
            position11 = 1;
            position22 = 1;
        } else {
            position00 = -1;
            position11 = -1;
            position22 = 1;
        }
        double[][] matrix = new double[][]{
            {position00, 0, 0},
            {0, position11, 0},
            {0, 0, position22}
        };
        return matrix;
    }

    public static double[][] shearMatrix2D (double shearX, double shearY) {
        double[][] matrix = new double[][]{
            {1, shearX, 0},
            {shearY, 1, 0},
            {0, 0, 1}
        };
        return matrix;
    }

    public static double[][] translationMatrix3D (double translationX, double translationY, double translationZ) {
        double[][] matrix = new double[][]{
            {1, 0, 0, translationX},
            {0, 1, 0, translationY},
            {0, 0, 0, translationZ},
            {0, 0, 0, 1}
        };
        return matrix;
    }

    public static double[][] scalingMatrix3D (double scalingX, double scalingY, double scalingZ) {
        scalingX = (scalingX == 0) ? 1 : scalingX;
        scalingY = (scalingY == 0) ? 1 : scalingY;
        scalingZ = (scalingZ == 0) ? 1 : scalingZ;
        double[][] matrix = new double[][]{
            {scalingX, 0, 0, 0},
            {0, scalingY, 0, 0},
            {0, 0, scalingZ, 0},
            {0, 0, 0, 0}
        };
        return matrix;
    }

    public static double[][] rotationMatrix3DX(double angle) {
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));
        double[][] matrix = new double[][]{
            {1, 0, 0, 0},
            {0, cos, -sin, 0},
            {0, sin, cos, 0},
            {0, 0, 0, 1}
        };
        return matrix;
    }
    
    public static double[][] rotationMatrix3DY(double angle) {
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));
        double[][] matrix = new double[][]{
            {cos, 0, sin, 0},
            {0, 1, 0, 0},
            {-sin, 0, cos, 0},
            {0, 0, 0, 1}
        };
        return matrix;
    }

    public static double[][] rotationMatrix3DZ(double angle) {
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));
        double[][] matrix = new double[][]{
            {cos, -sin, 0, 0},
            {sin, cos, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        return matrix;
    }

    public static double[][] reflectionMatrix3D (String axis) {
        double position00;
        double position11;
        double position22;
        double position33;
        if (axis.equalsIgnoreCase("XY")) {
            position00 = 1;
            position11 = 1;
            position22 = -1;
            position33 = 1;
        } else if (axis.equalsIgnoreCase("YZ")) {
            position00 = -1;
            position11 = 1;
            position22 = 1;
            position33 = 1;
        } else {
            position00 = 1;
            position11 = -1;
            position22 = 1;
            position33 = 1;
        }

        double[][] matrix = new double[][]{
            {position00, 0, 0, 0},
            {0, position11, 0, 0},
            {0, 0, position22, 0},
            {0, 0, 0, position33}
        };
        return matrix;
    }

    public static double[][] shearMatrix3D (double shearYx, double shearZx, 
                                            double shearXy, double shearZy,
                                            double shearXz, double shearYz) {
        double[][] matrix = new double[][]{
            {1, shearXy, shearXz, 0},
            {shearYx, 1, shearYz, 0},
            {shearZx, shearZy, 1, 0},
            {0, 0, 0, 1}
        };
        return matrix;
    }
}
