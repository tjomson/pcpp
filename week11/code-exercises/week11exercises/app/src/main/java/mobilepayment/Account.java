package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class Account extends AbstractBehavior<Account.AccountCommand> {

    /* --- Messages ------------------------------------- */
    public interface AccountCommand {
    }

    // Feel free to add message types at your convenience

    public static final class Deposit implements AccountCommand {
        public final int amount;

        public Deposit(int amount) {
            this.amount = amount;
        }
    }

    public static final class PrintBalance implements AccountCommand {
    }

    /* --- State ---------------------------------------- */
    private int balance = 0;

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private Account(ActorContext<AccountCommand> context) {
        super(context);
    }

    /* --- Actor initial state -------------------------- */
    public static Behavior<AccountCommand> create() {
        return Behaviors.setup(Account::new);
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<AccountCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(Deposit.class, this::onDeposit)
                .onMessage(PrintBalance.class, this::onPrintBalance)
                .build();
    }

    /* --- Handlers ------------------------------------- */

    private Behavior<AccountCommand> onDeposit(Deposit msg) {
        this.balance += msg.amount;
        super.getContext().getLog().info("Depositting " + msg.amount);
        return this;
    }

    private Behavior<AccountCommand> onPrintBalance(PrintBalance msg) {
        super.getContext().getLog().info("Balance: " + this.balance);
        return this;
    }
}
