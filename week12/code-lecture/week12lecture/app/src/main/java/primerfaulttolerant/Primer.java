package primerfaulttolerant;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Random;

public class Primer extends AbstractBehavior<Primer.PrimerCommand> {
    /* --- Messages ------------------------------------- */
    public interface PrimerCommand { }

    public static final class CheckPrime implements PrimerCommand {
	public final int number;
	
	public CheckPrime(int number) {
	    this.number = number;
	}
    }

    public static final class PrimeResult implements PrimerCommand {
	public final int prime;
	public final Boolean isPrime;
	
	public PrimeResult(int prime, Boolean isPrime) {
	    this.prime = prime;
	    this.isPrime = isPrime;
	}
    }

    public static final class StartPrimer implements PrimerCommand {
	public final int numWorkers;

	public StartPrimer(int numWorkers) {
	    this.numWorkers = numWorkers;
	}
    }

    
    /* --- State ---------------------------------------- */
    private final List<ActorRef<Worker.IsPrime>> workers;

    // Unnecessary, just to see if everything is computed
    private int computedTasks;
    private int pendingTasks;
    private long initTime;


    
    /* --- Constructor ---------------------------------- */
    private Primer(ActorContext<PrimerCommand> context) {
	super(context);
	workers = new ArrayList<ActorRef<Worker.IsPrime>>();
	this.computedTasks = 0;
	this.pendingTasks  = 0;
	this.initTime = System.currentTimeMillis();
    }

    /* --- Actor initial behavior ----------------------- */
    public static Behavior<PrimerCommand> create() {
	return Behaviors.setup(Primer::new);
    }

    
    /* --- Message handling ----------------------------- */
    @Override
    public Receive<PrimerCommand> createReceive() {
	return newReceiveBuilder()
	    .onMessage(CheckPrime.class, this::onCheckPrime)
	    .onMessage(PrimeResult.class, this::onPrimeResult)
	    .onMessage(StartPrimer.class, this::onStartPrimer)
	    .build();
    }

    /* --- Handlers ------------------------------------- */
    public Behavior<PrimerCommand> onCheckPrime(CheckPrime msg) {
	final ActorRef<Worker.IsPrime> selectedWorker = workers.get(msg.number%workers.size());
	getContext().getLog().info("{}: Cheking whether {} is prime by worker {}",
				   getContext().getSelf().path().name(),
				   msg.number, selectedWorker.path().name());
	pendingTasks++;
	selectedWorker.tell(new Worker.IsPrime(msg.number));
	return this;
    }

    public Behavior<PrimerCommand> onPrimeResult(PrimeResult msg) {
	computedTasks++;
	if(computedTasks == 1_000_000) {
	    getContext().getLog().info("Execution time: {}ms",
				       System.currentTimeMillis() - initTime);	    
	}	    
	getContext().getLog().info("{}: Number {} is {} prime. [{}/{}]",
				   getContext().getSelf().path().name(), msg.prime,
				   msg.isPrime ? "" : "not",
				   computedTasks,pendingTasks);
	return this;
    }

    public Behavior<PrimerCommand> onStartPrimer(StartPrimer msg) {
	IntStream
	    .range(1,msg.numWorkers+1)
	    .forEach((workerId) -> {
		    final ActorRef<Worker.IsPrime> worker =
			getContext().spawn(Worker.create(getContext().getSelf()),
					   "worker_"+workerId);
		    workers.add(worker);
		});
	getContext().getLog().info("{}: Server and workers started",
				   getContext().getSelf().path().name());
	return this;
    }
}
