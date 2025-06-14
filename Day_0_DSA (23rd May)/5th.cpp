
// reorder list 

#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(nullptr) {}
};
ListNode* reverseLL(ListNode* head){
    ListNode* curr = head;
    ListNode* prev = nullptr;

    while(curr){
        ListNode* next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
void solution(ListNode* head) {
    if (!head || !head->next) return;

    ListNode* slow = head;
    ListNode* fast = head;

    while(fast && fast->next){
        slow = slow->next;
        fast = fast->next->next;
    }

    ListNode* second = slow->next;
    slow->next = nullptr;
    second = reverseLL(second);
    ListNode* first = head;

    while (first && second) {
        ListNode* temp1 = first->next;
        ListNode* temp2 = second->next;

        first->next = second;
        if (!temp1) break;
        second->next = temp1;
        first = temp1;
        second = temp2;
    }
}

ListNode* createList(int n) {
    if (n <= 0) return nullptr;
    int val;
    cin >> val;
    ListNode* head = new ListNode(val);
    ListNode* curr = head;
    for(int i = 1; i < n; i++){
        cin >> val;
        curr->next = new ListNode(val);
        curr = curr->next;
    }
    return head;
}

int main() {
    int n; 
    cin >> n;
    ListNode* head = createList(n);
    solution(head);

    while(head){
        cout << head->val << " ";
        head = head->next;
    }
    cout << "\n";

    return 0;
}