import java.util.Random;

public class RandomizeArray {

	

	public static int[] randomizeArray(int[] array) {
		int temp;
		Random r = new Random();
		int rand = r.nextInt(array.length);

		for (int i = 0; i < array.length; i++) {
			temp = array[i];
			array[i] = array[rand];
			array[rand] = temp;

		}
		return array;

	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

		randomizeArray(array);

		for (int j = 0; j < array.length; j++) {
			System.out.println(array[j]);
		}
	}
}
