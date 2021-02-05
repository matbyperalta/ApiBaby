package com.apibaby.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/babyapi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestServicio {

	@GET
	@Path("/images")
	public String getImages(@QueryParam("pagina") String pagina) {
		System.out.println("imagenws ... "+pagina);	
		List<UrlImage> urls = new ArrayList<>();
		try {
			
			Random rand = new Random(); 
			int rand_int1 = rand.nextInt(10); 
			int pag = Integer.parseInt(pagina) * rand_int1;
			
			JSONObject unsplash = readJsonFromUrl(
					"https://unsplash.com/napi/search/photos?query=baby&xp=&per_page=25&page=" + String.valueOf(pag) + "");
			JSONArray resultsUnsplash = unsplash.getJSONArray("results");
			for (int i = 0; i < resultsUnsplash.length(); i++) {
				String updateAt = "";
				String username = "";
				String name = "";
				String firstName = "";
				String lastName = "";
				String twitterUsername = "";
				String instagramUsername = "";
				String portafolio = "";
				String biografia = "";
				
				try {
					JSONObject unsplash1 = resultsUnsplash.getJSONObject(i);
					String descripcion = "";
					if(!unsplash1.isNull("alt_description")) 
						descripcion = (String)unsplash1.get("alt_description");
					
					if(!unsplash1.isNull("description")) 
						descripcion = (String)unsplash1.get("description");
					
					if(!unsplash1.isNull("updated_at")) 
						updateAt = (String)unsplash1.get("updated_at");
					
					JSONObject unsplash2 = (JSONObject) unsplash1.get("urls");
					String unsplash3 = (String) unsplash2.get("small");
					
					
					JSONObject user = (JSONObject) unsplash1.get("user");
					
					if(!user.isNull("username"))
						username = (String)user.get("username"); 
					
					if(!user.isNull("name"))
						name = (String)user.get("name");
					
					if(!user.isNull("first_name"))
						firstName = (String)user.get("first_name");
					
					if(!user.isNull("last_name"))
						lastName = (String)user.get("last_name");
					
					if(!user.isNull("twitter_username"))
						twitterUsername = (String)user.get("twitter_username");
					else
						twitterUsername = "@";
					
					if(!user.isNull("instagram_username"))
						instagramUsername = (String)user.get("instagram_username");
					else
						instagramUsername = "@";
						
					if(!user.isNull("portfolio"))
						portafolio = (String)user.get("portfolio");
					
					if(!user.isNull("bio"))
						biografia = (String)user.get("bio");
					
					urls.add(new UrlImage(unsplash3, descripcion, updateAt, name, firstName, lastName, username, twitterUsername, instagramUsername, portafolio, biografia));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"error:\":\"Error consultando imagenes\"}";
		}
		return "{\"imagenes\":"+urls.toString()+"}";
	}

	@GET
	@Path("/gift")
	public String getGifs(@QueryParam("pagina") String pagina) {
		System.out.println("gift ... "+pagina);	
		List<UrlGift> urls = new ArrayList<>();
		try {
			JSONObject json = readJsonFromUrl("https://api.giphy.com/v1/gifs/search?offset=" + pagina
					+ "&type=gifs&sort=&q=baby&api_key=Gc7131jiJuvI7IdN0HZ1D7nh0ow5BU6g&pingback_id=5ec1ffa20ae6f072");
			JSONArray data = json.getJSONArray("data");
			System.out.println(data.length());
			for (int i = 0; i < data.length(); i++) {
				JSONObject object1 = data.getJSONObject(i);
				JSONObject object2 = (JSONObject) object1.get("images");
				
				String updateAs = "";
				if(!object1.isNull("update_datetime"))
					updateAs = (String)object1.get("update_datetime");	
				
				String titulo = (String)object1.get("title");
				JSONObject object3 = (JSONObject) object2.get("original");
				String object4 = (String) object3.get("url");
				
				urls.add(new UrlGift(object4,titulo,updateAs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"error:\":\"Error consultando imagenes\"}";
		}
		return "{\"imagenes\":"+urls.toString()+"}";
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

}
