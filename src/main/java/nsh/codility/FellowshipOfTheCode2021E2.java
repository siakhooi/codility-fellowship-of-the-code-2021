package nsh.codility;

public class FellowshipOfTheCode2021E2 implements FellowshipOfTheCode2021Interface {
	class tree1 {
		int base;
		Tuple neutr;
		Tuple[] tree;

		tree1(int N, Tuple neutr) {
			base = 1;
			while (base < N)
				base *= 2;
			this.neutr = neutr;
			this.tree = new Tuple[2 * base];
			for (int j = 0; j < 2 * base; j++)
				tree[j] = new Tuple(neutr.a, neutr.n);
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

		void update(int x, Tuple val) {
			x += base;
			tree[x] = (val == null) ? neutr : val;

			while (x > 1) {
				x = (int) (x / 2);
				tree[x] = minTuple(tree[2 * x], tree[2 * x + 1]);
			}
		}

		Tuple query(int x) {
			if (x == -1)
				return neutr;
			x += base;
			Tuple res = tree[x];
			while (x > 1) {
				if (x % 2 != 0)
					res = minTuple(tree[x - 1], res);
				x = (int) (x / 2);
			}
			return res;
		}
	}

	class tree2 {
		int base;
		int neutr;
		int[] tree;

		tree2(int N, int neutr) {
			base = 1;
			while (base < N) {
				base *= 2;
			}
			this.neutr = neutr;
			tree = new int[2 * base];
			for (int j = 0; j < 2 * base; j++)
				tree[j] = neutr;
		}

		void update(int x, int val) {
			x += base;
			tree[x] = (val == -1) ? neutr : val;
			while (x > 1) {
				x = (int) (x / 2);
				tree[x] = tree[2 * x] + tree[2 * x + 1];
			}
		}

		int query_binsearch(int s) {
			int i = 1;
			while (i < base)
				if (tree[2 * i] > s)
					i = 2 * i;
				else {
					s -= tree[2 * i];
					i = 2 * i + 1;
				}

			return i - base;
		}

		int query(int x) {
			if (x == -1)
				return neutr;
			x += base;
			int res = tree[x];
			while (x > 0) {
				if (x % 2 != 0)
					res = tree[x - 1] + res;
				x = (int) (x / 2);
			}
			return res;
		}
	}

	class Tuple {
		char a;
		int n;

		Tuple(char a, int n) {
			this.a = a;
			this.n = n;
		}
	}

	public String solution(String S, int K) {
		int N = S.length();
		char[] ans = new char[N];
		int r = 0;

		Tuple inf = new Tuple('z', N + 1);

		tree1 tree_let = new tree1(N, inf);
		tree2 tree_num = new tree2(N, 0);

		for (int i = 0; i < N; i++) {
			tree_let.update(i, new Tuple(S.charAt(i), i));
			tree_num.update(i, 1);
		}
		for (int i = 0; i < N; i++) {
			int pref = tree_num.query_binsearch(K);
			Tuple mi = tree_let.query(pref);

			ans[r++] = mi.a;
			K -= tree_num.query(mi.n - 1);

			tree_let.update(mi.n, null);
			tree_num.update(mi.n, -1);
		}
		return new String(ans);
	}
}
