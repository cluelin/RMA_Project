package AdvancedReplacement;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Default.Client;

public class ComboBoxListener implements DocumentListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel
			.getGUIadvancedReplecementPanel();

	// DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>();

	List<String> founds = null;

	JComboBox owner;
	JTextComponent tc;
	List<String> resultArryList = null;

	public ComboBoxListener() {

	}

	// CompanyName으로 검색해서 해당 company에서 신청한 RMA를 검색해서 뿌려줌.
	private void readRelatedRMAfromDataBase() {

		System.out.println("readRelatedRMAfromDataBase");

		guiAdvancedRepalcementPanel.clearHistoryPanel();

		JSONObject obj = new JSONObject();

		obj.put("Action", "requestSearchRelatedRMA");
		obj.put("companyName", tc.getText());

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
	}

	// 서버로부터 siteName에 대한 정보를 검색해 가지고 옴.
	private List<String> getSiteNameFromServer(String keyword, String companyName) {

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

		return resultArryList;

	}

	private List<String> getCompanyNameFromServer(String keyword) {

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

		return resultArryList;
	}

	private JSONObject getCompanyDetail(String companyName) {

		JSONObject obj = new JSONObject();

		obj.put("Action", "requestCompanyDetail");

		obj.put("companyName", companyName);

		Client.printStream.println(obj.toJSONString());

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

		try {
			Client.connectServer();

			founds = getCompanyNameFromServer(tc.getText());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Client.closeConnection();
		}

		try {
			Client.connectServer();
			readRelatedRMAfromDataBase();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Client.closeConnection();
		}

		// perform separately, as listener conflicts between the
		// editing component

		// and JComboBox will result in an IllegalStateException due
		// to editing

		// the component when it is locked.

		SwingUtilities.invokeLater(new Runnable() {

			@Override

			public void run() {

				Set<String> foundSet = new HashSet<String>();

				for (String s : founds) {

					foundSet.add(s.toLowerCase());

				}

				// Collections.sort(founds);// sort alphabetically

				owner.setEditable(false);

				owner.removeAllItems();

				// if founds contains the search text, then only add
				// once.

				if (!foundSet.contains(tc.getText().toLowerCase())) {

					owner.addItem(tc.getText());
					// guiAdvancedRepalcementPanel.clearCompanyDetail();

				} else {

					try {
						Client.connectServer();

						System.out.println("tc.getText() : " + tc.getText());

						JSONObject companyDetailObject = getCompanyDetail(tc.getText());

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

				for (String s : founds) {

					owner.addItem(s);

				}
				owner.setPopupVisible(true);
				owner.setEditable(true);
				owner.requestFocus();

			}

		});

		// When the text component changes, focus is gained

		// and the menu disappears. To account for this, whenever the focus

		// is gained by the JTextComponent and it has searchable values, we
		// show the popup.

		tc.addFocusListener(new FocusListener() {

			@Override

			public void focusGained(FocusEvent arg0) {

				// companyUpdate();

				if (tc.getText().length() > 0) {

					owner.setPopupVisible(true);

				}

			}

			@Override

			public void focusLost(FocusEvent arg0) {

			}

		});

	}

	private void siteUpdate() {

		try {
			Client.connectServer();

			founds = getSiteNameFromServer(tc.getText(),
					guiAdvancedRepalcementPanel.getTxtCompanyName().getEditor().getItem().toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Client.closeConnection();
		}

		// perform separately, as listener conflicts between the
		// editing component

		// and JComboBox will result in an IllegalStateException due
		// to editing

		// the component when it is locked.

		SwingUtilities.invokeLater(new Runnable() {

			@Override

			public void run() {

				Set<String> foundSet = new HashSet<String>();

				for (String s : founds) {

					foundSet.add(s.toLowerCase());

				}

				
				Collections.sort(founds);// sort alphabetically

				owner.setEditable(false);

				owner.removeAllItems();

				// if founds contains the search text, then only add
				// once.
				
				

				if (!foundSet.contains(tc.getText().toLowerCase())) {

					owner.addItem(tc.getText());

				} else {

				}

				for (String s : founds) {

					owner.addItem(s);

				}
				
				
				
				owner.setPopupVisible(true);
				
				System.out.println("여기서 바뀜? tc.getText() : " + tc.getText());
				owner.setEditable(true);
				owner.requestFocus();

				
			}

		});

		// When the text component changes, focus is gained

		// and the menu disappears. To account for this, whenever the focus

		// is gained by the JTextComponent and it has searchable values, we
		// show the popup.

		tc.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {

				// companyUpdate();

				if (tc.getText().length() > 0) {

					owner.setPopupVisible(true);

				}

			}

			@Override
			public void focusLost(FocusEvent arg0) {

			}

		});

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("insert Update");

		if (e.getDocument().getProperty("owner").equals("companyName")) {

			owner = guiAdvancedRepalcementPanel.getTxtCompanyName();
			Component c = owner.getEditor().getEditorComponent();
			tc = (JTextComponent) c;

			companyUpdate();

		} else if (e.getDocument().getProperty("owner").equals("siteName")) {
			owner = guiAdvancedRepalcementPanel.getTxtSiteName();

			Component c = owner.getEditor().getEditorComponent();
			tc = (JTextComponent) c;
			
			System.out.println("tc.getText() : " + tc.getText());
			siteUpdate();
		}

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("remove Update");

		if (e.getDocument().getProperty("owner").equals("companyName")) {

			owner = guiAdvancedRepalcementPanel.getTxtCompanyName();
			Component c = owner.getEditor().getEditorComponent();
			tc = (JTextComponent) c;

			companyUpdate();

		} else if (e.getDocument().getProperty("owner").equals("siteName")) {
			owner = guiAdvancedRepalcementPanel.getTxtSiteName();

			Component c = owner.getEditor().getEditorComponent();
			tc = (JTextComponent) c;
			
			System.out.println("tc.getText() : " + tc.getText());
			siteUpdate();
		}

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("changed Update");

	}

}
