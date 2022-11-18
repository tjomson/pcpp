package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.HashMap;
// Hint: You may generate random numbers using Random::ints
import java.util.Random;
import java.util.stream.IntStream;

public class MobileApp extends AbstractBehavior<MobileApp.MobileAppCommand> {

    /* --- Messages ------------------------------------- */
    public interface MobileAppCommand { }
    public static final class Payment implements MobileAppCommand {
        public final ActorRef<Account.AccountCommand> from;
        public final ActorRef<Account.AccountCommand> to;
        public final int amount;
        public final ActorRef<Bank.BankCommand> via;
        public Payment(ActorRef<Account.AccountCommand> f, ActorRef<Account.AccountCommand> t, int a, ActorRef<Bank.BankCommand> v) {
            from = f; to = t; amount = a; via = v;
        }
    }
    

    /* --- State ---------------------------------------- */
    

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private MobileApp(ActorContext context) {	
        super(context);
        context.getLog().info("Mobile app {} started!",
                    context.getSelf().path().name());
    }
    

    /* --- Actor initial state -------------------------- */
    public static Behavior<MobileApp.MobileAppCommand> create() {
        return Behaviors.setup((context) -> new MobileApp(context));
    }
    

    /* --- Message handling ----------------------------- */    
    @Override
    public Receive<MobileAppCommand> createReceive() {
	return newReceiveBuilder()
	    .onMessage(Payment.class, this::onPayment)
	    .build();
    }

    

    /* --- Handlers ------------------------------------- */
    private Behavior<MobileAppCommand> onPayment(Payment msg) {
        try {
            Random rng = new Random();
            for(int i = 0; i < 100; i++) {
                int amount = rng.nextInt();
                msg.via.tell(new Bank.Transaction(msg.from, msg.to, amount, msg.via));
                System.out.println("From " + msg.from + " to " + msg.to + " via " + msg.via);
            }
        } catch (Exception e) {
            System.out.println("Bank transaction failed");
        }
        return this;
    }
}
