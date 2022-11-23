package mobilepayment;

import java.util.HashSet;
import java.util.Set;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;


public class Bank extends AbstractBehavior<Bank.BankCommand> {

    /* --- Messages ------------------------------------- */
    public interface BankCommand { }
    public static final class Transaction implements BankCommand {
        public final ActorRef<Account.AccountCommand> from;
        public final ActorRef<Account.AccountCommand> to;
        public final int amount;
        public final ActorRef<Bank.BankCommand> handling_bank;
        public Transaction(ActorRef<Account.AccountCommand> f, ActorRef<Account.AccountCommand> t, int a, ActorRef<Bank.BankCommand> h) {
            from = f; to = t; amount = a; handling_bank = h;
        }
    }
    public static final class RejectTransaction implements BankCommand {
        public final Transaction rejected;
        public final String reason;
        public RejectTransaction(Transaction t, String r) {
            rejected = t;
            reason = r;
        }
    }
    public static final class AcceptTransaction implements BankCommand {
        public final Transaction accepted;
        public AcceptTransaction(Transaction t) {
            accepted = t;
        }
    }
    public static final class FinishTransaction implements BankCommand {
        public final Transaction finished;
        public FinishTransaction(Transaction t) {
            finished = t;
        }
    }
    

    /* --- State ---------------------------------------- */
    Set<Transaction> ongoingTransactions = new HashSet<>();
    

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private Bank(ActorContext<BankCommand> context) {
	    super(context);
    }
    

    /* --- Actor initial state -------------------------- */
    public static Behavior<Bank.BankCommand> create() {
        return Behaviors.setup((context) -> new Bank(context));
    }     


    /* --- Message handling ----------------------------- */
    @Override
    public Receive<BankCommand> createReceive() {
	return newReceiveBuilder()
	    .onMessage(Transaction.class, this::onTransaction)
        .onMessage(RejectTransaction.class, this::onRejection)
        .onMessage(AcceptTransaction.class, this::onAcceptance)
        .onMessage(FinishTransaction.class, this::onFinish)
	    .build();
    }
    

    /* --- Handlers ------------------------------------- */
    private Behavior<BankCommand> onTransaction(Transaction msg) {
        try {
            System.out.println("Transaction " +msg.from+ " --["+msg.amount+"]-> " +msg.to+ " started");
            msg.from.tell(new Account.Deposit(msg, -msg.amount));
            ongoingTransactions.add(msg);
        } catch (Exception e) {
            System.out.println("Bank transaction failed");
        }
        return this;
    }

    private Behavior<BankCommand> onRejection(RejectTransaction msg) {
        try {
            if (!ongoingTransactions.contains(msg.rejected)) return this;
            System.out.println("Transaction " +msg.rejected.from+ " --["+msg.rejected.amount+"]-> " +msg.rejected.to+ " rejected: " + msg.reason);
            ongoingTransactions.remove(msg.rejected);
        } catch (Exception e) {
            System.out.println("Bank transaction failed");
        }
        return this;
    }

    private Behavior<BankCommand> onAcceptance(AcceptTransaction msg) {
        try {
            if (!ongoingTransactions.contains(msg.accepted)) return this;
            System.out.println("Transaction " +msg.accepted.from+ " --["+msg.accepted.amount+"]-> " +msg.accepted.to+ " accepted");
            msg.accepted.to.tell(new Account.Deposit(msg.accepted, msg.accepted.amount));
        } catch (Exception e) {
            System.out.println("Bank transaction failed");
        }
        return this;
    }

    private Behavior<BankCommand> onFinish(FinishTransaction msg) {
        try {
            if (!ongoingTransactions.contains(msg.finished)) return this;
            System.out.println("Transaction " +msg.finished.from+ " --["+msg.finished.amount+"]-> " +msg.finished.to+ " finished");
            ongoingTransactions.remove(msg.finished);
        } catch (Exception e) {
            System.out.println("Bank transaction failed");
        }
        return this;
    }
}
