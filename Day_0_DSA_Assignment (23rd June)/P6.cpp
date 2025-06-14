#include <bits/stdc++.h>
using namespace std;


bool isAnagram(const vector<int>& a, const vector<int>& b) {
    for (int i = 0; i < 26; ++i) {
        if (a[i] != b[i]) return false;
    }
    return true;
}

bool isAnagramSubstring(string s, string t) {
    int sLen = s.length(), tLen = t.length();
    if (tLen > sLen) return false;

    vector<int> freqS(26, 0), freqT(26, 0);

    for (int i = 0; i < tLen; ++i) {
        freqT[t[i] - 'a']++;
        freqS[s[i] - 'a']++;
    }

    if (isAnagram(freqS, freqT)) return true;

    for (int i = tLen; i < sLen; ++i) {
        freqS[s[i - tLen] - 'a']--;
        freqS[s[i] - 'a']++;

        if (isAnagram(freqS, freqT)) return true;
    }

    return false;
}

int main() {
    string s, t;
    cin >> s;
    cin >> t;

    if (isAnagramSubstring(s, t)) {
        cout << "Output: true" << endl;
    } else {
        cout << "Output: false" << endl;
    }

    return 0;
}
