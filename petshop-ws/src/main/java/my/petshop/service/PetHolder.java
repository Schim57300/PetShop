package my.petshop.service;

import java.util.ArrayList;

import my.petshop.models.PetEntity;

public class PetHolder {

	private ArrayList<PetEntity> list;

    public PetHolder() {
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
