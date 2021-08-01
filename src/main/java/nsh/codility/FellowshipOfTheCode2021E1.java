package nsh.codility;

public class FellowshipOfTheCode2021E1 implements FellowshipOfTheCode2021Interface {
	class Tuple {
		char a;
		int n;

		Tuple(char a, int n) {
			this.a = a;
			this.n = n;
		}
	}

	Tuple minTuple(Tuple m1, Tuple m2) {
		if (m1.a < m2.a)
			return m1;
		if (m2.a < m1.a)
			return m2;
		if (m1.n <= m2.n)
			return m1;
		return m2;
	}

	public String solution(String S, int K) {
		int N = S.length();
		String ans = "";
		for (int j = 0; j < N; j++) {
			Tuple mi = new Tuple('z', N + 1);
			for (int idx = 0; idx < Math.min(K + 1, S.length()); idx++) {
				mi = minTuple(mi, new Tuple(S.charAt(idx), idx));
			}
			ans += mi.a;
			S = S.substring(0, mi.n) + S.substring(mi.n + 1);
			K -= mi.n;
		}
		return ans;
	}
}
