package nsh.codility;

public class FellowshipOfTheCode2021A implements FellowshipOfTheCode2021Interface {

	public String solution(String S, int K) {
		StringBuilder r = new StringBuilder();
		int N = S.length();
		char[] S1 = S.toCharArray();
		int s1 = 0;

		while (K > 0 && s1 < N) {
			int search = 0;
			char x = S1[s1];
			int i = s1 + 1;

			int index = s1;
			int searchCount = 0;
			while (search < K && i < N && x != 'a') {
				char y = S1[i];
				if (y == 0) {
					i++;
					continue;
				}
				search++;
				if (S1[i] < x) {
					index = i;
					x = S1[i];
					searchCount = search;
				}
				i++;
			}
			r.append(x);
			S1[index] = 0;
			K -= searchCount;
			if (s1 == index) {
				while (++s1 < N)
					if (S1[s1] != 0)
						break;
			}

		}
		for (int i = s1; i < N; i++) {
			if (S1[i] != 0)
				r.append(S1[i]);
		}

		return r.toString();
	}
}
