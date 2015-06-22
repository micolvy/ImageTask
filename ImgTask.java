package com.mico.imgex.ImgTask;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImgTask {

	/**
	 * 缩放图片
	 * 
	 * @param zoomOutFactor
	 *            缩放因子 0.0 - 1.0: 缩小 ,1.0 - x.x: 放大
	 * @return
	 */
	public BufferedImage zoomImage(float zoomOutFactor);

	/**
	 * 图像灰度化
	 * 
	 * @return
	 */
	public BufferedImage ColorToGray();

	/**
	 * 图像二值化
	 */
	public BufferedImage ColorToBinary();

	/**
	 * 均值滤波 输入彩色图像,先调用方法{@code public BufferedImage ColorToGray()}
	 * 然后用得到的灰度图进行模板为 3x3的均值滤波
	 * 
	 * @return 返回均值滤波后的灰度图
	 */
	public BufferedImage AverFilter();

	/**
	 * 图片另存为
	 * 
	 * @param path
	 *            保存路径
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveImage(String path) throws FileNotFoundException,
			IOException;

}
