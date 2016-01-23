package game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

import building.Building;
import game.pocket.Planets;
import game.pocket.PlanetsInfo;
import game.pocket.RequestPlanetInfo;

public class Client {
	private TSerializer serializer;
	private TDeserializer deserializer;
	//public volatile Planets planets;
	public volatile PlanetsInfo planetsInfo;
	public volatile RequestPlanetInfo requestPlanetInfo;
	public volatile GalaxyInitializer galaxyInitializer;
	
	private static final Logger logger = LogManager.getLogger(Client.class);
	
	public Client(GalaxyInitializer galaxyInitializer){

		planetsInfo = new PlanetsInfo();
		requestPlanetInfo = new RequestPlanetInfo();
		
		this.galaxyInitializer = galaxyInitializer;
		serializer = new TSerializer(new TBinaryProtocol.Factory());
		deserializer = new TDeserializer(new TBinaryProtocol.Factory());
		
	}
	
	public byte[] sendPlanetInfo(byte[] bytes){
		try {
			deserializer.deserialize(requestPlanetInfo, bytes);
			
			logger.info("Test"+requestPlanetInfo.systemid);
			
			for(Integer key: galaxyInitializer.galaxySystem.get(requestPlanetInfo.systemid).planets.keySet()){
				Planets planets = new Planets();
				planets.planetid = (short) galaxyInitializer.galaxySystem.get(requestPlanetInfo.systemid).planets.get(key).id;
				planets.x = (short) galaxyInitializer.galaxySystem.get(requestPlanetInfo.systemid).planets.get(key).x;
				planets.y = (short) galaxyInitializer.galaxySystem.get(requestPlanetInfo.systemid).planets.get(key).y;
				planets.z = (short) galaxyInitializer.galaxySystem.get(requestPlanetInfo.systemid).planets.get(key).z;
				planetsInfo.PlanetList.add(planets);
			}
			planetsInfo.id = 2;
			return serializer.serialize(planetsInfo);
		} catch (TException e) {
			logger.info("SendPlanetInfo error"+e);
		}
		return null;
		
	}

}
