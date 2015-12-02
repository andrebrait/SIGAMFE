package com.sigamfe.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ImageUtils {

	public static Byte[] convertImageToBytes(Image image) {
		if (image == null) {
			return null;
		}
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", byteOutput);
		} catch (IOException e) {
			return null;
		}
		byte[] output = byteOutput.toByteArray();
		Byte out[] = new Byte[output.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = output[i];
		}
		return out;
	}

	public static Image convertBytesToImage(Byte[] b) {
		if (b == null) {
			return null;
		}
		byte input[] = new byte[b.length];
		for (int i = 0; i < input.length; i++) {
			input[i] = b[i];
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(input);
		try {
			BufferedImage image = ImageIO.read(bais);
			WritableImage fxImage = new WritableImage(image.getWidth(), image.getHeight());
			return SwingFXUtils.toFXImage(image, fxImage);
		} catch (IOException e) {
			return null;
		}
	}

}
