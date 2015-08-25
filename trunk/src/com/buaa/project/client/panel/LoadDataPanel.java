package com.buaa.project.client.panel;

import java.util.Iterator;
import java.util.List;
import com.buaa.project.client.DatabaseService;
import com.buaa.project.client.DatabaseServiceAsync;
import com.buaa.project.client.dd.BeanNewsDTO;
import com.buaa.project.client.dd.BeanDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.core.Template;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.HTMLPanel;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.WaitConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GridView;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtextux.client.data.PagingMemoryProxy;

public class LoadDataPanel extends Panel {

	String news_title;

	PagingMemoryProxy proxy;
	
	final String bodyStyle = "text-align:center;padding:5px 0;"
		+ "border:1px dotted #99bbe8;background:#dfe8f6;"
		+ "color:#15428b;cursor:default;margin:10px;"
		+ "font:bold 11px tahoma,arial,sans-serif;";

	private Object[][] getObj(Object response) {
		List data = (List) response;

		Iterator it = data.iterator();
		int i = data.size();
		int j = 0;
		Object[][] b = new Object[i][];
		while (it.hasNext()) {
			final BeanDTO bean = (BeanDTO) it.next();
			Object[] a = bean.toObjectArray();
			b[j++] = a;

		}

		return b;
	}

	public LoadDataPanel() {

			//this.setWidth(558);
		this.setIconCls("grid-icon");
		this.setBorder(false);

		

		final Panel centerPanel1 = new Panel();
		centerPanel1.setLayout(new FitLayout());
		centerPanel1.setTitle("新闻浏览");
		centerPanel1.setIconCls("grid-icon");

		final GridPanel grid_news = new GridPanel();

		ToolTip tip = new ToolTip();
		tip.setHtml("点击浏览该新闻");
		tip.setWidth(120);
		tip.setBodyStyle(bodyStyle);
		tip.setTrackMouse(true);
		tip.applyTo(grid_news);

		final Toolbar refreshTb = new Toolbar();
		refreshTb.addFill();
		final ToolbarButton refreshBt = new ToolbarButton("刷新");

	

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
		
		// refreshTb.addButton(refreshBt);
		this.add(centerPanel1);
	}

}
