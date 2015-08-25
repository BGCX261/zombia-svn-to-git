package com.buaa.project.client.dd;

import com.google.gwt.user.client.rpc.IsSerializable;


public class BeanDTO implements IsSerializable{

	
	public String N_TITLE;
    public String  N_AUTHOR;
    public String  N_TIME;
    public String  N_ID;
    
   
    
    public String getN_TITLE() {
        return this.N_TITLE;
      }
   
    public String getN_ID() {
        return this.N_ID;
      }
   
    public String getN_TIME() {
        return this.N_TIME;
      }
   
    public String getN_AUTHOR() {
        return this.N_AUTHOR;
      }
   

    
    public void setN_TITLE(String N_TITLE) {
        this.N_TITLE=N_TITLE;
      }
   
 
    public void setN_AUTHOR(String N_AUTHOR) {
        this.N_AUTHOR=N_AUTHOR;
      }
   
    public void setN_TIME(String N_TIME) {
        this.N_TIME=N_TIME;
      }
    public void setN_ID(String N_ID) {
        this.N_ID=N_ID;
      }
    public Object[] toObjectArray() {
            return new Object[]{
            		N_TITLE, N_AUTHOR, N_TIME
            		};
   } 
    public Object[] toObject(){
    	return new Object[]{
    			
    			N_TITLE
    	};
    }
    
    public Object[][] toObjectArray2() {
        return new Object[][]{
        		new Object[] {N_TITLE, N_AUTHOR, N_TIME}
        		};
} 
}

