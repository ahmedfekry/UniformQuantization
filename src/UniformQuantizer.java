
public class UniformQuantizer {

    public static int[] compress(int levels, String filePath) {

        ImageRW obj = new ImageRW();
        ImageRW obj2 = new ImageRW();
        int Range = 256 / levels;
        double MeanError=0;
        int[] a = new int[levels + 1];
        int[][] pixels = new int[512][512];
        int[][] compressed = new int[512][512];
        pixels = obj.readImage(filePath);
        compressed = pixels;

        for (int i = 0; i < obj.height; i++) {
            for (int j = 0; j < obj.width; j++) {
             
                if ((compressed[i][j] / Range) >= (levels)) {
                    compressed[i][j] = levels - 1;
                } else {
                    compressed[i][j] = (compressed[i][j] / Range);
                }
            }
        }
        System.out.println("DONE ??? !!!");
        for (int i = 0; i < (levels + 1); i++) {
            a[i] = ((i * Range) + ((i + 1) * Range)) / 2;
            
        }
        
        System.out.println("DONE final !!!");

        obj.writeImage(compressed, "compressed.jpg");
        int[][] decompressed = new int[512][512];
        decompressed = decompress(a, "compressed.jpg");
        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                MeanError+=Math.pow((pixels[i][j]-decompressed[i][j]),2);
                
          }
        }
        MeanError/=(512*512); 
        System.out.println(MeanError);
        return a;

    }

    public static int[][] decompress(int[] a, String filePath) {

        ImageRW obj2 = new ImageRW();
        obj2.readImage(filePath);
        int[][] pixels = new int[obj2.height][obj2.width];
        pixels = obj2.readImage(filePath);
        System.out.println(a.length);
        for (int i = 0; i < obj2.height; i++) {
            for (int j = 0; j < obj2.width; j++) {
                if (pixels[i][j] < a.length-1) {
                    pixels[i][j] = a[pixels[i][j]];
                } else {
                    pixels[i][j] = a[a.length-1];
                }
            }
        }
        obj2.writeImage(pixels, "decompressed.jpg");
        return pixels;
    }

}