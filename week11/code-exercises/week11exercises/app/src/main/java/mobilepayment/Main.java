package mobilepayment;

import akka.actor.typed.ActorSystem;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		final ActorSystem<Guardian.GuardianCommand> system = ActorSystem.create(Guardian.create(), "bank");

		system.tell(new Guardian.KickOff());

		// start actor system
		// To be implemented

		// init message
		// To be implemented

		// wait until user presses enter
		try {
			System.out.println(">>> Press ENTER to exit <<<");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		} finally {
			// terminate actor system execution
			// To be implemented
		}

	}

}
