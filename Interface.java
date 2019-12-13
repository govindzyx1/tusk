package com.Interfaces;

import com.Bean.AdminBean;
import com.Bean.UploadFileBean;
import com.Bean.UserBean;

public interface Interface {
	public int adminRegister(AdminBean bean);
	public int adminLogin(AdminBean bean);
	public int adminUpload(UploadFileBean ufb);
	public int userRegister(UserBean bean);
	public int userLogin(UserBean bean);
	public int userUpload(UploadFileBean ufb);
	public boolean block(String ip);

}
