package com.mico.imgex.ImgTask.impl;

public class Gaussian {

	private double[][] gaussTable;

	public static void gaussBlur(int[] data, int width, int height, int radius,  
            float sigma) {  
  
        float pa = (float) (1 / (Math.sqrt(2 * Math.PI) * sigma));  
        float pb = -1.0f / (2 * sigma * sigma);  
  
        // generate the Gauss Matrix  
        float[] gaussMatrix = new float[radius * 2 + 1];  
        float gaussSum = 0f;  
        for (int i = 0, x = -radius; x <= radius; ++x, ++i) {  
            float g = (float) (pa * Math.exp(pb * x * x));  
            gaussMatrix[i] = g;  
            gaussSum += g;  
        }  
  
        for (int i = 0, length = gaussMatrix.length; i < length; ++i) {  
            gaussMatrix[i] /= gaussSum;  
        }  
  
        // x direction  
        for (int y = 0; y < height; ++y) {  
            for (int x = 0; x < width; ++x) {  
                float r = 0, g = 0, b = 0;  
                gaussSum = 0;  
                for (int j = -radius; j <= radius; ++j) {  
                    int k = x + j;  
                    if (k >= 0 && k < width) {  
                        int index = y * width + k;  
                        int color = data[index];  
                        int cr = (color & 0x00ff0000) >> 16;  
                        int cg = (color & 0x0000ff00) >> 8;  
                        int cb = (color & 0x000000ff);  
  
                        r += cr * gaussMatrix[j + radius];  
                        g += cg * gaussMatrix[j + radius];  
                        b += cb * gaussMatrix[j + radius];  
  
                        gaussSum += gaussMatrix[j + radius];  
                    }  
                }  
  
                int index = y * width + x;  
                int cr = (int) (r / gaussSum);  
                int cg = (int) (g / gaussSum);  
                int cb = (int) (b / gaussSum);  
                  
                data[index] = cr << 16 | cg << 8 | cb | 0xff000000;  
            }  
        }  
  
        // y direction  
        for (int x = 0; x < width; ++x) {  
            for (int y = 0; y < height; ++y) {  
                float r = 0, g = 0, b = 0;  
                gaussSum = 0;  
                for (int j = -radius; j <= radius; ++j) {  
                    int k = y + j;  
                    if (k >= 0 && k < height) {  
                        int index = k * width + x;  
                        int color = data[index];  
                        int cr = (color & 0x00ff0000) >> 16;  
                        int cg = (color & 0x0000ff00) >> 8;  
                        int cb = (color & 0x000000ff);  
  
                        r += cr * gaussMatrix[j + radius];  
                        g += cg * gaussMatrix[j + radius];  
                        b += cb * gaussMatrix[j + radius];  
  
                        gaussSum += gaussMatrix[j + radius];  
                    }  
                }  
  
                int index = y * width + x;  
                int cr = (int) (r / gaussSum);  
                int cg = (int) (g / gaussSum);  
                int cb = (int) (b / gaussSum);  
                data[index] = cr << 16 | cg << 8 | cb | 0xff000000;  
            }  
        }  
    }  

	public void showTable() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {

				System.out.print(gaussTable[i][j] + "\t");
			}
			System.out.println("");
		}
	}

}