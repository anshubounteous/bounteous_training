// 2. Subarray Sum Equals K
// Given an array of integers and a 
// target sum k, return the total number 
// of continuous subarrays whose sum equals to k.


#include <bits/stdc++.h>
using namespace std;

int solutoin(vector<int>& v, int target){
    unordered_set<int> uset;

    int sum = 0, count = 0;
    for(int i = 0; i < v.size(); i++){
        sum += v[i];
        uset.insert(sum);
        int diff = sum - target;

        if(uset.find(diff) != uset.end()){
            count++;
        }
    }
    return count;
}

int main(){
    int n;
    cin >> n;
    vector<int> v(n);
    for(int i =0; i < n; i++){
        cin >> v[i];
    }
    int target;
    cin >> target;

    cout << solutoin(v, target) << endl;
    return 0;
}