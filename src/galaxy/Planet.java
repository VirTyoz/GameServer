package galaxy;

import java.util.ArrayList;

import building.Building;

public class Planet {
	private int id;
	private String name;
	private int ownerId;
	private int systemId;
	private int x;
	private int y;
	private int z;
	public volatile Position position;
	public volatile Building build;
	public ArrayList<Building> list;
	
	
	public Planet(){
		position = new Position();
		build = new Building();
		list = new ArrayList<Building>();
		list.add(build);
		list.size();
		
	}
}
