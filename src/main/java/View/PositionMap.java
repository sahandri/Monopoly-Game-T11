package View;

import java.util.HashMap;
import java.util.Map;

public class PositionMap {
	private Map<Integer,int[]> GUIposition = new HashMap<Integer,int[]>();
	
	public PositionMap(){
		createMap();
	}
	
	public int getX(int position){
		return GUIposition.get(position)[0];
	}
	
	public int getY(int position){
		return GUIposition.get(position)[1];
	}
	
	private void createMap(){
		GUIposition.put(0, new int[]{530,490});
		
		GUIposition.put(1, new int[]{470,490});
		
		GUIposition.put(2, new int[]{424,490});
		
		GUIposition.put(3, new int[]{374,490});
		
		GUIposition.put(4, new int[]{328,490});
		
		GUIposition.put(5, new int[]{280, 490});
		
		GUIposition.put(6, new int[]{232 ,490 });
		
		GUIposition.put(7, new int[]{185, 490});
		
		GUIposition.put(8, new int[]{138,490 });
		
		GUIposition.put(9, new int[]{89, 490});
		
		GUIposition.put(10, new int[]{35, 490});
		
		GUIposition.put(11, new int[]{35, 438});
		
		GUIposition.put(12, new int[]{35, 397});
		
		GUIposition.put(13, new int[]{35, 343});
		
		GUIposition.put(14, new int[]{ 35, 299});
		
		GUIposition.put(15, new int[]{ 35, 251});
		
		GUIposition.put(16, new int[]{ 35, 203});
		
		GUIposition.put(17, new int[]{35 ,155 });
		
		GUIposition.put(18, new int[]{ 35, 110});
		
		GUIposition.put(19, new int[]{ 35, 57});
		
		GUIposition.put(20, new int[]{ 35, 6});
		
		GUIposition.put(21, new int[]{ 92, 6});
		
		GUIposition.put(22, new int[]{ 136, 6});
		
		GUIposition.put(23, new int[]{ 184, 6});
		
		GUIposition.put(24, new int[]{ 232, 6});
		
		GUIposition.put(25, new int[]{ 279, 6});
		
		GUIposition.put(26, new int[]{ 325, 6});
		
		GUIposition.put(27, new int[]{ 375, 6});
		
		GUIposition.put(28, new int[]{ 421, 6});
		
		GUIposition.put(29, new int[]{ 468, 6});
		
		GUIposition.put(30, new int[]{ 519, 6});
		
		GUIposition.put(31, new int[]{ 519, 66});
		
		GUIposition.put(32, new int[]{ 519, 110});
		
		GUIposition.put(33, new int[]{ 519, 155});
		
		GUIposition.put(34, new int[]{ 519, 205});
		
		GUIposition.put(35, new int[]{519 , 249});
		
		GUIposition.put(36, new int[]{ 519, 300});
		
		GUIposition.put(37, new int[]{ 519, 345});
		
		GUIposition.put(38, new int[]{ 519, 392});
		
		GUIposition.put(39, new int[]{ 519, 440});
	}
}
