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
