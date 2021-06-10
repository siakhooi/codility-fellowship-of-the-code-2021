package nsh.codility;

public class FellowshipOfTheCode2021B1 implements FellowshipOfTheCode2021Interface {

	public String solution(String S, int K) {
		StringBuilder r = new StringBuilder();
		int N = S.length();
		char[] S1 = S.toCharArray();

		int[] SCount = new int[N];
		int[] SStart = new int[N];
		int segCount = 0;
		char lastChar = 'a';

		for (int i = 0; i < N; i++) {
			if (i == 0) {
				SStart[0] = 0;
				SCount[0] = 1;
				lastChar = S1[0];
			} else {
				if (S1[i] >= lastChar) {
					SCount[segCount]++;
					lastChar = S1[i];
				} else {
					segCount++;
					SCount[segCount] = 1;
					SStart[segCount] = i;
					lastChar = S1[i];
				}
			}
		}
		segCount++;

		int currentSeg = 0;

		while (K > 0 && currentSeg < segCount) {

			int sstart = SStart[currentSeg];
			int scount = SCount[currentSeg];
			char currentChar = S1[sstart];

			if (scount > K) {
				r.append(currentChar);
				SStart[currentSeg]++;
				SCount[currentSeg]--;
				if (SCount[currentSeg] == 0)
					while (++currentSeg < segCount && SCount[currentSeg] == 0)
						;
				continue;
			}
			int search = scount;
			int currentIndex = currentSeg;
			int currentCount = search;

			for (int i = currentSeg + 1; i < segCount && currentChar != 'a' && search <= K; i++) {
				int sstart2 = SStart[i];
				int scount2 = SCount[i];
				if (scount2 == 0)
					continue;
				char checkChar = S1[sstart2];
				if (checkChar < currentChar) {
					currentIndex = i;
					currentChar = checkChar;
					currentCount = search;
				}
				search += scount2;
			}

			if (currentIndex == currentSeg) {
				r.append(currentChar);
				SStart[currentIndex]++;
				SCount[currentIndex]--;
				if (SCount[currentSeg] == 0)
					while (++currentSeg < segCount && SCount[currentSeg] == 0)
						;

			} else {
				while (SCount[currentIndex] > 0 && S1[SStart[currentIndex]] == currentChar && K >= currentCount) {
					r.append(currentChar);
					K -= currentCount;
					SStart[currentIndex]++;
					SCount[currentIndex]--;
				}
				if (SCount[currentSeg] == 0) {
					while (++currentSeg < segCount && SCount[currentSeg] == 0)
						;
				}
			}
		}
		for (int i = currentSeg; i < segCount; i++) {
			int sstart = SStart[i];
			int scount = SCount[i];
			for (int j = 0; j < scount; j++)
				r.append(S1[sstart + j]);
		}

		return r.toString();
	}
}
