#include <bits/stdc++.h>
using namespace std;

bool areCloseStrings(string word1, string word2) {
    if (word1.length() != word2.length()) return false;

    unordered_map<char, int> freq1, freq2;
    unordered_set<char> set1, set2;

    for (char c : word1) {
        freq1[c]++;
        set1.insert(c);
    }

    for (char c : word2) {
        freq2[c]++;
        set2.insert(c);
    }

    if (set1 != set2) return false;
    vector<int> f1, f2;
    for (auto& pair : freq1) f1.push_back(pair.second);
    for (auto& pair : freq2) f2.push_back(pair.second);

    sort(f1.begin(), f1.end());
    sort(f2.begin(), f2.end());

    return f1 == f2;
}

int main() {
    string word1, word2;
    cin >> word1;
    cin >> word2;

    if (areCloseStrings(word1, word2)) {
        cout << "Output: true" << endl;
    } else {
        cout << "Output: false" << endl;
    }

    return 0;
}
