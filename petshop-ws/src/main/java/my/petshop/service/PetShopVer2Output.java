package my.petshop.service;

import java.util.ArrayList;

import my.petshop.models.PetEntity;

public class PetShopVer2Output {

	private ArrayList<PetEntity> list;

    public PetShopVer2Output() {
    	this.list = new ArrayList<PetEntity>();
    }


	public ArrayList<PetEntity> getList() {
		return list;
	}

	public void add(PetEntity pet) {
		this.list.add(pet);
	}
	
	public void add(ArrayList<PetEntity> petList) {
		this.list.addAll(petList);
	}
}
