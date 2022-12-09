package mathsserver;

// Hint: The imports below may give you hints for solving the exercise.
//       But feel free to change them.

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.ChildFailed;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.*;

import java.util.Queue;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.IntStream;

import mathsserver.Task;
import mathsserver.Task.BinaryOperation;
import scala.Tuple2;

public class Server extends AbstractBehavior<Server.ServerCommand> {
    /* --- Messages ------------------------------------- */
    public interface ServerCommand { }
    
    public static final class ComputeTasks implements ServerCommand {
		public final List<Task> tasks;
		public final ActorRef<Client.ClientCommand> client;

		public ComputeTasks(List<Task> tasks, ActorRef<Client.ClientCommand> client) {
			this.tasks  = tasks;
			this.client = client;
		}
    }

    public static final class WorkDone implements ServerCommand {
		ActorRef<Worker.WorkerCommand> worker;

		public WorkDone(ActorRef<Worker.WorkerCommand> worker) {
			this.worker = worker;
		}
    }
    
    /* --- State ---------------------------------------- */
	private int workerIds = 0;
	private int curWorkers;
	private int maxWorkers;
    private Queue<ActorRef<Worker.WorkerCommand>> idleWorkers = new LinkedList<>();
    private HashSet<ActorRef<Worker.WorkerCommand>> busyWorkers = new HashSet<>();
	private Queue<Tuple2<ActorRef<Client.ClientCommand>, Task>> tasks = new LinkedList<>();
    
    

    /* --- Constructor ---------------------------------- */
    private Server(ActorContext<ServerCommand> context, int minWorkers, int maxWorkers) {
    	super(context);
		this.curWorkers = minWorkers;
		this.maxWorkers = maxWorkers;
		for(int i = 0; i < minWorkers; i++) {
			ActorRef<Worker.WorkerCommand> worker = context.spawn(Worker.create(context.getSelf()), "worker_"+(workerIds++));
			getContext().watch(worker);
			idleWorkers.add(worker);
		}
    }


    /* --- Actor initial state -------------------------- */
    public static Behavior<ServerCommand> create(int minWorkers, int maxWorkers) {
    	return Behaviors.setup(context -> new Server(context, minWorkers, maxWorkers));
    }
    

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<ServerCommand> createReceive() {
    	return newReceiveBuilder()
    	    .onMessage(ComputeTasks.class, this::onComputeTasks)
    	    .onMessage(WorkDone.class, this::onWorkDone)
			// To be extended
			.onSignal(ChildFailed.class, this::onCrash)
    	    .build();
    }


    /* --- Handlers ------------------------------------- */
    public Behavior<ServerCommand> onComputeTasks(ComputeTasks msg) {
		for (Task t : msg.tasks) {
			if (!idleWorkers.isEmpty()) {
				ActorRef<Worker.WorkerCommand> worker = idleWorkers.poll();
				busyWorkers.add(worker);
				worker.tell(new Worker.ComputeTask(t, msg.client));
			}
			else if (curWorkers < maxWorkers) {
				curWorkers++;
				ActorRef<Worker.WorkerCommand> worker = getContext().spawn(Worker.create(getContext().getSelf()), "worker"+(workerIds++));
				getContext().watch(worker);
				busyWorkers.add(worker);
				worker.tell(new Worker.ComputeTask(t, msg.client));
			}
			else {
				tasks.add(new Tuple2<ActorRef<Client.ClientCommand>,Task>(msg.client, t));
			}
		}
    	return this;
    }

    public Behavior<ServerCommand> onWorkDone(WorkDone msg) {
		if (!busyWorkers.contains(msg.worker)) return this;
		if (!tasks.isEmpty()) {
			Tuple2<ActorRef<Client.ClientCommand>, Task> task = tasks.poll();
			msg.worker.tell(new Worker.ComputeTask(task._2, task._1()));
		}
		else {
			busyWorkers.remove(msg.worker);
			idleWorkers.add(msg.worker);
		}
		return this;	
    }    

	public Behavior<ServerCommand> onCrash(ChildFailed fail) {
		ActorRef<Worker.WorkerCommand> worker = getContext().spawn(Worker.create(getContext().getSelf()), "worker_"+(workerIds++));
		getContext().watch(worker);
		idleWorkers.add(worker);
		return this;
	}
}
