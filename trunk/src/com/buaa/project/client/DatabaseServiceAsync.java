package com.buaa.project.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatabaseServiceAsync {

	public void login(String username,String password, AsyncCallback<Boolean> callback);
    
    public void validate(String username, AsyncCallback<Boolean> callback);
    
    public void userType(String name, AsyncCallback<String> callback);
    
    public void getdata(AsyncCallback<List> callback);
    
    public void getNewsContent(String N_TITLE, AsyncCallback<String> callback);

}
