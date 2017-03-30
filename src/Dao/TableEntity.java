package Dao;

import java.util.ArrayList;
import java.util.List;

public class TableEntity {
	public List<String> columns = new ArrayList<String>();
	public List<Object> values = new ArrayList<Object>();
	
	public String ListeColumns(){
		StringBuffer sb= new StringBuffer("[");
		for (String col : columns) {
			sb.append(col+" | ");
		}
		sb.replace(sb.length()-2, sb.length(), "").append("]");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuffer sb= new StringBuffer("[");
		for (Object value : values) {
			sb.append(value+", ");
		}
		sb.replace(sb.length()-2, sb.length(), "").append("]");
		return sb.toString();
	}
}
