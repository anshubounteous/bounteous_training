// 9. Rearrange a no to find min possible no in o(n) and constant space.
 
// for eg input 
// 1. 324 -> output 234
// 2. 50221 - 01225

#include <bits/stdc++.h>
using namespace std;

long long solution(long long num)
{
    if (num < 0)
    {
        string s = to_string(-num);
        sort(s.rbegin(), s.rend());
        return -stoll(s);
    }
    else if (num == 0)
    {
        return 0;
    }

    string s = to_string(num);
    sort(s.begin(), s.end());

    int i = 0;
    while (s[i] == '0')
        i++;

    char c = s[i];
    s.erase(s.begin() + i);
    s = c + s;

    return stoll(s);
}

int main()
{
    long long n;
    cin >> n;

    long long result = solution(n);
    cout << result << endl;

    return 0;
}
