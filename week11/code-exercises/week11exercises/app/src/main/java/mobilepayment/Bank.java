package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import mobilepayment.Account.AccountCommand;

public class Bank extends AbstractBehavior<Bank.BankCommand> {

    /* --- Messages ------------------------------------- */
    public interface BankCommand {
    }

    // Feel free to add message types at your convenience
    public static final class Transaction implements BankCommand {
        final ActorRef<AccountCommand> a1, a2;
        final int amount;

        public Transaction(ActorRef<AccountCommand> a1, ActorRef<AccountCommand> a2, int amount) {
            this.a1 = a1;
            this.a2 = a2;
            this.amount = amount;
        }
    }

    /* --- State ---------------------------------------- */
    // To be Implemented

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private Bank(ActorContext<BankCommand> context) {
        super(context);
    }

    /* --- Actor initial state -------------------------- */
    public static Behavior<BankCommand> create() {
        return Behaviors.setup(Bank::new);
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<BankCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(Transaction.class, this::onTransaction)
                .build();
    }

    /* --- Handlers ------------------------------------- */
    private Behavior<BankCommand> onTransaction(Transaction msg) {
        msg.a1.tell(new Account.Deposit(msg.amount * -1));
        msg.a2.tell(new Account.Deposit(msg.amount));
        return this;
    }
}
