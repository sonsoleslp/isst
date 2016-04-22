package es.upm.dit.isst.socialTV.bs.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import javax.print.attribute.standard.PrinterLocation;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.mapfish.geo.*;

public class Spain {
	private static Spain instance = null;
	public static ArrayList<MultiPolygon> mp = new  ArrayList<MultiPolygon>();
	String[] provincias = new String[52];
	public static String  pais =  Spain.class.getResource("spain.geojson").getPath();
	
	public Spain(){

	}
	public static Spain getInstance() {
		if (instance == null){
			instance = new Spain();
			instance.fill();
		}
		return instance;
	}
	
	public String whichProvince(double lng, double lat){
		int ind = 0;

		Geometry g = new GeometryFactory().createPoint(new Coordinate(lng,lat));
		for (MultiPolygon mul : mp){
			if(mul.contains(g)) {
				return provincias[ind];
			}
			ind++;
    	}  
		return "Other";
	}
	
	public void fill(){
		System.out.println("filling");
		try {
			InputStreamReader inReader = new InputStreamReader(new FileInputStream(new File(pais)), "UTF-8");
			String geojson = null;
			
	        try (BufferedReader br = new BufferedReader(inReader)){
	    		MfGeoFactory mfFactory = new MfGeoFactory() {
	    			
	    			public MfFeature createFeature(String id, MfGeometry geometry, JSONObject properties) {
	    					return new MyFeature(id, geometry, properties);}};
	        	
	            StringBuilder sb = new StringBuilder();
	            String sCurrentLine;

	            while ((sCurrentLine = br.readLine()) != null) {
	                sb.append(sCurrentLine);
	                sb.append(System.lineSeparator());
	            }

	            geojson = sb.toString();
	            MfGeoJSONReader reader = new MfGeoJSONReader(mfFactory);
	            MfGeo result = reader.decode(geojson);
	            
	            //Get List with Feature per province
	            ArrayList<MfFeature> coll = (ArrayList<MfFeature>) ((MfFeatureCollection) result).getCollection();
	            //This array will contain the name of each province
	            int ind = 0;
	            ArrayList<MultiPolygon> multiPolygonList = new  ArrayList<MultiPolygon>();
	            
	           
            	for (MfFeature mf : coll){
            		MultiPolygon multipolygon = (MultiPolygon) mf.getMfGeometry().getInternalGeometry();
            		String name = ((MyFeature) mf).getProps().getString("name");
            		multiPolygonList.add(multipolygon);
            		provincias[ind++]=name;
            	}
            	System.out.println(multiPolygonList.size());
            	Spain.mp = multiPolygonList;
	        	
				
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }

			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	public class MyFeature extends MfFeature {
		 private final String id;
	     private final MfGeometry geometry;
	     public final JSONObject properties;

		 public MyFeature(String id, MfGeometry geometry, JSONObject properties) {
	         this.id = id;
	         this.geometry = geometry;
	         this.properties = properties;
	     }

	     public String getFeatureId() {
	         return id;
	     }

	     public MfGeometry getMfGeometry() {
	         return geometry;
	     }
	     public JSONObject getProps(){
	    	 return properties;
	     }
	     @Override
	     public void toJSON(JSONWriter builder) throws JSONException {
	         throw new RuntimeException("Not implemented");
	     }
	}
	 

	
}
	 


