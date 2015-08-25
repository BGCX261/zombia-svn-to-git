package com.buaa.project.client.layout;

import com.buaa.project.client.BaseCaseTreeNode;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;

public class AccordionLayoutSample extends BaseCaseTreeNode {

	private Panel panel;
	
	
	@Override
	public Panel initComponent() {
		 if (panel == null) {

	            panel = new Panel("Accordion layout");
	            panel.setLayout(new HorizontalLayout(15));

	            Panel accordionPanel = createAccordionPanel();
	            accordionPanel.setTitle("Accordion Panel");
	            accordionPanel.setHeight(400);
	            accordionPanel.setWidth(200);

	            Button button = new Button("Show Accordion in Window", new ButtonListenerAdapter() {
	                public void onClick(Button button, EventObject e) {
	                    Panel accordionPanel = createAccordionPanel();
	                    Window window = new Window();
	                    window.setTitle("Accordion Window");
	                    window.setWidth(200);
	                    window.setHeight(400);
	                    window.add(accordionPanel);
	                    window.show(button.getId());
	                }
	            });

	            panel.add(accordionPanel);
	            panel.add(button);

	        }
	    return panel;
	}

	 private Panel createAccordionPanel() {
	        Panel accordionPanel = new Panel();
	        accordionPanel.setLayout(new AccordionLayout(true));

	        Panel panelOne = new Panel("Panel 1", "<p>Panel1 content!</p>");
	        panelOne.setIconCls("settings-icon");
	        accordionPanel.add(panelOne);

	        Panel panelTwo = new Panel("Panel 2", "<p>Panel2 content!</p>");
	        panelTwo.setIconCls("folder-icon");
	        accordionPanel.add(panelTwo);

	        Panel panelThree = new Panel("Panel 3", "<p>Panel3 content!</p>");
	        panelThree.setIconCls("user-add-icon");
	        accordionPanel.add(panelThree);

	        return accordionPanel;
	    }
	
	@Override
	public void setTreeNodeProerties() {
		
		setText("Accordion Layout");

	}

}
