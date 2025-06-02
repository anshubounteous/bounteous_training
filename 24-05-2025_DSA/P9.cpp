#include <bits/stdc++.h>
using namespace std;

int lengthOfLastWord(string s) {
    int length = 0, i = s.length() - 1;

    while (i >= 0 && s[i] == ' ') i--;

    while (i >= 0 && s[i] != ' ') {
        length++;
        i--;
    }

    return length;
}

int main() {
    string s;
    getline(cin, s);

    int result = lengthOfLastWord(s);
    cout <<  result << endl;
    return 0;
}
