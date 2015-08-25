package com.buaa.project.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

@RemoteServiceRelativePath("databaseservice")
public interface DatabaseService extends RemoteService {

	public static class Util {

		public static DatabaseServiceAsync getInstance() {

			return GWT.create(DatabaseService.class);
		}
	}
	
    public boolean login(String username,String password) throws Exception;
    
    public boolean validate(String username)throws Exception;
    
    public String userType(String name)throws Exception;
    
    public List getdata()throws Exception;
    
    public String getNewsContent(String N_TITLE)throws Exception;

}
