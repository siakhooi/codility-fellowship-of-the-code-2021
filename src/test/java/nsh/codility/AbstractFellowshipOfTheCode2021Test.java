package nsh.codility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class AbstractFellowshipOfTheCode2021Test {
	abstract FellowshipOfTheCode2021Interface getTestObject();

	@Test
	void test01() {
		String S = "decade";
		int K = 4;

		String A = "adcede";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test02() {
		String S = "bbbabbb";
		int K = 2;

		String A = "babbbbb";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test03() {
		String S = "abracadabra";
		int K = 15;
		String A = "aaaaabrcdbr";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	@DisplayName("Short String, Bigger K")
	void test04() {
		String S = "t";
		int K = 2;
		String A = "t";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	@DisplayName("Short String, K<N")
	void test05() {
		String S = "zxzttyx";
		int K = 2;
		String A = "xztztyx";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test05a() {
		String S = "decade";
		int K = 1;

		String A = "dceade";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test05b() {
		String S = "cbaaaaa";
		int K = 1;

		String A = "bcaaaaa";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test05c() {
		String S = "cbaaaaa";
		int K = 3;
		String A = "abcaaaa";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@DisplayName("K=0")
	@Test
	void test06() {
		String S = "decade";
		int K = 0;
		String A = "decade";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test07a() {
		String S = "aaacccbbb";
		int K = 3;
		String A = "aaabcccbb";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test07b() {
		String S = "ccbbdd";
		int K = 2;
		String A = "bccbdd";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test07c() {
		String S = "edcba";
		int K = 3;
		String A = "bedca";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test07d() {
		String S = "zzffzzffzzrff";
		int K = 5;
		String A = "ffzzzfzfzzrff";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test08() {
		String S = "ccbbaaaa";
		int K = 17;
		String A = "aaaacbcb";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test09() {
		String S = "ytyx";
		int K = 2;
		String A = "tyxy";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test10() {
		String S = "aabbccaabbccaabbcc";
		int K = 20;
		String A = "aaaaabbbbccccabbcc";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test11() {
		String S = "bccefggahrstua";
		int K = 5;
		String A = "bcacefgghrstua";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test12a() {
		String S = "bbbbaaaaa";
		int K = 19;
		String A = "aaaababbb";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test12b() {
		String S = "bbbbaaaaa";
		int K = 21;
		String A = "aaaaabbbb";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test13() {
		String S = "zzqqbcac";
		int K = 9;
		String A = "aqzqzbcc";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	@DisplayName("Random")
	void test14() {
		String S = "addceggharrlestzvyattykaassttyxz";
		int K = 32;
		String A = "aaacddeegghrlrstzvyttykaassttyxz";

		assertEquals(A, getTestObject().solution(S, K));
	}

	@Test
	void test15() {
		String S = "eeeeedddddaadddcccccccrweioutpwoeriotuweoituebbbbbbaaaa";
		int K = 620;
		String A = "aaaaaabbbbbbcccccccddddddddeeeeeeeiorwutpworiotuweoitue";

		assertEquals(A, getTestObject().solution(S, K));
	}
}
