package com.apibaby.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement
public class UrlImage {
	@XmlElement(name = "url")
	String url;

	@XmlElement(name = "updateAt")
	String updateAt;

	@XmlElement(name = "descripcion")
	String descripcion;

	@XmlElement(name = "name")
	String name;

	@XmlElement(name = "firstName")
	String firstName;

	@XmlElement(name = "lastName")
	String lastName;

	@XmlElement(name = "username")
	String username;

	@XmlElement(name = "twitterUsername")
	String twitterUsername;

	@XmlElement(name = "instagramUsername")
	String instagramUsername;

	@XmlElement(name = "portafolioUrl")
	String portafolioUrl;

	@XmlElement(name = "bio")
	String bio;

	public UrlImage() {
	}
 
	public UrlImage(String url, String descripcion, String updateAt, String name, String firstName, String lastName, String username,
			String twitterUsername, String instagramUsername, String  portafolioUrl, String bio) {
		this.url = url;
		this.descripcion = descripcion;
		this.updateAt = updateAt;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username =username;
		this.twitterUsername = twitterUsername;
		this.instagramUsername = instagramUsername;
		this.portafolioUrl = portafolioUrl;
		this.bio = bio;
	}

	@Override
	public String toString() {
		try {
			return new JSONObject().put("url", url).put("descripcion", descripcion).put("upadteAt", updateAt)
					.put("name", name).put("firstName", firstName).put("lastName", lastName).put("username", username)
					.put("twitterUsername", twitterUsername).put("instagramUsername", instagramUsername)
					.put("portafolioUrl", portafolioUrl).put("bio", bio).toString();
		} catch (JSONException e) {
			return null;
		}
	}
}
