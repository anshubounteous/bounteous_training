// 4. Linked Lists
// Add Two Numbers
// You are given two non-empty linked 
// lists representing two non-negative integers.
//  Add the two numbers and return the sum as a linked list.


#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
};

Node* reverse(Node* head) {
    Node* curr = head;
    Node* prev = nullptr;

    while (curr) {
        Node* next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }

    return prev;
}

Node* solution(Node* head1, Node* head2) {
    Node* curr1 = head1;
    Node* curr2 = head2;

    Node* dummy = new Node();
    Node* last = dummy;
    int carry = 0;

    while (curr1 || curr2 || carry) { 
        int sum = carry;

        if (curr1) {
            sum += curr1->val;
            curr1 = curr1->next;
        }

        if (curr2) {
            sum += curr2->val;
            curr2 = curr2->next;
        }

        carry = sum / 10;
        Node* temp = new Node();
        temp->val = sum % 10;
        temp->next = nullptr;

        last->next = temp;
        last = temp;
    }

    return dummy->next;
}

int main() {
    int n;
    cin >> n;

    Node* head1 = new Node();
    Node* last = head1;

    for (int i = 0; i < n; i++) {
        int value;
        cin >> value;
        Node* temp = new Node();
        temp->val = value;
        temp->next = nullptr;

        last->next = temp;
        last = temp;
    }

    head1 = reverse(head1->next);  
    int m;
    cin >> m;

    Node* head2 = new Node();
    last = head2;

    for (int i = 0; i < m; i++) {
        int value;
        cin >> value;
        Node* temp = new Node();
        temp->val = value;
        temp->next = nullptr;

        last->next = temp;
        last = temp;
    }

    head2 = reverse(head2->next);  
    Node* result = reverse(solution(head1, head2));

    Node* curr = result;
    while (curr) {  
        cout << curr->val;
        if (curr->next) cout << " -> ";
        curr = curr->next;
    }
    cout << endl;

    return 0;
}
