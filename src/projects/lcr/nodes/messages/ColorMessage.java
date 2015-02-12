package projects.lcr.nodes.messages;

import sinalgo.nodes.messages.Message;

public class ColorMessage extends Message {

	final private int colorID;
	
	public ColorMessage(int i) {
		this.colorID = i;
	}
	
	public int getColorID() {
		return this.colorID;
	}
	
	@Override
	public Message clone() {
		return this;
	}

}