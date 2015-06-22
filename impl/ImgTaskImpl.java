package com.mico.imgex.ImgTask.impl;

import java.awt.Color;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mico.imgex.ImgTask.ImgTask;

public class ImgTaskImpl implements ImgTask {

	private int imgWidth;
	private int imgHeight;
	private String path;
	private BufferedImage bImage;

	public ImgTaskImpl(String path) {
		this.path = path;
		inputImg();
	}

	private void inputImg() {

		File file = new File(path);
		try {
			bImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		imgWidth = bImage.getWidth();
		imgHeight = bImage.getHeight();
	}

	@Override
	public BufferedImage zoomImage(float zoomOutFactor) {

		int toWidth = (int) (imgWidth * zoomOutFactor);
		int toHeight = (int) (imgHeight * zoomOutFactor);

		BufferedImage bufferedImage = new BufferedImage(toWidth, toHeight,
				BufferedImage.TYPE_INT_RGB);

		bufferedImage.getGraphics()
				.drawImage(
						bImage.getScaledInstance(toWidth, toHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
		imgWidth = toWidth;
		imgHeight = toHeight;
		bImage = bufferedImage;
		return bImage;
	}

	@Override
	public BufferedImage ColorToGray() {

		BufferedImage newPic = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_3BYTE_BGR);

		ColorConvertOp cco = new ColorConvertOp(
				ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		cco.filter(bImage, newPic);

		bImage = newPic;

		return bImage;
	}

	@Override
	public BufferedImage AverFilter() {

		bImage = ColorToGray();

		for (int i = 1; i < imgWidth - 1; i++)
			for (int j = 1; j < imgHeight - 1; j++) {
				// 因为已经转换成灰度图像,只要取其中一个分量的值即为该像素点的灰度值(&0xff 得到蓝色分量)
				// 滤波模板为 3x3 的像素矩阵,中心像素点为滤波结果
				int row0 = (bImage.getRGB(i - 1, j - 1) & 0xff)
						+ (bImage.getRGB(i - 1, j) & 0xff)
						+ (bImage.getRGB(i - 1 + 1, j + 1) & 0xff);

				int row1 = (bImage.getRGB(i, j - 1) & 0xff)
						+ (bImage.getRGB(i, j) & 0xff)
						+ (bImage.getRGB(i, j + 1) & 0xff);

				int row2 = (bImage.getRGB(i + 1, j - 1) & 0xff)
						+ (bImage.getRGB(i + 1, j) & 0xff)
						+ (bImage.getRGB(i + 1, j + 1) & 0xff);

				int grayValueSum = row0 + row1 + row2;
				int averGrayValue = grayValueSum / 9;
				Color c = new Color(averGrayValue, averGrayValue, averGrayValue);
				int avergb = c.getRGB();

				bImage.setRGB(i, j, avergb);
			}

		return bImage;
	}

	@Override
	public BufferedImage ColorToBinary() {

		Color c = new Color(0x30, 0x30, 0x30);
		int midGry = c.getRGB();
		int colorValue_0 = Color.black.getRGB();
		int colorValue_1 = Color.white.getRGB();

		for (int i = 0; i < imgWidth; i++)
			for (int j = 0; j < imgHeight; j++) {
				if (bImage.getRGB(i, j) < midGry) {
					bImage.setRGB(i, j, colorValue_0);
				} else {
					bImage.setRGB(i, j, colorValue_1);
				}
			}
		return bImage;

	}

	@Override
	public void saveImage(String path) throws FileNotFoundException,
			IOException {
		File f = new File(path);
		ImageIO.write(bImage, "BMP", f);
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public BufferedImage getbImage() {
		return bImage;
	}

}
