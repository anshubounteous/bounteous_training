#include <iostream>
#include <string>
using namespace std;

int findSubstring(string haystack, string needle) {
    int n = haystack.size();
    int m = needle.size();

    if (m == 0) return 0; // Edge case: empty needle

    for (int i = 0; i <= n - m; ++i) {
        int j = 0;
        while (j < m && haystack[i + j] == needle[j]) {
            ++j;
        }
        if (j == m) return i; // Full match found
    }

    return -1; // Not found
}

int main() {
    string haystack, needle;
    cout << "Enter haystack: ";
    cin >> haystack;
    cout << "Enter needle: ";
    cin >> needle;

    int result = findSubstring(haystack, needle);
    cout << "Output: " << result << endl;

    return 0;
}
