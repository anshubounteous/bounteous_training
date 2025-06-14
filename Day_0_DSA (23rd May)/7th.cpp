// longest substring without repeation
#include <bits/stdc++.h>
using namespace std;

int solution(string s) {
    int n = s.size();
    int i = 0, j = 0;
    unordered_set<char> uset;
    int res = 0;

    while (j < n) {
        if (uset.find(s[j]) == uset.end()) {
            uset.insert(s[j]);
            j++;
            res = max(res, j - i);
        } else {
            uset.erase(s[i]);
            i++;
        }
    }

    return res;
}

int main() {
    string s;
    cin >> s;
    cout << solution(s) << endl;

    return 0;
}
