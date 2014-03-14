package biz;

import java.util.List;

import dao.AdminDAO;
import entity.Admin;

public class AdminBIZ {
	private AdminDAO adminDAO = null;

	public AdminBIZ(AdminDAO adminDAO) {
		super();
		this.adminDAO = adminDAO;
	}
	
	public Admin adminLogin(String username, String password) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<Object> resutAdminList = this.adminDAO.findByUsername(username);
		Admin resultAdmin = null;
		if(resutAdminList.size() != 0){
			resultAdmin = (Admin) resutAdminList.get(0);
		}
		
		if (resultAdmin == null){
			throw new Exception("wrong username");
		}else{
			if (resultAdmin.getPassword().equals(password)){
				return resultAdmin;
			}else{
				throw new Exception("wrong password");
			}
		}
	}
	
}
