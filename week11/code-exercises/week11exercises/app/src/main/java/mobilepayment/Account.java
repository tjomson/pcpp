package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import mobilepayment.Bank.BankCommand;

public class Account extends AbstractBehavior<Account.AccountCommand> {

    /* --- Messages ------------------------------------- */
    public interface AccountCommand { }
    public static final class Deposit implements AccountCommand {
        public final Bank.Transaction related_transaction;
        public final long amount;
        public Deposit(Bank.Transaction t, long a) { 
            related_transaction = t;
            amount = a; 
        }
    };
    public static final class PrintBalance implements AccountCommand {}
    
    

    /* --- State ---------------------------------------- */
    private final String holder;
    private final ActorRef<Bank.BankCommand> registered_bank;
    private long balance;
    

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private Account(ActorContext<AccountCommand> context, String h, long bal, ActorRef<Bank.BankCommand> b) {
	    super(context);
        holder = h;
        balance = bal;
        registered_bank = b;
    }
    

    /* --- Actor initial state -------------------------- */
    public static Behavior<Account.AccountCommand> create(String holder, long balance, ActorRef<Bank.BankCommand> bank) {
        return Behaviors.setup((ActorContext<AccountCommand> context) -> new Account(context, holder, balance, bank));
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
    private Behavior<AccountCommand> onDeposit(Deposit deposit) {
        if (deposit.amount < 0) {
            if (deposit.related_transaction.handling_bank != registered_bank) 
                deposit.related_transaction.handling_bank.tell(new Bank.RejectTransaction(deposit.related_transaction, "Not the registered bank of " + holder));
            else if (balance - deposit.amount < 0) 
                deposit.related_transaction.handling_bank.tell(new Bank.RejectTransaction(deposit.related_transaction, "Not enough money on balance"));
            else {
                balance += deposit.amount;
                deposit.related_transaction.handling_bank.tell(new Bank.AcceptTransaction(deposit.related_transaction));
            }
        }
        else {
            balance += deposit.amount;
            deposit.related_transaction.handling_bank.tell(new Bank.FinishTransaction(deposit.related_transaction));
        }
        return this;
    }

    private Behavior<AccountCommand> onPrintBalance(PrintBalance msg) {
        System.out.println(holder + " has " + balance + "DKK");
        return this;
    }
}
