package nsh.codility;

public class FellowshipOfTheCode2021C2 implements FellowshipOfTheCode2021Interface {

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

	char[] S1;
	SortedSegment[] SS;
	int segCount = 0;
	SortedSegment[] TT;

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

	SortedSegment update(int tid, int from, int to, int base, int K) {
		if (base > K) {
			TT[tid].enable = false;
			return TT[tid];
		}
		if (from == to) {
			TT[tid].enable = TT[tid].count > 0;
			return TT[tid];
		}

		int m = (from + to) >> 1;
		SortedSegment l = update((tid << 1) + 1, from, m, base, K);
		SortedSegment r = update((tid << 1) + 2, m + 1, to, base + (l.enable ? l.count : 0), K);

		TT[tid].enable = l.enable | r.enable;

		if (l.enable && r.enable) {
			TT[tid].count = l.count + r.count;
			TT[tid].minChar = l.minChar <= r.minChar ? l.minChar : r.minChar;
			TT[tid].id = l.minChar <= r.minChar ? l.id : r.id;
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

	SortedSegment remove2(int tid, int from, int to, int sid, int base, int newK) {
		if (from == to && from == sid) {
			TT[tid].count--;
			TT[tid].start++;
			TT[tid].enable = TT[tid].count > 0 && base <= newK;

			if (TT[tid].count > 0)
				TT[tid].minChar = S1[TT[tid].start];
			return TT[tid];
		}
		if (from == to) {
			TT[tid].enable = TT[tid].count > 0 && base <= newK;
			return TT[tid];
		}

		int m = (from + to) >> 1;
		SortedSegment l = remove2((tid << 1) + 1, from, m, sid, base, newK);
		SortedSegment r = remove2((tid << 1) + 2, m + 1, to, sid, base + (l.enable ? l.count : 0), newK);

		TT[tid].enable = l.enable | r.enable & base <= newK;

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

	public String solution(String S, int K) {
		StringBuilder r = new StringBuilder();
		int N = S.length();
		S1 = S.toCharArray();

		initSortedSegment(N);

		int TreeN = 1;
		while (segCount > TreeN)
			TreeN <<= 1;
		TreeN <<= 1;
		TT = new SortedSegment[TreeN];

		build(0, 0, segCount - 1);
		int maxSteps = N - 1;

		while (K > 0 && maxSteps > 0) {
			while (K >= maxSteps && maxSteps > 0) {
				int steps = TT[0].steps;
				char minChar = TT[0].minChar;
				int sid = TT[0].id;
				int start = SS[sid].start;
				int count = SS[sid].count;

				int removeCount = 1;
				int removeSteps = steps;
				while (removeCount < count && start + removeCount < N && S1[start + removeCount] == minChar
						&& K > removeSteps) {
					removeCount++;
					removeSteps += steps;
				}
				while (K < removeSteps) {
					removeCount--;
					removeSteps -= steps;
				}

				remove(0, 0, segCount - 1, sid, removeCount);
				K -= removeSteps;
				maxSteps -= removeCount;
				while (removeCount-- > 0)
					r.append(minChar);
			}

			update(0, 0, segCount - 1, 0, K);
			while (K < maxSteps && K > 0 && maxSteps > 0) {
				int steps = TT[0].steps;
				char minChar = TT[0].minChar;
				int sid = TT[0].id;

				K -= steps;
				remove2(0, 0, segCount - 1, sid, 0, K);
				r.append(minChar);
				maxSteps--;
			}
		}
		for (int i = 0; i < segCount; i++) {
			SortedSegment s1 = SS[i];
			while (s1.count-- > 0)
				r.append(S1[s1.start++]);
		}

		return r.toString();
	}
}
