// longest Common Prefix 
#include <bits/stdc++.h>
using namespace std;

string solution(vector<string>& strs) {
    if (strs.empty()) return "";

    string prefix = strs[0];

    for (int i = 1; i < strs.size(); ++i) {
        while (strs[i].find(prefix) != 0) {
            prefix = prefix.substr(0, prefix.length() - 1);
            if (prefix.empty()) return "";
        }
    }

    return prefix;
}

int main() {
    int n;
    cin >> n;

    vector<string> strs(n);
    for (int i = 0; i < n; ++i) {
        cin >> strs[i];
    }

    string result = solution(strs);
    cout << result << " " << endl;

    return 0;
}
