package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import mobilepayment.Account.AccountCommand;
import mobilepayment.Bank.BankCommand;

// Hint: You may generate random numbers using Random::ints
import java.util.Random;
import java.util.stream.IntStream;

public class MobileApp extends AbstractBehavior<MobileApp.MobileAppCommand> {

    /* --- Messages ------------------------------------- */
    public interface MobileAppCommand {
    }

    public static final class Payment implements MobileAppCommand {
        final ActorRef<AccountCommand> a1, a2;
        final ActorRef<BankCommand> b1;
        final int amount;

        public Payment(ActorRef<AccountCommand> a1, ActorRef<AccountCommand> a2, ActorRef<BankCommand> b1, int amount) {
            this.a1 = a1;
            this.a2 = a2;
            this.b1 = b1;
            this.amount = amount;
        }
    }
    // Feel free to add message types at your convenience

    /* --- State ---------------------------------------- */
    // To be Implemented

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private MobileApp(ActorContext context) {
        super(context);
        context.getLog().info("Mobile app {} started!",
                context.getSelf().path().name());
    }

    /* --- Actor initial state -------------------------- */
    public static Behavior<MobileApp.MobileAppCommand> create() {
        return Behaviors.setup(MobileApp::new);
        // You may extend the constructor if necessary
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
        msg.b1.tell(new Bank.Transaction(msg.a1, msg.a2, msg.amount));
        return this;
    }
}
