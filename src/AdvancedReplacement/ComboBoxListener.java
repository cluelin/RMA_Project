package AdvancedReplacement;

import java.awt.Component;
import java.util.ArrayList;
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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Default.Client;

public class ComboBoxListener implements DocumentListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel
			.getGUIadvancedReplecementPanel();

	public ComboBoxListener() {

		companyUpdate();

		// guiAdvancedRepalcementPanel.getItemComboBox().requestFocus();

		// itemUpdate();

	}

	// CompanyName으로 검색해서 해당 company에서 신청한 RMA를 검색해서 뿌려줌.
	private void showPreviousRMAList(String targetName) {

		try {
			Client.connectServer();

			guiAdvancedRepalcementPanel.clearHistoryPanel();

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestSearchRelatedRMA");
			obj.put("companyName", targetName);

			Client.printStream.println(obj.toJSONString());

			while (true) {

				try {
					String input = Client.bufferedReader.readLine();

					if (input == null) {
						break;
					}

					JSONParser jsonParser = new JSONParser();

					JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

					String rmaNumber = jsonObject.get("RMAnumber").toString();
					String rmaDate = jsonObject.get("RMAdate").toString();
					String rmaContents = jsonObject.get("RMAcontents").toString();

					// history panel에 결과 출력.
					guiAdvancedRepalcementPanel.setRelatedRMAInformation(rmaNumber, rmaDate, rmaContents);

					System.out.println("rmaNumber : " + rmaNumber + " rmaContents : " + rmaContents);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Client.closeConnection();
		}

	}

	// 서버로부터 siteName에 대한 정보를 검색해 가지고 옴.
	private List<String> getSiteNameFromServer(String keyword, String companyName) {

		List<String> resultArryList = null;

		try {
			Client.connectServer();

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestSiteName");

			obj.put("companyName", companyName);
			obj.put("siteName", keyword);

			Client.printStream.println(obj.toJSONString());

			JSONParser jsonParser = new JSONParser();

			String input;

			resultArryList = new ArrayList<>();

			while (true) {

				try {

					input = Client.bufferedReader.readLine();

					if (input == null) {
						break;
					}

					JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

					resultArryList.add(jsonObject.get("siteName").toString());

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Client.closeConnection();
		}

		return resultArryList;

	}

	// 서버로 부터 keyword에 해당하는 Item 검색
	private List<String> getItemNameFromServer(String keyword) {

		ArrayList resultArryList = new ArrayList<String>();

		try {
			Client.connectServer();

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestItemName");
			obj.put("itemName", keyword);

			Client.printStream.println(obj.toJSONString());

			JSONParser jsonParser = new JSONParser();

			String input;

			while (true) {

				try {

					input = Client.bufferedReader.readLine();

					if (input == null) {
						break;
					}

					JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

					// Integer itemCode =
					// Integer.parseInt(jsonObject.get("itemCode").toString());
					String itemName = jsonObject.get("itemName").toString();
					String itemDescription = jsonObject.get("itemDescription").toString();
					Integer itemPrice = Integer.parseInt(jsonObject.get("itemPrice").toString());

					resultArryList.add(itemName);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Client.closeConnection();
		}

		return resultArryList;

	}

	private List<String> getCompanyNameFromServer(String keyword) {

		List<String> resultArryList = null;
		try {
			Client.connectServer();

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestCompanyName");

			obj.put("companyName", keyword);

			Client.printStream.println(obj.toJSONString());

			JSONParser jsonParser = new JSONParser();

			String input;

			resultArryList = new ArrayList<>();

			while (true) {

				try {

					input = Client.bufferedReader.readLine();

					if (input == null) {
						break;
					}

					JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

					resultArryList.add(jsonObject.get("companyName").toString());

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Client.closeConnection();
		}

		return resultArryList;
	}

	private JSONObject getCompanyDetail(String companyName) {

		JSONObject sendJSONobj = new JSONObject();

		sendJSONobj.put("Action", "requestCompanyDetail");

		sendJSONobj.put("companyName", companyName);

		Client.printStream.println(sendJSONobj.toJSONString());

		JSONParser jsonParser = new JSONParser();

		String input;

		try {

			input = Client.bufferedReader.readLine();

			JSONObject companyDetailObject = (JSONObject) jsonParser.parse(input);

			return companyDetailObject;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
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
		JComboBox owner = guiAdvancedRepalcementPanel.getTxtCompanyName();
		Component component = owner.getEditor().getEditorComponent();
		JTextComponent textComponent = (JTextComponent) component;

		String targetName = textComponent.getText();

		// 현재 입력창에 입력된 값(targetName)을 기준으로 추천단어를 검색해서 리스트에 등록.
		List<String> targetList = getCompanyNameFromServer(targetName);

		// targetName과 같은 회사의 이전 RMA정보를 출력.
		showPreviousRMAList(targetName);

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
			// guiAdvancedRepalcementPanel.clearCompanyDetail();

		} else {

			// 검색어와 일치하는 회사가 있을경우 정보를 가져옴.

			try {
				Client.connectServer();

				JSONObject companyDetailObject = getCompanyDetail(targetName);

				String address = companyDetailObject.get("companyAddress").toString();
				String city = companyDetailObject.get("companyCity").toString();
				String zipCode = companyDetailObject.get("companyZipCode").toString();
				String phone = companyDetailObject.get("companyPhone").toString();
				String email = companyDetailObject.get("companyEmail").toString();

				guiAdvancedRepalcementPanel.setCompanyDetail(address, city, zipCode, phone, email);

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				Client.closeConnection();
			}

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

		JComboBox owner = guiAdvancedRepalcementPanel.getTxtSiteName();

		Component component = owner.getEditor().getEditorComponent();
		JTextComponent textComponent = (JTextComponent) component;

		String siteName = textComponent.getText();

		List<String> founds = getSiteNameFromServer(siteName,
				guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString());

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

		}

		for (String s : founds) {

			owner.addItem(s);

		}

		owner.setPopupVisible(true);
		owner.setEditable(true);
		owner.requestFocus();
	}

	private void showRecommendItemList() {

		JComboBox owner = guiAdvancedRepalcementPanel.getItemComboBox();
		Component component = owner.getEditor().getEditorComponent();
		JTextComponent textComponent = (JTextComponent) component;

		String partialOfItemName = textComponent.getText();
		
		// Keyword Result list
		List<String> founds = getItemNameFromServer(partialOfItemName);
		
		Set<String> foundSet = new HashSet<String>();

		for (String temp : founds) {

			foundSet.add(temp.toLowerCase());

		}

		Collections.sort(founds);// sort alphabetically

		DefaultComboBoxModel boxModel = new DefaultComboBoxModel();

		if (!foundSet.contains(partialOfItemName.toLowerCase())) {

			boxModel.addElement(partialOfItemName);

		} else {

		}

		for (String temp : founds) {

			boxModel.addElement(temp);

		}
		
		
//		owner.setEditable(false);

		owner.setModel(boxModel);

		

//		owner.addPopupMenuListener(new ItemPopupListener(founds));

		owner.setPopupVisible(true);
		owner.setEditable(true);
		owner.requestFocus();

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

			itemUpdate();
		}

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("changed Update");

	}

}
