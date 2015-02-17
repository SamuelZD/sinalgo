package projects.lcr.nodes.messages;

import sinalgo.nodes.messages.Message;
import sinalgo.nodes.Node;

public class SensMessage extends Message {
	
	private Node nodeReponse = null;
	private Node nodeRequest = null;
	
	
	public void setnodeRequest(Node node){
		this.nodeRequest = node;
	}
	
	public Node getNodeReponse(){
		return this.nodeReponse;
	}
	
	public Node getnodeRequest(){
		return this.nodeRequest;
	}

	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
