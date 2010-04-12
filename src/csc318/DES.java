package csc318;

/**
 * @author mscoutermarsh
 * @date 4/11/2010
 * 
 */
public class DES {
	
	private String key;
	private String message;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static void main(String[] args) {
		DES DES = new DES();
		DES.setMessage("0100010111010001000011110111110100010110110011001010001101010010");
		DES.setKey("0110001001010001110010110010110010111100111100101010100000111011");
		
		DES.shiftBits("1110",1);
		
		String key1 = DES.removeParityBits(DES.getKey());
		
		String pc1 = DES.pc1(DES.getMessage());
		System.out.println("Message: " + DES.getMessage());
		System.out.println("After initial permutation:");
		System.out.println(pc1);

		String L0 = pc1.substring(0, 32);
		String R0 = pc1.substring(32);

		System.out.println();
		System.out.println("L0:" + L0);
		System.out.println("R0:" + R0);
		
		// expand r... E(r)
		String Er = DES.E(R0);
		
		
		

	}
	
	private String[] generateKeys(String key){
		
		return null;
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
	
	// initial key permutation
	private String keyPermutation1(String input) {
		int[] p = {57,49,41,33,25,17,9,1,58,50,42,34,26,18,
				10,2,59,51,43,35,27,19,11,3,60,52,44,36,
				63,55,47,39,31,23,15,7,62,54,46,38,30,22,
				14,6,61,53,45,37,29,21,13,5,28,20,12,4};

		String k = "";

		for (int i = 0; i < (p.length); i++) {
			k += input.substring(p[i] - 1, p[i]);
		}
		return k;
	}
	
	// final key permutation
	private String keyPermutation2(String input) {
		int[] p = {14,17,11,24,1,5,3,28,15,6,21,10,
				23,19,12,4,26,8,16,7,27,20,13,2,
				41,52,31,37,47,55,30,40,51,45,33,48,
				44,49,39,56,34,53,46,42,50,36,29,32};

		String k = "";

		for (int i = 0; i < (p.length); i++) {
			k += input.substring(p[i] - 1, p[i]);
		}
		return k;
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
	
	// shifts bits to the left
	private String shiftBits(String input, int round) {
		int[] shift = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
		int[] shifted = new int[input.length()] ;
		
		round = round - 1;
		
		for(int i = 0; i < input.length(); i++){
			shifted[(i+shift[round]) % input.length()] = Integer.parseInt(input.substring(i, i+1));
		}
		
		String out = "";
		for(int i = 0; i < shifted.length; i++){
			out += shifted[i];
		}
		return shifted.toString();
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
