import java.util.*;
public class Mail {
	public static void main(String[] args) {
		UniformQuantizer obj = new UniformQuantizer();
		Scanner cs = new Scanner(System.in);
		int x = cs.nextInt();
		obj.compress(x, "/home/ahmed/FCI/java/UniformQuantization/src/lena.jpg");
	}

}
