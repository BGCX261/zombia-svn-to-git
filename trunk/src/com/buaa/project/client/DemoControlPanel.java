package com.buaa.project.client;

import java.util.Iterator;
import java.util.List;
import com.buaa.project.client.dd.BeanNewsDTO;
import com.buaa.project.client.layout.AccordionLayoutSample;
import com.buaa.project.client.panel.LoadDataPanel;
import com.buaa.project.client.panel.LoginPanel;
import com.buaa.project.client.panel.NewsWindow;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.core.Template;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.BoxComponent;
import com.gwtext.client.widgets.HTMLPanel;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GridView;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtextux.client.data.PagingMemoryProxy;

public class DemoControlPanel implements EntryPoint {

	private static TabPanel centerTabPanel = null;
	
	String news_title;

	PagingMemoryProxy proxy;

	public static TabPanel getCenterTabPanel() {
		return centerTabPanel;
	}

	public static void setCenterTabPanel(TabPanel centerTabPanel) {
		DemoControlPanel.centerTabPanel = centerTabPanel;
	}
	
	private Object[][] getObj(Object response) {
		List data = (List) response;

		Iterator it = data.iterator();
		int i = data.size();
		int j = 0;
		Object[][] b = new Object[i][];
		while (it.hasNext()) {
			final BeanNewsDTO bean = (BeanNewsDTO) it.next();
			Object[] a = bean.toObjectArray();
			b[j++] = a;

		}

		return b;
	}

	public void onModuleLoad() {
		Panel panel = new Panel();
		panel.setBorder(false);
		panel.setPaddings(0);
		panel.setLayout(new FitLayout());
		//panel.setTitle("GWT-EXT");
		panel.setIconCls("home");

		// ***************borderPanel****************************************************
		Panel borderPanel = new Panel();
		borderPanel.setLayout(new BorderLayout());

		// ***************northPanel*****************************************************
		BoxComponent northPanel = new BoxComponent();
		northPanel.setEl(new HTML("<p>north - generally for menus, toolbars"
				+ " and/or advertisements</p>").getElement());
		northPanel.setHeight(32);
		borderPanel.add(northPanel, new BorderLayoutData(RegionPosition.NORTH));

		// ***************westPanel*******************************************************
		Panel westPanel = new Panel();
		westPanel.setTitle("Cases");
		westPanel.setIconCls("cases");
		westPanel.setCollapsible(true);
		westPanel.setWidth(200);
		westPanel.setLayout(new FitLayout());
		westPanel.add(initCasePanel());

		BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);
		westData.setSplit(true);
		westData.setMinSize(175);
		westData.setMaxSize(400);
		westData.setMargins(new Margins(0, 0, 0, 0));
		borderPanel.add(westPanel, westData);
		// **************southPanel********************************************************
		Panel southPanel = new HTMLPanel(
				"<p>south - generally for informational stuff,"
						+ " also could be for status bar</p>");
		southPanel.setHeight(100);
		southPanel.setCollapsible(true);
		southPanel.setTitle("South");

		BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);
		southData.setMinSize(100);
		southData.setMaxSize(200);
		southData.setMargins(new Margins(0, 0, 0, 0));
		southData.setSplit(true);
		borderPanel.add(southPanel, southData);

		// **************eastPanel**********************************************************
		Panel eastPanel = new Panel();
		eastPanel.setTitle("East Side");
		eastPanel.setCollapsible(true);
		eastPanel.setWidth(225);
		eastPanel.setLayout(new FitLayout());

		BorderLayoutData eastData = new BorderLayoutData(RegionPosition.EAST);
		eastData.setSplit(true);
		eastData.setMinSize(175);
		eastData.setMaxSize(400);
		eastData.setMargins(new Margins(0, 0, 5, 0));

		borderPanel.add(eastPanel, eastData);

		// ***************centerPanel*******************************************************
		TabPanel centerPanel = new TabPanel();
		centerPanel.setDeferredRender(false);
		centerPanel.setActiveTab(0);
		//centerPanel.setLayout(new FitLayout());
		//centerPanel.add(initCenterTabPanel());

		// // Panel centerPanel_1 = new Panel();
		// // centerPanel_1.setTitle("test");
		// centerPanel_1.setClosable(true);
		// centerTabPanel.add(centerPanel_1);

		// LoginPanel loginPanel = new LoginPanel();
		// centerTabPanel.add(loginPanel);
//***********************************************************************************************
//		LoadDataPanel loadDataPanel = new LoadDataPanel();
//		centerPanel.add(loadDataPanel);
		
		final Panel centerPanel1 = new Panel();
		centerPanel1.setLayout(new FitLayout());
		centerPanel1.setTitle("新闻浏览");
		centerPanel1.setIconCls("grid-icon");

		centerPanel.add(centerPanel1);

		final GridPanel grid_news = new GridPanel();

		ToolTip tip = new ToolTip();
		tip.setHtml("点击浏览该新闻");
		tip.setWidth(120);
		//tip.setBodyStyle(bodyStyle);
		tip.setTrackMouse(true);
		tip.applyTo(grid_news);

		final Toolbar refreshTb = new Toolbar();
		refreshTb.addFill();
		final ToolbarButton refreshBt = new ToolbarButton("刷新");

		final String bodyStyle = "text-align:center;padding:5px 0;"
				+ "border:1px dotted #99bbe8;background:#dfe8f6;"
				+ "color:#15428b;cursor:default;margin:10px;"
				+ "font:bold 11px tahoma,arial,sans-serif;";

		final RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("N_TITLE"), new StringFieldDef("N_AUTHOR"),
				new StringFieldDef("N_TIME")

		});

		ColumnConfig N_TITLE = new ColumnConfig("新闻标题", "N_TITLE", 220, true);
		ColumnConfig N_AUTHOR = new ColumnConfig("发布单位", "N_AUTHOR", 80, true);
		ColumnConfig N_TIME = new ColumnConfig("发布时间", "N_TIME", 56, true);

		final ColumnModel columnModel = new ColumnModel(new ColumnConfig[] {
				N_TITLE, N_AUTHOR, N_TIME

		});

		final DatabaseServiceAsync loadService = DatabaseService.Util
				.getInstance();
		final AsyncCallback cb_load = new AsyncCallback() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				// Window.alert("Fail to getting data" + caught.toString());
			}

			public void onSuccess(Object response) {

				proxy = new PagingMemoryProxy(getObj(response));

				ArrayReader reader = new ArrayReader(recordDef);

				Store store = new Store(proxy, reader, true);

				grid_news.setStore(store);

				grid_news.setColumnModel(columnModel);

				// grid_news.setHeight(473);
				grid_news.setFrame(true);
				// grid_news.setAutoExpandColumn("N_TITLE");
				grid_news.stripeRows(true);
				// grid_news.setWidth(563);
				// grid_news.setLoadMask(true);

				grid_news.setVisible(true);

				final PagingToolbar pagingToolbar = new PagingToolbar(store);

				pagingToolbar.setPageSize(19);
				pagingToolbar.setDisplayInfo(true);
				pagingToolbar.setDisplayMsg("显示新闻条数 {0} - {1} of {2}");
				pagingToolbar.setEmptyMsg("没有数据显示");
				pagingToolbar.setVisible(true);

				NumberField pageSizeField = new NumberField();
				pageSizeField.setWidth(40);
				pageSizeField.setSelectOnFocus(true);
				pageSizeField.addListener(new FieldListenerAdapter() {
					public void onSpecialKey(Field field, EventObject e) {
						if (e.getKey() == EventObject.ENTER) {
							int pageSize = Integer.parseInt(field
									.getValueAsString());
							pagingToolbar.setPageSize(pageSize);
						}
					}
				});

				ToolTip toolTip = new ToolTip("请输每页要显示的新闻条数");
				toolTip.applyTo(pageSizeField);

				// pagingToolbar.setVisible(true);
				// pagingToolbar.addButton(refreshBt);

				pagingToolbar.addField(pageSizeField);
				grid_news.setBottomToolbar(pagingToolbar);
				grid_news.doLayout();
				GridView view = new GridView();
				view.setForceFit(true);
				grid_news.setView(view);

				store.load(0, 19);
				centerPanel1.add(grid_news);
				centerPanel1.doLayout();

				final ExtElement element = Ext.get("main-panel");
				element.unmask();

				if (store == null) {
					return;
				}

				List data = (List) response;

				Iterator it = data.iterator();

				while (it.hasNext()) {

					final BeanNewsDTO bean = (BeanNewsDTO) it.next();
					Object[] a = bean.toObjectArray();
					Object[][] b = new Object[][] { a };
					// store.add(recordDef.createRecord(bean.toObjectArray()));

				}

				store.commitChanges();

			}

		};
		loadService.getdata(cb_load);

		grid_news.addGridCellListener(new GridCellListenerAdapter() {

			public void onCellClick(GridPanel grid, int rowIndex, int title,
					EventObject e) {

				Record[] records = grid.getSelectionModel().getSelections();
				news_title = "";
				for (int i = 0; i < records.length; ++i) {
					Record record = records[i];

					news_title += record.getAsString("N_TITLE");

				}

				DatabaseServiceAsync getNewsContentService = DatabaseService.Util
						.getInstance();
				AsyncCallback cb_getNewsContent = new AsyncCallback() {

					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub

					}

					public void onSuccess(Object result) {
						// TODO Auto-generated method stub

						// System.out.println(result);

						Panel newsPanel = new HTMLPanel();
						newsPanel.setHeight(400);
						newsPanel.setAutoScroll(true);
						newsPanel.setHtml(result.toString());
						NewsWindow newWindow = new NewsWindow();
						newWindow.setTitle(news_title);

						Template template = new Template(result.toString());
						newWindow.add(newsPanel);
						newWindow.show();
						final ExtElement element = Ext.get("main-panel");

						element.mask();

						newWindow.addListener(new PanelListenerAdapter() {

							public void onClose(Panel panel) {

								element.unmask();

							}
						});
					}

				};

				getNewsContentService.getNewsContent(news_title,
						cb_getNewsContent);

			}
		});

//************************************************************************************************
		borderPanel.add(centerPanel,
				new BorderLayoutData(RegionPosition.CENTER));
		panel.add(borderPanel);

		Viewport viewport = new Viewport(panel);
	}

	private Panel initCasePanel() {
		Panel casePanel = new Panel();
		casePanel.setLayout(new AccordionLayout(true));

		Panel layoutCasePanel = createLayoutCasePanel();
		layoutCasePanel.setIconCls("catalog");
		casePanel.add(layoutCasePanel);

		Panel panelCasePanel = createPanelCasePanel();
		panelCasePanel.setIconCls("catalog");
		casePanel.add(panelCasePanel);

		Panel treeCasePanel = createTreeCasePanel();
		treeCasePanel.setIconCls("catalog");
		casePanel.add(treeCasePanel);

		Panel createDDCasePanel = createDDCasePanel();
		createDDCasePanel.setIconCls("catalog");
		casePanel.add(createDDCasePanel);
		return casePanel;
	}

	private Panel createLayoutCasePanel() {
		Panel layoutCasePanel = new Panel("Layout cases");
		TreePanel treePanel = initTreePanel();
		TreeNode root = treePanel.getRootNode();

		// add accordion layout sample
		AccordionLayoutSample accordionLayoutSample = new AccordionLayoutSample();
		root.appendChild(accordionLayoutSample);

		layoutCasePanel.add(treePanel);
		return layoutCasePanel;
	}

	private Panel createPanelCasePanel() {
		Panel panelCasePanel = new Panel("Panel cases");
		TreePanel treePanel = initTreePanel();
		TreeNode root = treePanel.getRootNode();

		panelCasePanel.add(treePanel);
		return panelCasePanel;
	}

	private Panel createTreeCasePanel() {
		Panel treeCasePanel = new Panel("Tree cases");
		TreePanel treePanel = initTreePanel();
		TreeNode root = treePanel.getRootNode();

		treeCasePanel.add(treePanel);
		return treeCasePanel;
	}

	private Panel createDDCasePanel() {
		Panel ddCasePanel = new Panel("Drag drop cases");
		TreePanel treePanel = initTreePanel();
		TreeNode root = treePanel.getRootNode();

		ddCasePanel.add(treePanel);
		return ddCasePanel;
	}

	private TreePanel initTreePanel() {
		TreePanel treePanel = new TreePanel();
		treePanel.setAutoScroll(true);
		treePanel.setRootVisible(false);
		treePanel.setBorder(false);
		TreeNode root = new TreeNode();
		treePanel.setRootNode(root);
		return treePanel;
	}

	private Panel initCenterTabPanel() {
		if (centerTabPanel == null)
			centerTabPanel = new TabPanel();
		return centerTabPanel;
	}

}
