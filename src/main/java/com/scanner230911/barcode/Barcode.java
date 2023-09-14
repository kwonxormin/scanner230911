package com.scanner230911.barcode;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Barcode {
    public static void generateBarcodeImage(String content, String filePath) {
        int width = 300;
        int height = 100;
        String format = "PNG";

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, width, height);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            File imageFile = new File(filePath);
            ImageIO.write(bufferedImage, format, imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
