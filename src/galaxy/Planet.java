package galaxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;

import building.Building;
import game.pocket.PlanetInfo;
import game.service.GameService;

public class Planet {
	private static final Logger logger = LogManager.getLogger(Planet.class);
	private GameService.Client clientService;
	public int id;
	private String name;
	private int ownerId;
	private int systemId;
	public int x;
	public int y;
	public int z;
	public volatile Position position;
	public HashMap <Integer,Building> buldings;
	
	
	public Planet(int id, int systemId, GameService.Client clientService) throws TException{
		this.clientService = clientService;
		this.id = id;
		this.systemId = systemId;
		
		
		List<Integer> buildingId = new ArrayList<Integer>();
		buildingId = clientService.getSelectedId("built","planet_id",id);
		
		buldings = new HashMap <Integer,Building>();
		
		for(int i=0; i<buildingId.size();i++){
			buldings.put(buildingId.get(i),new Building(buildingId.get(i)));
		}
	}
	
	public void getAllInfo(){
		PlanetInfo planetInfo = new PlanetInfo();
		try {
			planetInfo = clientService.getPlanetInfo(id);
			planetInfo.name = name;
			planetInfo.ownerId = ownerId;
			planetInfo.x = x;
			planetInfo.y = y;
			planetInfo.z = z;
		} catch (Exception e) {
			logger.catching(e);
		}
	}
}
