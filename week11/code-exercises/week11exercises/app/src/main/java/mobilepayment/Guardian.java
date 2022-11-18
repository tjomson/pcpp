package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class Guardian extends AbstractBehavior<Guardian.GuardianCommand> {

    /* --- Messages ------------------------------------- */
    public interface GuardianCommand {
    }

    public static final class KickOff implements GuardianCommand {
    }
    // Feel free to add message types at your convenience

    /* --- State ---------------------------------------- */
    // empty

    /* --- Constructor ---------------------------------- */
    private Guardian(ActorContext<GuardianCommand> context) {
        super(context);
    }

    /* --- Actor initial state -------------------------- */
    public static Behavior<GuardianCommand> create() {
        return Behaviors.setup(Guardian::new);
    }

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<GuardianCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(KickOff.class, this::onKickOff)
                .build();
    }

    /* --- Handlers ------------------------------------- */
    private Behavior<GuardianCommand> onKickOff(KickOff msg) {
        ActorRef<MobileApp.MobileAppCommand> app1 = getContext().spawn(MobileApp.create(), "app1");
        ActorRef<MobileApp.MobileAppCommand> app2 = getContext().spawn(MobileApp.create(), "app2");
        ActorRef<Bank.BankCommand> bank1 = getContext().spawn(Bank.create(), "bank1");
        ActorRef<Bank.BankCommand> bank2 = getContext().spawn(Bank.create(), "bank2");
        ActorRef<Account.AccountCommand> acc1 = getContext().spawn(Account.create(), "acc1");
        ActorRef<Account.AccountCommand> acc2 = getContext().spawn(Account.create(), "acc2");

        app1.tell(new MobileApp.Payment(acc1, acc2, bank1, 200));
        app1.tell(new MobileApp.Payment(acc2, acc1, bank2, 300));

        return this;
    }
}
