package pl.umcs.oop;

public class Main {
    public static void main(String[] args) {
        ImageHandler ih = new ImageHandler();

        ih.loadImage("test.png");
        long start = System.currentTimeMillis();
        ih.increaseBrightness(0x77);
        long end = System.currentTimeMillis();
        ih.saveImage("out.png");

        ih.loadImage("test.png");
        long start1 = System.currentTimeMillis();
        ih.increaseBrightnessMultiThread(0x77);
        long end1 = System.currentTimeMillis();
        ih.saveImage("out.png");

        ih.loadImage("test.png");
        long start2 = System.currentTimeMillis();
        ih.increaseBrightnessThreadPool(0x77);
        long end2 = System.currentTimeMillis();
        ih.saveImage("out.png");

        System.out.println("1 wątek: "+(end-start)+" ms");
        System.out.println("Wielowątkowo: "+(end1-start1)+" ms");
        System.out.println("Thread Pool: "+(end2-start2)+" ms");

    }
}