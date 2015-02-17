package projects.lcr.nodes.messages;

import sinalgo.nodes.messages.Message;
import projects.lcr.nodes.nodeImplementations.Node;;;

public class SensMessage extends Message {
	
	private Node nodeReponse = null;
	private Node nodeRequest = null;
	private int fois = 1;
	
	public SensMessage(Node node){
		this.nodeRequest = node;
	}
	
	public void setnodeRequest(Node node){
		this.nodeRequest = node;
	}
	
	public Node getNodeReponse(){
		return this.nodeReponse;
	}
	
	public Node getnodeRequest(){
		return this.nodeRequest;
	}
	
	public void setFois(){
		this.fois--;
	}
	public int getFois(){
		return this.fois;
	}

	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
