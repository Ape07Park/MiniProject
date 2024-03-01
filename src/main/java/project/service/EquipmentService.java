package project.service;

import java.util.List;

import project.entity.Equipment;

public interface EquipmentService {
	public static final int COUNT_PER_PAGE = 20;
	
	Equipment getEquipmentByInum(int inum);
	
	List<Equipment> getEquipmentList(int page);
	
	int getEquipmentCount();
	
	
}
