package AdvancedReplacement;

import java.awt.Component;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;

import Communication.Communication;

public class ComboBoxListener implements DocumentListener {

	GUIadvancedRepalcementPanel guIadvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	public ComboBoxListener() {

		// companyUpdate();

	}

	private void companyUpdate() {

		SwingUtilities.invokeLater(new Runnable() {

			@Override

			public void run() {
				showRecommendCompanyList();
			}

		});

	}

	private void siteUpdate() {

		SwingUtilities.invokeLater(new Runnable() {

			@Override

			public void run() {

				showRecommendSiteList();

			}

		});

	}

	private void itemUpdate() {

		SwingUtilities.invokeLater(new Runnable() {

			@Override

			public void run() {

				showRecommendItemList();

			}

		});

	}

	private void showRecommendCompanyList() {

		// 이벤트 발생지를 Company로 조정한다.
		JComboBox owner = guIadvancedRepalcementPanel.getTxtCompanyName();
		Component component = owner.getEditor().getEditorComponent();
		JTextComponent textComponent = (JTextComponent) component;

		String targetName = textComponent.getText();

		// 현재 입력창에 입력된 값(targetName)을 기준으로 추천단어를 검색해서 리스트에 등록.
		List<String> targetList = Communication.getInstance().getCompanyNameListFromServer(targetName);

		// targetName과 같은 회사의 이전 RMA정보를 출력.
		Communication.getInstance().showPreviousRMAList(targetName, "");

		Set<String> foundSet = new HashSet<String>();

		for (String temp : targetList) {

			foundSet.add(temp.toLowerCase());

		}

		Collections.sort(targetList);// sort alphabetically

		owner.setEditable(false);

		owner.removeAllItems();

		// if founds contains the search text, then only add
		// once.

		if (!foundSet.contains(targetName.toLowerCase())) {

			owner.addItem(targetName);
			guIadvancedRepalcementPanel.clearCompanyDetail();

		} else {

			// 검색어와 일치하는 회사가 있을경우 정보를 가져옴.

			JSONObject companyDetailObject = Communication.getInstance().getCompanyDetailJSON(targetName);

			String address = companyDetailObject.get("companyAddress").toString();
			String city = companyDetailObject.get("companyCity").toString();
			String zipCode = companyDetailObject.get("companyZipCode").toString();
			String phone = companyDetailObject.get("companyPhone").toString();
			String email = companyDetailObject.get("companyEmail").toString();

			guIadvancedRepalcementPanel.setCompanyDetail(address, city, zipCode, phone, email);

			guIadvancedRepalcementPanel.setBillToArea(targetName, address, city, zipCode, phone);

		}

		for (String temp : targetList) {

			owner.addItem(temp);

		}

		showRecommendSiteList();

		owner.setPopupVisible(true);
		owner.setEditable(true);
		owner.requestFocus();

	}

	private void showRecommendSiteList() {


		JComboBox owner = guIadvancedRepalcementPanel.getTxtSiteName();
		
		
		Component component = owner.getEditor().getEditorComponent();
		JTextComponent textComponent = (JTextComponent) component;

		String siteName = textComponent.getText();
		String companyName = guIadvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString();
		
//		String siteName = advancedReplacementOperation.getSiteName();
//		String companyName = advancedReplacementOperation.getCompanyName();

		List<String> founds = Communication.getInstance().getSiteNameListFromServer(siteName, companyName);

		Set<String> foundSet = new HashSet<String>();

		for (String s : founds) {

			foundSet.add(s.toLowerCase());

		}

		Collections.sort(founds);// sort alphabetically

		owner.setEditable(false);

		owner.removeAllItems();

		// if founds contains the search text, then only add
		// once.

		if (!foundSet.contains(siteName.toLowerCase())) {

			owner.addItem(siteName);

		} else {

			// 일치하는 경우

		}

		System.out.println("siteName : " + siteName);

		if (siteName.equals("")) {
			System.out.println("siteName 공백 ");
		}

		Communication.getInstance().showPreviousRMAList(companyName, siteName);

		for (String s : founds) {

			owner.addItem(s);

		}

		owner.setPopupVisible(true);
		owner.setEditable(true);
		owner.requestFocus();
	}

	private void showRecommendItemList() {

		JComboBox itemNameCombo = guIadvancedRepalcementPanel.getItemComboBox();
		Component itemNameComponent = itemNameCombo.getEditor().getEditorComponent();
		JTextComponent itemNameTextComponent = (JTextComponent) itemNameComponent;

		String partialOfItemName = itemNameTextComponent.getText();

		// Keyword Result list

		JSONObject resultJSON = Communication.getInstance().getItemNameFromServer(partialOfItemName);
		List<String> nameFounds = (List<String>) resultJSON.get("itemName");

		Set<String> foundSet = new HashSet<String>();

		for (String temp : nameFounds) {

			foundSet.add(temp.toLowerCase());

		}

		Collections.sort(nameFounds);// sort alphabetically

		DefaultComboBoxModel boxModel = new DefaultComboBoxModel();

		if (!foundSet.contains(partialOfItemName.toLowerCase())) {

			boxModel.addElement(partialOfItemName);

		} else {

			JTable itemTable = guIadvancedRepalcementPanel.get_RMAitemTable();
			int rowIndex = itemTable.getSelectedRow();

			if (resultJSON.get("itemDescription") != null && resultJSON.get("itemPrice") != null) {
				String itemDescription = resultJSON.get("itemDescription").toString();
				int itemPrice = (int) resultJSON.get("itemPrice");

				itemTable.setValueAt(itemDescription, rowIndex, 2);
				itemTable.setValueAt(itemPrice, rowIndex, 3);

			}

		}

		for (String temp : nameFounds) {

			boxModel.addElement(temp);

		}

		// owner.setEditable(false);

		itemNameCombo.setModel(boxModel);

		// owner.addPopupMenuListener(new ItemPopupListener(founds));

		// owner.setPopupVisible(true);
		itemNameCombo.setEditable(true);
		itemNameCombo.requestFocus();

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("insert Update");

		if (e.getDocument().getProperty("owner").equals("companyName")) {

			companyUpdate();

		} else if (e.getDocument().getProperty("owner").equals("siteName")) {

			siteUpdate();
		} else if (e.getDocument().getProperty("owner").equals("itemName")) {

			for (Element element : e.getDocument().getRootElements()) {
				System.out.println("엘리먼트 이름 : " + element.getName());
			}

			itemUpdate();
		}

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("remove Update");

		if (e.getDocument().getProperty("owner").equals("companyName")) {

			companyUpdate();

		} else if (e.getDocument().getProperty("owner").equals("siteName")) {

			siteUpdate();
		} else if (e.getDocument().getProperty("owner").equals("itemName")) {

			for (Element element : e.getDocument().getRootElements()) {
				System.out.println("엘리먼트 이름 : " + element.getName());
			}

			itemUpdate();
		}

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("changed Update");

	}

}
