package com.laptopstore;

import java.util.Scanner;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaptopStoreFileInput {
    private static final Logger logger = LoggerFactory.getLogger(LaptopStoreFileInput.class);

    public static void fillLaptops() {
        try {
            // 使用类名.class 替代 getClass()
            InputStream inputStream = LaptopStoreFileInput.class.getClassLoader().getResourceAsStream("data/database.csv");
            if (inputStream == null) {
                logger.error("Cannot find database.csv");
                return;
            }
            Scanner inputFile = new Scanner(inputStream);

            inputFile.useDelimiter(",|\r\n");
            inputFile.nextLine();

            for (int index = 0; index < LaptopStoreApplication.laptopArray.length; index++) {
                String brand = inputFile.next();
                int customerRating = inputFile.nextInt();
                String model = inputFile.next();
                String type = inputFile.next();
                double laptopCost = inputFile.nextDouble();
                String cpuBrand = inputFile.next();
                String cpuModel = inputFile.next();
                int cores = inputFile.nextInt();
                double speedGHZ = inputFile.nextDouble();
                int speedRating = inputFile.nextInt();
                int ram = inputFile.nextInt();
                int ssd = inputFile.nextInt();
                int storageRating = inputFile.nextInt();
                String gpuBrand = inputFile.next();
                String gpuModel = inputFile.next();
                int usbPorts = inputFile.nextInt();
                String otherPorts = inputFile.next();
                String os = inputFile.next();
                double displaySize = inputFile.nextDouble();
                String displayResolution = inputFile.next();
                boolean touchscreen = inputFile.nextBoolean();
                double weight = inputFile.nextDouble();
                String link = inputFile.next();

                LaptopStoreApplication.laptopArray[index] = new Laptop(model, cpuModel, gpuModel, brand, cpuBrand,
                        gpuBrand, laptopCost, type, cores, speedGHZ, speedRating, storageRating, customerRating, ram,
                        ssd, usbPorts, otherPorts, os, displaySize, displayResolution, touchscreen, weight, link);

                // 图片也应该从资源中加载
                InputStream imageStream = LaptopStoreFileInput.class.getClassLoader()
                        .getResourceAsStream("images/laptops/laptop" + index + ".jpg");
                if (imageStream != null) {
                    LaptopStoreApplication.laptopArray[index].setIcon(new ImageIcon(imageStream.readAllBytes()));
                } else {
                    logger.error("无法找到图片: images/laptops/laptop{}.jpg", index);
                }
            }

            inputFile.close();

        } catch (Exception e) {
            logger.error("文件读取错误: {}", e.getMessage(), e);
        }
    }
}