// anagram problem
#include <bits/stdc++.h>
using namespace std;

vector<vector<string>> solution(vector<string>& strs) {
    unordered_map<string, vector<string>> umap;
    for (string currStr : strs) {
        string sortedStr = currStr;
        sort(sortedStr.begin(), sortedStr.end());
        umap[sortedStr].push_back(currStr);
    }

    vector<vector<string>> result;
    for (auto& it : umap) {
        result.push_back(it.second);
    }
    return result;
}

int main() {
    int n;
    cin >> n;

    vector<string> strs(n);
    for (int i = 0; i < n; ++i) {
        cin >> strs[i];
    }

    vector<vector<string>> result = solution(strs);

    for (const auto& group : result) {
        for (const auto& word : group) {
            cout << word << " ";
        }
        cout << endl;
    }
    return 0;
}