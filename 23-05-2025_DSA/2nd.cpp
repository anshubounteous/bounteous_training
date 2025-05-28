// 2. Subarray Sum Equals K
// Given an array of integers and a 
// target sum k, return the total number 
// of continuous subarrays whose sum equals to k.


#include <bits/stdc++.h>
using namespace std;

int solution(vector<int>& v, int target){
    unordered_map<int, int> umap;
    int sum = 0, count = 0;
    
    umap[0] = 1;
    
    for(int i = 0; i < v.size(); i++){
        sum += v[i];
        
        if(umap.find(sum - target) != umap.end()){
            count += umap[sum - target];
        }
        
        umap[sum]++;
    }

    return count;
}

int main(){
    int n;
    cin >> n;
    vector<int> v(n);
    for(int i = 0; i < n; i++){
        cin >> v[i];
    }
    int target;
    cin >> target;

    cout << solution(v, target) << endl;
    return 0;
}
