# Week 11

## 1

See Guardian.java and Main.java

We added the KickOff message. Guardian's create() makes a new Behavior with the guardian constructor. When a KickOff message is received, onKickOff() is called, which creates a new MobileApp.

In guardian, we make a new ActorSystem to initialize the system, and we give it a Guardian. Then we tell the system to send a KickOff message

## 2

See Account.java

Added the state "balance". Added a message Deposit, that will add or subtract to the balance. When initialized, balance is 0;

## 3

See Bank.java

It has no state. It has one message (Transaction), which subtracts the amount from a1 and adds the amount to a2.

## 4

See MobileApp.java

One message (Payment), that takes two accounts, a bank, and an amount. When this message is received, it tells the given bank to make a transaction.

## 5

See onKickOff() in Guardian.java

## 6

See onPayment() in MobileApp.java

## 7

See onPrintBalance() in Account.java

The balance is printed before, because the order of the messages are not synchronized. As it takes more steps to update the balance, it happens later.

## 8

No. One message is handled (popped from the mailbox) at a time , and the order of deposits does not matter.

## 9

Changes made to Bank.java, and Account.java. 

Bank now initially only sends the withdrawal, additonally containing a reference to itself, and saves the transaction as an ongoing transaction.
Accounts are initialized with a reference to a Bank, and if an account receives a withdrawal from another Bank it sends a Rejection to the Bank referenced in the withdrawal.
When a Bank receives an Acceptance, it then sends the deposit to the other account.
When a Bank receives a Rejection, it removes the transaction from the ongoing transactions.
When an Account receives a deposit it sends a Finished to the referenced Bank.
When a Bank receives a Finined, it removes the transaction from the ongoing transactions.

## 10

Changes made to Account.java.

If a withdrawal in an Account would result in a balances of less than 0, it send a Rejection to the referenced Bank.