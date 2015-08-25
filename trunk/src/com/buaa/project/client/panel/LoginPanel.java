package com.buaa.project.client.panel;


import java.util.Map;

import com.buaa.project.client.DatabaseService;
import com.buaa.project.client.DatabaseServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.TextAlign;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.WaitConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;


public class LoginPanel extends FormPanel implements AsyncCallback {
	
	

	public LoginPanel(){
		
		
		
	 	this.setWidth(200);
		this.setLabelWidth(75);
		this.setIconCls("loginPanel-icon");
		this.setTitle("Login");
		
		final ComboBox cb = new ComboBox();
		
		cb.setForceSelection(true);
		cb.setMinChars(1);
		cb.setFieldLabel("用户类型");
		//cb.setStore(store);
		cb.setDisplayField("state");
		cb.setMode(ComboBox.LOCAL);
		cb.setTriggerAction(ComboBox.ALL);
		cb.setEmptyText("选择类型");
		cb.setLoadingText("Searching...");
		cb.setTypeAhead(true);
		cb.setSelectOnFocus(true);
		cb.setWidth(100);
		cb.setHideTrigger(false);
		
		this.add(cb);

		final TextField txtLoginName = new TextField("用户名", "txtLoginname", 100);
		txtLoginName.setAllowBlank(false);
		this.add(txtLoginName);

		final TextField txtLoginPsw = new TextField("密码", "txtLoginpsw", 100);
		txtLoginPsw.setPassword(true);
		this.add(txtLoginPsw);
		
		final AsyncCallback callback = null;
       
		System.out.println("1*******************");
	
		final Button btLogin = new Button("Login");
		btLogin.setIconCls("btLogin-icon");
		btLogin.addListener(new ButtonListenerAdapter() {


			public void onClick(final Button button, EventObject e) {

				String username = txtLoginName.getText();
				String password = txtLoginPsw.getText();
				DatabaseServiceAsync loginService = DatabaseService.Util
						.getInstance();
				
			

				// MessageBox.wait("正在登陆");

				MessageBox.show(new MessageBoxConfig() {
					{
						setMsg("正在登陆......");
						setProgressText("登陆中...");
						setWidth(300);
						setWait(true);
						setWaitConfig(new WaitConfig() {
							{
								setInterval(200);
							}
						});
						setAnimEl(button.getId());

						Timer timer = new Timer() {
				
							public void run() {
								MessageBox.hide();	
							}
						};
						timer.schedule(100000);
					}
				});

				AsyncCallback cb_login = new AsyncCallback() {

					public void onFailure(Throwable arg0) {

					}

					public void onSuccess(Object result) {
					
						System.out.println(result.toString()+"********");
						Boolean ok = (Boolean) result;

						if (ok.booleanValue()) {
							
							MessageBox.alert("登陆成功!");
							final ExtElement  element1 = Ext.get("west");
							element1.unmask();
							
					DatabaseServiceAsync typeService = DatabaseService.Util.getInstance();
					
					AsyncCallback cb_type = new  AsyncCallback(){

						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub
							
						}

						public void onSuccess(Object type) {
							// TODO Auto-generated method stub
							
							String id = (String)type;
							
							
							if(id.equals("1")){//1代表中心管理员
								
								Ext.get("treePanel1").mask("您没有权限！");
								Ext.get("treePanel2").mask("您没有权限！");
								Ext.get("treePanel3").mask("您没有权限！");
								Ext.get("treePanel4").mask("您没有权限！");
								Ext.get("treePanel5").mask("您没有权限！");
								Ext.get("treePanel6").mask("您没有权限！");
							
								
								
							}
							if(id.equals("2")){//2代表法人单位 
																
								Ext.get("treePanel1").mask("您没有权限！");
								Ext.get("treeNode2_1").mask();
							}
						}
						
					};
					typeService.userType(txtLoginName.getText(), cb_type);

							
							final ExtElement element4 = Ext.get("logout");
							element4.setVisible(true);
	
							
						} else {
							MessageBox.alert("密码输入有误，请重新输入!");
							txtLoginPsw.setValue("");
						}

					}

				};
				
			
				loginService.login(username, password, cb_login);
				
			}
			
			

		});


		
		Button cancel = new Button("取消");
     /*   cancel.addListener(new ButtonListenerAdapter(){
        	public void onClick(Button button,EventObject e){
        		if(txtLoginname.getText().length()!=0){
        		txtLoginname.clearInvalid();
        		}
        	}
        });*/
		this.addButton(btLogin);
		this.addButton(cancel);

		

	}
	
	public void onSuccess(Object arg0) {
		MessageBox.hide();
		System.out.println("login suess");
		MessageBox.alert("成功");

	}


	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		MessageBox.hide();
		System.out.println("loginfailer");
		MessageBox.alert("失败");
	}

}
