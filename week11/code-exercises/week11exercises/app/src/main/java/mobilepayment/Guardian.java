package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import mobilepayment.Bank.BankCommand;
import mobilepayment.MobileApp.MobileAppCommand;

public class Guardian extends AbstractBehavior<Guardian.GuardianCommand> {

    /* --- Messages ------------------------------------- */
    public interface GuardianCommand { }
    public static final class KickOff implements GuardianCommand {}

    /* --- State ---------------------------------------- */

    /* --- Constructor ---------------------------------- */
    private Guardian(ActorContext<GuardianCommand> context) {
	    super(context);
    }


    /* --- Actor initial state -------------------------- */
    public static Behavior<Guardian.GuardianCommand> create() {
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
        
        // Banks
        ActorRef<BankCommand> JB = getContext().spawn(Bank.create(), "JyskeBank");
        ActorRef<BankCommand> ALB = getContext().spawn(Bank.create(), "ArbejdernesLandsBank");

        // Accounts
        ActorRef<Account.AccountCommand> bob = Helpers.CreateAccount(getContext(), "bob", 100, JB);
        ActorRef<Account.AccountCommand> alice = Helpers.CreateAccount(getContext(), "alice", 100, ALB);

        // MobineApp
        ActorRef<MobileAppCommand> mobileapp1 = getContext().spawn(MobileApp.create(), "mobileapp1");
        ActorRef<MobileAppCommand> mobileapp2 = getContext().spawn(MobileApp.create(), "mobileapp2");

        // Interactions
        // mobileapp1.tell(new MobileApp.Payment(bob, alice, 25, JB));
        // mobileapp2.tell(new MobileApp.Payment(alice, bob, 5, ALB));
        JB.tell(new Bank.Transaction(bob, alice, 10, JB));

        bob.tell(new Account.PrintBalance());
        alice.tell(new Account.PrintBalance());
        

        return this;
    }
}

