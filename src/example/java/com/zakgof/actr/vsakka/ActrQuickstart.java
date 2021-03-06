package com.zakgof.actr.vsakka;

import java.io.IOException;

import com.zakgof.actr.ActorRef;
import com.zakgof.actr.ActorSystem;

public class ActrQuickstart {

	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("helloactr");
		try {
			// #create-actors
			final ActorRef<Printer> printerActor = system.actorOf(Printer::new, "printerActor");
			final ActorRef<Greeter> howdyGreeter = system.actorOf(() -> new Greeter("Howdy", printerActor), "howdyGreeter");
			final ActorRef<Greeter> helloGreeter = system.actorOf(() -> new Greeter("Hello", printerActor), "helloGreeter");
			final ActorRef<Greeter> goodDayGreeter = system.actorOf(() -> new Greeter("Good day", printerActor), "goodDayGreeter");
			//#create-actors

			// #main-send-messages
			howdyGreeter.tell(gr -> gr.setWhoToGreet("Actr"));
			howdyGreeter.tell(Greeter::greet);

			howdyGreeter.tell(gr -> gr.setWhoToGreet("Zakgof"));
			howdyGreeter.tell(Greeter::greet);

			helloGreeter.tell(gr -> gr.setWhoToGreet("Java"));
			helloGreeter.tell(Greeter::greet);

			goodDayGreeter.tell(gr -> gr.setWhoToGreet("Lambda"));
			goodDayGreeter.tell(Greeter::greet);
			//#main-send-messages

			System.out.println(">>> Press ENTER to exit <<<");
			System.in.read();
		} catch (IOException ioe) {
		} finally {
			system.shutdown();
		}
	}

}
