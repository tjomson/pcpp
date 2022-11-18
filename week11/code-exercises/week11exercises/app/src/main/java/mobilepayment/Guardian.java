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
        ActorRef<MobileApp.MobileAppCommand> app = getContext().spawn(MobileApp.create(), "g");

        return this;
    }
}
