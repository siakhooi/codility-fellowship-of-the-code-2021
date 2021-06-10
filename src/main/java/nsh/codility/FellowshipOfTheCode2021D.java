package nsh.codility;

public class FellowshipOfTheCode2021D implements FellowshipOfTheCode2021Interface {

	void initSortedSegment(int N) {
		SS = new SortedSegment[N];
		char lastChar = 0;

		for (int i = 0; i < N; i++) {
			if (i == 0) {
				SS[0] = new SortedSegment();
				SS[0].start = 0;
				SS[0].count = 1;
				SS[0].id = 0;
				SS[0].minChar = S1[0];
				lastChar = S1[0];
			} else {
				if (S1[i] >= lastChar) {
					SS[segCount].count++;
					lastChar = S1[i];
				} else {
					SS[++segCount] = new SortedSegment();
					SS[segCount].start = i;
					SS[segCount].count = 1;
					SS[segCount].id = segCount;
					SS[segCount].minChar = S1[i];
					lastChar = S1[i];
				}
			}
		}
		segCount++;
	}

	class SortedSegment {
		boolean enable = true;
		char minChar = 0;
		int steps = 0;
		int id = -1;
		int start = -1;
		int count = 0;
	}

	SortedSegment build(int tid, int from, int to) {
		if (from == to) {
			TT[tid] = SS[from];
			return TT[tid];
		}
		int m = (from + to) >> 1;
		SortedSegment l = build((tid << 1) + 1, from, m);
		SortedSegment r = build((tid << 1) + 2, m + 1, to);

		TT[tid] = new SortedSegment();
		TT[tid].count = l.count + r.count;
		TT[tid].minChar = l.minChar <= r.minChar ? l.minChar : r.minChar;
		TT[tid].id = l.minChar <= r.minChar ? l.id : r.id;
		TT[tid].steps = l.minChar <= r.minChar ? l.steps : r.steps + l.count;

		return TT[tid];
	}

	SortedSegment remove(int tid, int from, int to, int sid, int removeCount) {
		if (from == to && from == sid) {
			TT[tid].count -= removeCount;
			TT[tid].start += removeCount;
			TT[tid].enable = TT[tid].count > 0;

			if (TT[tid].count > 0)
				TT[tid].minChar = S1[TT[tid].start];
			return TT[tid];
		}
		int m = (from + to) >> 1;
		SortedSegment l = (sid <= m) ? remove((tid << 1) + 1, from, m, sid, removeCount) : TT[(tid << 1) + 1];
		SortedSegment r = (sid > m) ? remove((tid << 1) + 2, m + 1, to, sid, removeCount) : TT[(tid << 1) + 2];

		TT[tid].enable = l.enable | r.enable;

		if (l.enable && r.enable) {
			TT[tid].minChar = l.minChar <= r.minChar ? l.minChar : r.minChar;
			TT[tid].id = l.minChar <= r.minChar ? l.id : r.id;
			TT[tid].count = l.count + r.count;
			TT[tid].steps = l.minChar <= r.minChar ? l.steps : r.steps + l.count;
		} else if (l.enable) {
			TT[tid].minChar = l.minChar;
			TT[tid].id = l.id;
			TT[tid].count = l.count;
			TT[tid].steps = l.steps;
		} else if (r.enable) {
			TT[tid].minChar = r.minChar;
			TT[tid].id = r.id;
			TT[tid].count = r.count;
			TT[tid].steps = r.steps;
		}

		return TT[tid];
	}

	char[] S1;
	SortedSegment[] SS;
	int segCount = 0;
	SortedSegment[] TT;

	public String solution(String S, int K) {
		StringBuilder r = new StringBuilder();
		int N = S.length();
		S1 = S.toCharArray();
		int K1 = K;

		initSortedSegment(N);

		int maxSteps = N - 1;

		if (K1 >= maxSteps && maxSteps > 0) {
			int TreeN = 1;
			while (segCount > TreeN)
				TreeN <<= 1;
			TreeN <<= 1;
			TT = new SortedSegment[TreeN];

			build(0, 0, segCount - 1);

			while (K1 >= maxSteps && maxSteps > 0) {
				int steps = TT[0].steps;
				char minChar = TT[0].minChar;
				int sid = TT[0].id;
				int start = SS[sid].start;
				int count = SS[sid].count;

				int removeCount = 1;
				int removeSteps = steps;
				while (removeCount < count && start + removeCount < N && S1[start + removeCount] == minChar
						&& K1 > removeSteps) {
					removeCount++;
					removeSteps += steps;
				}
				while (K1 < removeSteps) {
					removeCount--;
					removeSteps -= steps;
				}

				remove(0, 0, segCount - 1, sid, removeCount);
				K1 -= removeSteps;
				maxSteps -= removeCount;
				while (removeCount-- > 0)
					r.append(minChar);
			}
		}
		if (maxSteps >= 0) {
			int currentSeg = 0;
			if (K1 > 0 && maxSteps > 0) {
				if (K != K1) {
					int newSegCount = 0;
					while (newSegCount < segCount && SS[newSegCount].count > 0)
						newSegCount++;
					SortedSegment blank = new SortedSegment();

					for (int i = 0; i < segCount; i++) {
						if (SS[i].count > 0 && i > newSegCount) {
							SS[newSegCount] = SS[i];
							SS[i] = blank;
							while (newSegCount < segCount && SS[newSegCount].count > 0)
								newSegCount++;
						}
					}
					segCount = newSegCount;
				}

				while (K1 > 0 && currentSeg < segCount) {
					int sstart = SS[currentSeg].start;
					int scount = SS[currentSeg].count;
					char currentChar = S1[sstart];

					if (scount > K1) {
						r.append(currentChar);
						SS[currentSeg].start++;
						SS[currentSeg].count--;
						if (SS[currentSeg].count == 0)
							while (++currentSeg < segCount && SS[currentSeg].count == 0)
								;
						continue;
					}
					int search = scount;
					int currentIndex = currentSeg;
					int currentCount = search;

					for (int i = currentSeg + 1; i < segCount && currentChar != 'a' && search <= K1; i++) {
						int sstart2 = SS[i].start;
						int scount2 = SS[i].count;
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
						SS[currentIndex].start++;
						SS[currentIndex].count--;

						if (SS[currentSeg].count == 0)
							while (++currentSeg < segCount && SS[currentSeg].count == 0)
								;
					} else {
						while (SS[currentIndex].count > 0 && S1[SS[currentIndex].start] == currentChar
								&& K1 >= currentCount) {
							r.append(currentChar);
							K1 -= currentCount;
							SS[currentIndex].start++;
							SS[currentIndex].count--;
						}
						if (SS[currentSeg].count == 0) {
							while (++currentSeg < segCount && SS[currentSeg].count == 0)
								;
						}
					}
				}
			}
			for (int i = currentSeg; i < segCount; i++) {
				SortedSegment s1 = SS[i];
				while (s1.count-- > 0)
					r.append(S1[s1.start++]);
			}
		}
		return r.toString();
	}
}
