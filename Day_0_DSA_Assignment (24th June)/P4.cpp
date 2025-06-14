#include <bits/stdc++.h>
using namespace std;

string solution(string s, string t) {
    if (t.size() > s.size()) return "";

    unordered_map<char, int> t_freq, window;
    for (char c : t) t_freq[c]++;

    int required = t_freq.size();
    int formed = 0;
    int left = 0, right = 0;
    int minLen = INT_MAX, start = 0;

    while (right < s.size()) {
        char c = s[right];
        window[c]++;
        if (t_freq.count(c) && window[c] == t_freq[c]) {
            formed++;
        }

        while (formed == required) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                start = left;
            }

            window[s[left]]--;
            if (t_freq.count(s[left]) && window[s[left]] < t_freq[s[left]]) {
                formed--;
            }
            left++;
        }

        right++;
    }

    return minLen == INT_MAX ? "" : s.substr(start, minLen);
}

int main() {
    string s, t;
    getline(cin, s);
    getline(cin, t);

    string result = solution(s, t);
    cout << result << endl;

    return 0;
}
