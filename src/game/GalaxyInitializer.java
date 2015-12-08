package game;

import galaxy.Planet;

public class GalaxyInitializer{
	public volatile Planet haol;
	public volatile Planet sutin;
	public volatile Planet karguna;
	public volatile Planet korrigun;
	public volatile Planet nupruha;

	
	public GalaxyInitializer(){
		haol = new Planet();
		sutin = new Planet();
		karguna = new Planet();
		korrigun = new Planet();
		nupruha = new Planet();
	}
}