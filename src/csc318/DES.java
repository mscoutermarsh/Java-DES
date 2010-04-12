package csc318;

/**
 * @author mscoutermarsh
 * @date 4/11/2010
 * 
 */
public class DES {

	public static void main(String[] args) {
		DES encrypter = new DES();
		String message = "0100010111010001000011110111110100010110110011001010001101010010";
		String pc1 = encrypter.pc1(message);
		System.out.println("Message: " + message);
		System.out.println("After initial permutation:");
		System.out.println(pc1);

		String L0 = pc1.substring(0, 32);
		String R0 = pc1.substring(32);

		System.out.println();
		System.out.println("L0:" + L0);
		System.out.println("R0:" + R0);
		
		// expand r... E(r)
		String Er = encrypter.E(R0);
		
		String key1 = encrypter.removeParityBits("0110001001010001110010110010110010111100111100101010100000111011");
		

	}

	// initial permutation
	private String pc1(String input) {
		int[] p = { 58, 50, 42, 34, 26, 18, 10, 2, 50, 52, 44, 36, 28, 20, 12,
				4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16,
				8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
				61, 53, 45, 37, 29, 31, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };

		String m = "";

		for (int i = 0; i < (p.length); i++) {
			m += input.substring(p[i] - 1, p[i]);
		}
		return m;
	}
	
	// initial permutation
	private String keyPermutation(String input) {
		int[] p = {57,49,41,33,25,17,9,1,58,50,42,34,26,18,
				10,2,59,51,43,35,27,19,11,3,60,52,44,36,
				63,55,47,39,31,23,15,7,62,54,46,38,30,22,
				14,6,61,53,45,37,29,21,13,5,28,20,12,4};

		String m = "";

		for (int i = 0; i < (p.length); i++) {
			m += input.substring(p[i] - 1, p[i]);
		}
		return m;
	}

	// R expansion
	private String E(String input) {
		int[] e = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13,
				12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23,
				24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

		String r = "";

		for (int i = 0; i < (e.length); i++) {
			r += input.substring(e[i] - 1, e[i]);
		}
		return r;
	}
	
	private String removeParityBits(String key) {
		String key1 = "";
		for(int i = 0; i<(key.length()/8);i++){
			key1 += key.substring((i*8),((i*8)+7));
		}
		return key1;
	}

	// performs xor function using x1 and x2.
	private String xor(String x1, String x2) {
		String out = "";
		int len = 0;

		if (x1.length() >= x2.length()) {
			len = x2.length();
		} else {
			len = x1.length();
		}

		for (int i = 0; i < len; i++) {
			out += Integer.parseInt(x1.substring(i, i + 1))
					^ Integer.parseInt(x2.substring(i, i + 1));
		}

		return out;
	}

}
