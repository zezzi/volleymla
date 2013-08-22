package com.example.volleytest;

import com.google.android.gms.maps.model.LatLng;

public class Lugares {	
	private int id;
	private LatLng posicion;
	private String title;
	private String desc;
	private String code;
	private String address;
	
	public Lugares(LatLng posicion,String title,String desc,String code,String address){
		this.posicion=posicion;
		this.id=0;
		this.title=title;
		this.desc=desc;
		this.code=code;
		this.address=address;
	}
	public Lugares(){
		this.posicion=new LatLng(15.00,90.00);;
		this.id=0;
		this.title="";
		this.desc="";
		this.code="";
		this.address="";
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public void setLatLng(double lat,double lon){
		posicion=new LatLng(lat,lon);
	}
	public LatLng getLatLng(){
		return posicion;
	}
	
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setTitle(String titulo){
		this.title=titulo;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	
	public String toString() {
		String res = "id:" + getId() + "\nNombre:" + getDesc() + "\nthumbUrl:" + "\n address:" + address;
		return res;	
	}
}
