package com.apibaby.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement
public class UrlGift {
	@XmlElement(name = "url")
	String url;

	@XmlElement(name = "updateAt")
	String updateAt;

	@XmlElement(name = "descripcion")
	String descripcion;

	public UrlGift() {
	}
 
	public UrlGift(String url, String descripcion, String updateAt) {
		this.url = url;
		this.descripcion = descripcion;
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		try {
			return new JSONObject().put("url", url).put("descripcion", descripcion).put("upadteAt", updateAt).toString();
		} catch (JSONException e) {
			return null;
		}
	}
}
