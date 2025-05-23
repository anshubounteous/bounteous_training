// 6. Remove Nth Node From End of List
// Given a linked list, remove the nth node from the end and return its head.

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
};

Node* solution(Node* head, int pos) {
    Node* dummy = new Node();
    dummy->next = head;
    Node* first = dummy;
    Node* second = dummy;

    for (int i = 0; i <= pos; ++i) {
        if (first == nullptr) return head; 
        first = first->next;
    }

    
    while (first != nullptr) {
        first = first->next;
        second = second->next;
    }

    Node* temp = second->next;
    second->next = second->next->next;
    delete temp;

    Node* newHead = dummy->next;
    delete dummy;
    return newHead;
}

int main() {
    int n;
    cin >> n;

    Node* dummyHead = new Node(); 
    Node* last = dummyHead;

    for (int i = 0; i < n; i++) {
        int value; 
        cin >> value;
        Node* temp = new Node();
        temp->val = value;
        temp->next = nullptr;

        last->next = temp;
        last = temp;
    }

    int pos;
    cin >> pos;

    Node* head = dummyHead->next;
    delete dummyHead;

    head = solution(head, pos);


    Node* curr = head;
    while (curr) {
        cout << curr->val << " -> ";
        curr = curr->next;
    }
    cout << endl;

    return 0;
}
