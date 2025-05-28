// 1. Two Sum II - Input array is sorted
// Given a sorted array of integers, return the indices of the two numbers such that they add up to a specific target.


#include <bits/stdc++.h>
using namespace std;
vector<int> solution(vector<int>& v, int target){
    int i = 0, j = v.size() - 1;
    while(i < j){
        int sum = v[i] + v[j];

        if(sum == target){
            return {i, j};
        }
        else if(sum < target){
            i++;
        }
        else if(sum > target){
            j--;
        }
    }
    return {-1, -1};
}

int main(){
    int n;
    cin >> n;

    vector<int> v(n);
    for(int i = 0; i < n; i++){
        cin >> v[i] ;
    }

    int target;
    cin >> target;
    vector<int> result;
    
    result = solution(v, target);
    
    cout << result[0] << " " << result[1] << endl;
}