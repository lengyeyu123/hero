package com.han.hero;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MyTest {

    // Compulsory
    static {
        // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.load("D:\\devSoft\\opencv\\build\\java\\x86\\opencv_java460.dll");
    }

    @Test
    public void testMy() {
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
    }


}
