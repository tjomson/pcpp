# Week 11

## 1

See Guardian.java and Main.java

We added the KickOff message. Guardian's create() makes a new Behavior with the guardian constructor. When a KickOff message is received, onKickOff() is called, which creates a new MobileApp.

In guardian, we make a new ActorSystem to initialize the system, and we give it a Guardian. Then we tell the system to send a KickOff message

## 2

See Account.java

Added the state "balance". Added a message Deposit, that will add or subtract to the balance. When initialized, balance is 0;