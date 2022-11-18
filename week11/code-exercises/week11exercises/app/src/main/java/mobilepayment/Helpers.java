package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;

public class Helpers {
    public static ActorRef<Account.AccountCommand> CreateAccount(ActorContext<Guardian.GuardianCommand> context, String holder, long balance, ActorRef<Bank.BankCommand> bank) {
        Behavior<Account.AccountCommand> behaveior = Account.create(holder, balance, bank);
        return context.spawn(behaveior, holder);
    }
}
