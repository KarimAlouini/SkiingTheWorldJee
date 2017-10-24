package tn.codeinc.services;

import java.util.List;

import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;
import tn.codeinc.persistance.KeyWord;

public interface KeyWordManagementLocal {

	public void create(KeyWord keyWord);
	public List<KeyWord> getAll();
	public KeyWord get(int id);
	public void remove(KeyWord keyWord);
	public void update(KeyWord keyWord);

}
