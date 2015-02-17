package projects.lcr.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import projects.lcr.nodes.messages.LcrMessage;
import projects.lcr.nodes.messages.SensMessage;
import projects.lcr.nodes.messages.ColorMessage;
import projects.lcr.nodes.timers.MessageTimer;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class Node extends sinalgo.nodes.Node {
	
	static final int MAX_UIN = 10000;
	private int myUin;
	private int colorID = 0;
	private boolean root = false; //verifier si je suis root.
	private int countRecuMsgColorRoot = 0;
	private int[] arrayColor = new int[6];
	private int lePlusPetitGrand = MAX_UIN;
	private int nombreCompare = 0;

	
	//记录比自己uid大的邻居的数目
	//private int nbGranVoisin=2;
	//记录有几个邻居选择了颜色
	//private int nbColeurChoix=0;
	
	@Override
	public void handleMessages(Inbox inbox) {
		
		////////////////////////////////////////

		while(inbox.hasNext()) {
			Message msg = inbox.next();
			if (msg instanceof LcrMessage) {
				treat((LcrMessage) msg);
			}
			else if(msg instanceof SensMessage ){
				treat((SensMessage) msg);
			}
			else
				treat((ColorMessage)msg);
		}
		////////////////////////////////////////
		

		
		
	}
	
	
	/**
	 * @bref chosir le sens de circle
	 * @param msg  SensMessage pour controler
	 */
	private void treat(SensMessage msg){
		//si je suis root
		if(this.root){
			Node node = msg.getNodeReponse();
			this.send(new ColorMessage(this.myUin, this.colorID), node);
		}
		//je ne suis pas le root
		else{
			//seule les voisin de distance 1 du root peut traiter et l'envoie
			if(msg.getFois()==1){
				msg.setFois();
				msg.setnodeRequest(this);
				this.broadcast(msg);
			}
		}
	}

	private void treat(ColorMessage msg){
		//si je suis root
		if(this.root){
			if(this.countRecuMsgColorRoot == 0){
				this.countRecuMsgColorRoot ++;
			}
			else if(this.countRecuMsgColorRoot == 1){
				this.broadcast(new ColorMessage(this.myUin,this.colorID));
			}
		}
		//les actions pour tous les noeuds
		if(msg.getColorID() == 0){
			this.lePlusPetitGrand = (msg.getUidOriginal()<this.lePlusPetitGrand && msg.getUidOriginal() > this.myUin)
						?msg.getUidOriginal():this.lePlusPetitGrand;
			this.nombreCompare++;
		}
		this.arrayColor[msg.getColorID()] = 1;
		if(this.nombreCompare == 2){
			if(this.lePlusPetitGrand == MAX_UIN || this.lePlusPetitGrand == msg.getUidOriginal()){
				//choisir le couleur et l'envoie
				for(int i= 0; i < 6 ;i++){
					if(this.arrayColor[i] != 1 ){
						this.colorID = i;
						break;
					}
				}
				//TODO
				switch (this.colorID){
				case 1: this.setColor(Color.BLACK);break;
				case 2: this.setColor(Color.RED);break;
				case 3: this.setColor(Color.blue);break;
				case 4: this.setColor(Color.CYAN);break;
				case 5: this.setColor(Color.GREEN);break;
				}
				broadcast(new ColorMessage(this.myUin, this.colorID));
			}
		}
		
	}
		
	private void treat(LcrMessage m) {
		
		//////////////////////////////////////////////
		//avant  choisir le leader
		int uin = m.getUin();
		System.out.println(this + " receives uin " + uin);
		if (uin == myUin) {
			this.setColor(Color.BLUE);
			this.root = true;
			// le leader envoie le SensMessage
			broadcast(new SensMessage(this));
			
			System.out.println(this + " is the leader");
		} else if (uin > myUin) {
			System.out.println(this + " forwards " + uin);
			broadcast(new LcrMessage(uin));
		}
		
		//////////////////////////////////////////////
		
		/*
		// 初次尝试
		if(this.myUin > m.getUin() || m.getColorID()==0){
			this.nbGranVoisin--;
		}
		
		if( this.myUin < m.getUin() && m.getColorID()!=0 ){
			this.nbGranVoisin--;
			this.nbColeurChoix++;
		}
		
		
		//on choisit un couleur
		if(this.nbGranVoisin == 0){
			switch(this.nbColeurChoix){
				case 0 : this.colorID = 1 ;
				case 1 : this.colorID = m.getColorID()==1 ? 2 : 1 ;
				case 2 : this.colorID = 3;
				
			}
			
		}
		
		if(this.colorID != 0)
		{
			switch (this.colorID){
			case 1 : this.setColor(Color.BLUE);
			case 2 : this.setColor(Color.RED);
			case 3 : this.setColor(Color.BLACK);
			}
			
			broadcast(new LcrMessage(this.myUin,this.colorID));
		}*/
		//新思路  le plus grand ID
		
		
	}
	
	

	@Override
	public void init() {
	    Random rand = sinalgo.tools.Tools.getRandomNumberGenerator();
	    myUin = rand.nextInt(MAX_UIN); 
		System.out.println(this + " is initialized. UIN = " + myUin);
		new MessageTimer(new LcrMessage(myUin)).startRelative(1, this);
		for(int i = 0; i< 5; i++){
			this.arrayColor[i] = 0;
		}
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