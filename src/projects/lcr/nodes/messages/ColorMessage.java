package projects.lcr.nodes.messages;
 
import sinalgo.nodes.messages.Message;

public class ColorMessage extends Message {

	private int uidoriginal = 0;
	private int colorID = -1 ;
	private int rootRenvoi = 0; //pour identifier si le root renvoie le message ?
	                              // si oui 1; sinon 0
	
	public void setUidOriginal(int id){
		this.uidoriginal = id;
	}
	public void setColorID(int cid){
		this.colorID = cid;
	}
	public void setRootRenvoi(int i){
		this.rootRenvoi = i;
	}
	
	public int getUidOriginal(){
		return this.uidoriginal;
	}
	
	public int getColorID(){
		return this.colorID;
	}
	public int getRootRenvoi(){
		return this.rootRenvoi;
	}
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return null;
	}

}