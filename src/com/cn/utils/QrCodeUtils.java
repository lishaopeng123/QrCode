package com.cn.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 生成二维码工具类
 * 
 * @author lishaoping 静态的 方便类名点方法名调用
 */
public class QrCodeUtils {

	public static void generateQRCode(String content) throws IOException {

		Qrcode x = new Qrcode();
		x.setQrcodeErrorCorrect('M');//设置纠错等级
		x.setQrcodeEncodeMode('B');//设置编码模式
		x.setQrcodeVersion(7);//设置版本
		
		//构造画图对象图片
		int width=140;
		int height=140;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//创建画图
		Graphics2D graphics = bi.createGraphics();//创建画图对象
		graphics.setColor(Color.BLACK);//设置内容的颜色
		graphics.setBackground(Color.WHITE);//设置背景颜色
		
		graphics.clearRect(0, 0, width, height);//清理出一块矩形区域，用来填充图片内容
		
		
		
		byte[] d = content.getBytes("utf-8");//获取内容的字节数组
		
		if (d.length > 0 && d.length < 120) {
			boolean[][] s = x.calQrcode(d);

			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if (s[j][i]) {
						graphics.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);//画图对象方法
					}
				}
			}
		}
		
		graphics.dispose();//关闭释放资源
		bi.flush();
		
		ImageIO.write(bi, "png", new File("E:/test.png"));
		
	}
	
	public static void main(String[] args) throws IOException {
		
		generateQRCode("一丢丢同学万圣节快乐！               "+"  小鹏同学制作");
	}

}
