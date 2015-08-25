package com.buaa.project.client;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Node;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.event.TreeNodeListenerAdapter;

public abstract class BaseCaseTreeNode extends TreeNode {

	public BaseCaseTreeNode(){
		setIconCls("node");
		setTreeNodeProerties();
		initComponent();
		initTreeNodeListener();
	}
	
	public abstract Panel initComponent();
	
	public abstract void setTreeNodeProerties();
	
	public void initTreeNodeListener(){
		this.addListener(new TreeNodeListenerAdapter(){

			@Override
			public void onClick(Node node, EventObject e) {
				TabPanel centerTabPanel = DemoControlPanel.getCenterTabPanel();
				Panel panel = initComponent();
				centerTabPanel.add(panel);
				centerTabPanel.setActiveTab(panel.getId());
				
			}
			
		});
	}
	
}
