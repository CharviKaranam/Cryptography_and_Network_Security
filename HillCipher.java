//program 5


import java.io.*;
import java.util.*;

public class HillCipher {
    static float[][] decrypt = new float[3][1];
    static float[][] a = new float[3][3];
    static float[][] b = new float[3][3];
    static float[][] mes = new float[3][1];
    static float[][] res = new float[3][1];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) throws IOException {
        getkeymes();
        
        // Encryption: Matrix Multiplication
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 3; k++) {
                    res[i][j] = res[i][j] + a[i][k] * mes[k][j];
                }
            }
        }

        System.out.print("\nEncrypted String is : ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (res[i][0] % 26 + 97));
        }

        inverse();

        // Decryption: Matrix Multiplication
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 3; k++) {
                    decrypt[i][j] = decrypt[i][j] + b[i][k] * res[k][j];
                }
            }
        }

        System.out.print("\nDecrypted String is : ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) (decrypt[i][0] % 26 + 97));
        }
        System.out.println();
    }

    public static void getkeymes() throws IOException {
        System.out.println("Enter 3x3 matrix for key (it should be invertible): ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = sc.nextFloat();
            }
        }

        System.out.println("Enter a 3-letter string: ");
        String msg = br.readLine();
        for (int i = 0; i < 3; i++) {
            mes[i][0] = msg.charAt(i) - 97;
        }
    }

    public static void inverse() {
        float p, q;
        float[][] c = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = a[i][j];
            }
        }

        float det = determinant(c);
        if (det == 0) {
            System.out.println("Matrix is not invertible. Exiting.");
            System.exit(0);
        }

        // Identity Matrix initialization
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = (i == j) ? 1 : 0;
            }
        }

        // Gauss-Jordan Elimination for Inverse
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                p = c[i][k];
                q = c[k][k];
                for (int j = 0; j < 3; j++) {
                    if (i != k) {
                        c[i][j] = c[i][j] * q - p * c[k][j];
                        b[i][j] = b[i][j] * q - p * b[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = b[i][j] / c[i][i];
            }
        }

        System.out.println("\nInverse matrix is: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static float determinant(float[][] matrix) {
        float det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                  - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                  + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        return det;
    }
}