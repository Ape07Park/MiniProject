package project.service;

import java.util.List;

import project.dao.EquipmentDao;
import project.entity.Equipment;


public class EquipmentServiceImpl implements EquipmentService {
	private EquipmentDao eDao = new EquipmentDao();
	
	@Override
	public Equipment getEquipmentByInum(int inum) {
		Equipment equip = eDao.getEquipmentByInum(inum);
		return equip;
	}

	@Override
	public List<Equipment> getEquipmentList(int page) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		List<Equipment> list = eDao.getEquipmentList(COUNT_PER_PAGE, offset);
	      return list;
	}

	@Override
	public int getEquipmentCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
