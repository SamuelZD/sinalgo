package projects.lcr.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import projects.lcr.nodes.messages.LcrMessage;
import projects.lcr.nodes.messages.ColorMessage;
import projects.lcr.nodes.timers.MessageTimer;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;


//TODO finish treatement
public class Node extends sinalgo.nodes.Node {
	
	static final int MAX_UIN = 10000;
	private int myUin;
	
	// array of les neighors
	private int[] arryOfNeihor = new int[2] ;
	private int[] arryOfColors = new int[2] ;
	
	@Override
	public void handleMessages(Inbox inbox) {
		/* code surprise
		while(inbox.hasNext()) {
			Message msg = inbox.next();
			if (msg instanceof LcrMessage) {
				treat((LcrMessage) msg);
			} 
		}
		*/
		
		// code coloriagee
		while(inbox.hasNext()){
			Message msg = inbox.next();
			//pour obtenir le plus grand ID
			if (msg instanceof LcrMessage){
				// comparer l'identifiant pour savoir si il faut attendre
				int uid = treat( ( LcrMessage)msg );
				
			}
			else if (msg instanceof ColorMessage){
				// sauver les couleurs choisits pour trouver le couleur différent 
			}
		}
		
	}
		
	private int treat(LcrMessage m) {
		/* code surprise
		int uin = m.getUin();
		System.out.println(this + " receives uin " + uin);
		if (uin == myUin) {
			this.setColor(Color.BLUE);
			System.out.println(this + " is the leader");
		} else if (uin > myUin) {
			System.out.println(this + " forwards " + uin);
			broadcast(new LcrMessage(uin));
		}
		*/
		
		//TODO
		//pour treater les identfiant
		return 0;
		
	}
	
	private void treat(ColorMessage m){
		//TODO
		// pour treater les couleur
	}

	@Override
	public void init() {
	    Random rand = sinalgo.tools.Tools.getRandomNumberGenerator();
	    myUin = rand.nextInt(MAX_UIN); 
		System.out.println(this + " is initialized. UIN = " + myUin);
		new MessageTimer(new LcrMessage(myUin)).startRelative(1, this);
	}

	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {
		drawNodeAsDiskWithText(g, pt, highlight, (new Integer(ID).toString()), 25, Color.white);
	}

	@NodePopupMethod(menuText="broadcast")
	public void broadcast() {
		broadcast(new LcrMessage(myUin));
	}
	
	@Override
	public void preStep() { }

	@Override
	public void neighborhoodChange() { }

	@Override
	public void postStep() {  }

	@Override
	public void checkRequirements() throws WrongConfigurationException { }
	
}
