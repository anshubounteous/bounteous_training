#include <bits/stdc++.h>
using namespace std;

string expandAroundCenter(const string &s, int left, int right) {
    while (left >= 0 && right < s.size() && s[left] == s[right]) {
        left--;
        right++;
    }
    return s.substr(left + 1, right - left - 1);
}

string solution(string s) {
    if (s.empty()) return "";

    string longest = "";
    for (int i = 0; i < s.size(); i++) {
        string odd = expandAroundCenter(s, i, i);      
        string even = expandAroundCenter(s, i, i + 1); 

        if (odd.size() > longest.size()) longest = odd;
        if (even.size() > longest.size()) longest = even;
    }
    return longest;
}

int main() {
    string s;
    cin >> s;

    string result = solution(s);
    cout << result << endl;

    return 0;
}
