package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import galaxy.GalaxySystem;
import game.service.GameService;

public class GalaxyInitializer{
	private static final Logger logger = LogManager.getLogger(GameServer.class);
	private GameService.Client clientService;
	public HashMap <Integer,GalaxySystem> galaxySystem;
	
	public GalaxyInitializer(GameService.Client clientService){
		this.clientService = clientService;
		
		galaxySystem = new HashMap<Integer,GalaxySystem>();
		
		List<Integer> galaxySystemId = new ArrayList<Integer>();
		
		
		try {
				galaxySystemId = clientService.getAllId("systems","id");
				for(int i=0; i<galaxySystemId.size();i++){
				
				galaxySystem.put(galaxySystemId.get(i),new GalaxySystem(galaxySystemId.get(i), clientService));
			}
			
		} catch (Exception e) {
			logger.catching(e);
		}
		
		
	}
}