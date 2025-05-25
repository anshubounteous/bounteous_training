// parehthesis problem

#include <bits/stdc++.h>
using namespace std;

bool isValid(string s) {
    stack<char> stk;
    unordered_map<char, char> bracketPairs = {
        {')', '('},
        {']', '['},
        {'}', '{'}
    };

    for (char c : s) {
        if (bracketPairs.count(c)) {
            if (stk.empty() || stk.top() != bracketPairs[c])
                return false;
            stk.pop();
        } else {
            stk.push(c);
        }
    }

    return stk.empty();
}

int main() {
    string input;
    cin >> input;

    if (isValid(input))
        cout << "true" << endl;
    else
        cout << "false" << endl;

    return 0;
}
