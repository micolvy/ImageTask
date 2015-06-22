package com.mico.imgex.ImgTask;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImgTask {

	/**
	 * ����ͼƬ
	 * 
	 * @param zoomOutFactor
	 *            �������� 0.0 - 1.0: ��С ,1.0 - x.x: �Ŵ�
	 * @return
	 */
	public BufferedImage zoomImage(float zoomOutFactor);

	/**
	 * ͼ��ҶȻ�
	 * 
	 * @return
	 */
	public BufferedImage ColorToGray();

	/**
	 * ͼ���ֵ��
	 */
	public BufferedImage ColorToBinary();

	/**
	 * ��ֵ�˲� �����ɫͼ��,�ȵ��÷���{@code public BufferedImage ColorToGray()}
	 * Ȼ���õõ��ĻҶ�ͼ����ģ��Ϊ 3x3�ľ�ֵ�˲�
	 * 
	 * @return ���ؾ�ֵ�˲���ĻҶ�ͼ
	 */
	public BufferedImage AverFilter();

	/**
	 * ͼƬ���Ϊ
	 * 
	 * @param path
	 *            ����·��
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveImage(String path) throws FileNotFoundException,
			IOException;

}
