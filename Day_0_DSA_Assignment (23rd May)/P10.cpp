#include <bits/stdc++.h>
using namespace std;

string cleanWord(const string& word) {
    string result;
    for (char c : word) {
        if (isalpha(c)) result += tolower(c);
    }
    return result;
}

string mostCommonWord(string paragraph, vector<string>& banned) {
    unordered_set<string> bannedSet(banned.begin(), banned.end());
    unordered_map<string, int> freq;
    string word;
    stringstream ss(paragraph);

    while (ss >> word) {
        string clean = cleanWord(word);
        if (!clean.empty() && bannedSet.find(clean) == bannedSet.end()) {
            freq[clean]++;
        }
    }

    string result;
    int maxFreq = 0;
    for (auto& [w, count] : freq) {
        if (count > maxFreq) {
            maxFreq = count;
            result = w;
        }
    }

    return result;
}

int main() {
    string paragraph;
    getline(cin, paragraph);

    int bannedCount;
    cin >> bannedCount;
    cin.ignore();

    vector<string> banned(bannedCount);
    for (int i = 0; i < bannedCount; ++i) {
        cin >> banned[i];
    }

    string result = mostCommonWord(paragraph, banned);
    cout << result << endl;

    return 0;
}
