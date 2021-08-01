# Codility Challenge: The Fellowship of the Code 2021

## PartialSort

- Difficulty: Hard
- Given a string and an integer K, return the lexicographically minimum string that can be achieved by applying at most K swaps of adjacent letters.

- <https://app.codility.com/programmers/challenges/fellowship_of_the_code_2021/>
- <https://app.codility.com/programmers/task/partial_sort/>

## Versions

- Result
  - **Good: Correctness 100%, Performance 100%.**
  - `OK`: Correctness 100%, Performance <100%.
  - `Fail`: Correctness <100%.
- File naming convention
  - Code `A`: `FellowshipOfTheCode2021A.java`
  - etc

### Gold Award

| Code | Description           | base       | Result Complexity                          | Result | Report                                                                            |
| ---- | --------------------- | ---------- | ------------------------------------------ | ------ | --------------------------------------------------------------------------------- |
| `D`  | BTree/Sorted Segments | `B1`, `C1` | `O(N * log(min(N, K)))` or `O(N * log(N))` | `Good` | [7S455K](https://app.codility.com/cert/view/cert7S455K-BW5AM4QP597S83WT/details/) |

### Silver Awards

| Code | Description                    | base | Result Complexity                          | Result | Report                                                                            |
| ---- | ------------------------------ | ---- | ------------------------------------------ | ------ | --------------------------------------------------------------------------------- |
| `A`  | Simple Search                  |      | `O(N**2)` or `O(N * min(K, N))`            | `OK`   | [NJ5ZWT](https://app.codility.com/cert/view/certNJ5ZWT-XZA8ZWA4U88PYFTN/details/) |
| `B1` | Sorted Segments                |      | `O(N * log(min(N, K)))` or `O(N * log(N))` | `OK`   | [X7M9P7](https://app.codility.com/cert/view/certX7M9P7-VTXZPEEZJ9HFYWA2/details/) |
| `B2` | Sorted Segments, globalMinChar | `B1` | `O(N * log(min(N, K)))` or `O(N * log(N))` | `OK`   | [5DZA3Q](https://app.codility.com/cert/view/cert5DZA3Q-2VZMDTXX9JNP3RKS/details/) |
| `C1` | BTree/Sorted Segments          |      | `O(N * log(min(N, K)))` or `O(N * log(N))` | `OK`   | [K7FNPJ](https://app.codility.com/cert/view/certK7FNPJ-EXEGUPM28384A8XK/details/) |
| `C2` | combine update+remove2         | `C1` | `O(N * log(min(N, K)))` or `O(N * log(N))` | `OK`   | [YEGXCD](https://app.codility.com/cert/view/certYEGXCD-KFGTQ2KHF2HFC8K7/details/) |

### Solution provided by Codility

| Code | Description                      | Result Complexity                          | Result | Report                                                                  |
| ---- | -------------------------------- | ------------------------------------------ | ------ | ----------------------------------------------------------------------- |
| `E1` | `O(N * min(K, N-1)) < O(N ** 2)` | `O(N**2)` or `O(N * min(K, N))`            | `OK`   | [V544EB-UK7](https://app.codility.com/demo/results/trainingV544EB-UK7/) |
| `E2` | 2 Segment Trees                  | `O(N * log(min(N, K)))` or `O(N * log(N))` | `Good` | [JNMGZM-CVD](https://app.codility.com/demo/results/trainingJNMGZM-CVD/) |

- Reference for `E2`
  - [KHGHUT-4DY](https://app.codility.com/demo/results/trainingKHGHUT-4DY/)
