package galaxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.thrift.TException;

import game.service.GameService;

public class GalaxySystem {
	private GameService.Client clientService;
	private int id;
	private int name;
	public HashMap <Integer,Planet> planets;
	
	public GalaxySystem(int id, GameService.Client clientService) throws TException{
			this.clientService = clientService;
			this.id = id;
			List<Integer> planetsId = new ArrayList<Integer>();
			planetsId = clientService.getSelectedId("planets","system_id",id);
			
			
			planets = new HashMap<Integer,Planet>();
			
			for(int i=0; i<planetsId.size();i++){
				planets.put(planetsId.get(i),new Planet(planetsId.get(i), id, clientService));
			}
			
			//ArrayList<Planet> planets = new ArrayList<Planet>();
			/*for(int i=0; i<planetsId.size();i++){
				planets.add(new Planet(planetsId.get(i)));
			}*/
	}
		
}
