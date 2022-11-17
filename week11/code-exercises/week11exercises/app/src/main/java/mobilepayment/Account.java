package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class Account extends AbstractBehavior<Account.AccountCommand> {

    /* --- Messages ------------------------------------- */
    public interface AccountCommand { }
    // Feel free to add message types at your convenience
    

    /* --- State ---------------------------------------- */
    // To be Implemented
    

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private Account(ActorContext<AccountCommand> context) {
	super(context);
    }
    

    /* --- Actor initial state -------------------------- */
    @Override
    public Receive<AccountCommand> createReceive() {
	return newReceiveBuilder()
	    // To be implemented
	    .build();
    }
    

    /* --- Message handling ----------------------------- */
    // To be Implemented
    

    /* --- Handlers ------------------------------------- */
    // To be Implemented
}
