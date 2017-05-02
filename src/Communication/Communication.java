package Communication;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import AdvancedReplacement.GUIadvancedRepalcementPanel;

public class Communication {

	static GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	public static Communication instance = null;
	JSONParser jsonParser = new JSONParser();

	private Communication() {

	}

	public static Communication getInstance() {

		if (instance == null) {
			instance = new Communication();
		}

		return instance;
	}

	// 2017.04.25
	// get RMA number from Server (Start with DA ~~)
	public String getRMAnumberFromServer() {

		String rmaNumber = null;

		JSONObject obj = new JSONObject();
		obj.put("Action", "requestRMAindex");

		// send request to serever
		ConnectionSocket.printStream.println(obj.toJSONString());

		try {

			// �޾ƿ� RMA number ����.
			String input = ConnectionSocket.bufferedReader.readLine();

			if (input != null) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(input);
				rmaNumber = "DA" + jsonObject.get("RMAindex").toString();
				System.out.println("RMA index : " + rmaNumber);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rmaNumber;

	}

	// 2017.04.25
	// check given rmaNumber was already Used or not.
	// return boolean
	public boolean rmaNumberAlreadyUsed(String rmaNumber) {
		JSONObject objToServer = new JSONObject();
		objToServer.put("Action", "checkRMAnumber");
		objToServer.put("rmaNumber", rmaNumber);

		ConnectionSocket.printStream.println(objToServer.toJSONString());

		try {

			// �޾ƿ� RMA number ����.
			String input = ConnectionSocket.bufferedReader.readLine();

			if (input != null) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(input);
				boolean rmaNumberAlreadyUsed = (boolean) jsonObject.get("rmaNumberAlreadyUsed");

				return rmaNumberAlreadyUsed;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2017.04.25
		// �̷��� ������ �Ǵ��� �𸣰���. Ȯ���̾���.
		return false;
	}

	// save RMA Information To Database
	public void saveRMAInformationToServer(JSONObject rmaDetailObj) {

		try {

			ConnectionSocket.printStream.println(rmaDetailObj.toJSONString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���� ���� ����");
		}

	}

	// 2017.04.21
	// get all of RMA information from server
	// and Return that information
	public JSONObject getRMAdetailFromServer(String rmaNumber) {

		JSONObject obj = new JSONObject();

		obj.put("Action", "requestRMADetail");

		obj.put("rmaNumber", rmaNumber);

		ConnectionSocket.printStream.println(obj.toJSONString());

		JSONParser jsonParser = new JSONParser();

		String input;

		try {

			input = ConnectionSocket.bufferedReader.readLine();

			JSONObject RMADetailObject = (JSONObject) jsonParser.parse(input);

			return RMADetailObject;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// companyName�� ��ġ�ϴ� ȸ���� ������ ������
	public JSONObject getCompanyDetailJSON(String companyName) {

		JSONObject sendJSONobj = new JSONObject();

		sendJSONobj.put("Action", "requestCompanyDetail");

		sendJSONobj.put("companyName", companyName);

		ConnectionSocket.printStream.println(sendJSONobj.toJSONString());

		JSONParser jsonParser = new JSONParser();

		String input;

		try {

			input = ConnectionSocket.bufferedReader.readLine();

			JSONObject companyDetailObject = (JSONObject) jsonParser.parse(input);

			return companyDetailObject;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// �����κ��� siteName�� ���� ������ �˻��� ������ �ͼ� ����� return.
	public List<String> getSiteNameListFromServer(String partialSiteName, String companyName) {

		List<String> resultArryList = null;

		try {

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestSiteName");

			obj.put("companyName", companyName);
			obj.put("siteName", partialSiteName);

			ConnectionSocket.printStream.println(obj.toJSONString());

			JSONParser jsonParser = new JSONParser();

			String input;

			resultArryList = new ArrayList<>();

			while (true) {

				try {

					input = ConnectionSocket.bufferedReader.readLine();

					System.out.println("get site input : " + input);
					if (input == null || input.equals("end")) {
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
		}

		return resultArryList;

	}

	// ������ ���� keyword�� �ش��ϴ� Item �˻�
	public List<String> getItemNameFromServer(String partialItemName) {

		ArrayList resultArryList = new ArrayList<String>();

		try {

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestItemName");
			obj.put("itemName", partialItemName);

			ConnectionSocket.printStream.println(obj.toJSONString());

			JSONParser jsonParser = new JSONParser();

			String input;

			while (true) {

				try {

					input = ConnectionSocket.bufferedReader.readLine();

					if (input.equals("end")) {
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
			// Client.closeConnection();
		}

		return resultArryList;

	}

	// CompanyName���� �˻��ؼ� �ش� company���� ��û�� RMA�� �˻��ؼ� �ѷ���.
	public void showPreviousRMAList(String targetName) {

		try {

			guiAdvancedRepalcementPanel.clearHistoryPanel();

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestSearchRelatedRMA");
			obj.put("companyName", targetName);

			ConnectionSocket.printStream.println(obj.toJSONString());

			while (true) {

				try {
					String input = ConnectionSocket.bufferedReader.readLine();

					System.out.println("input : " + input);
					
					if (input.equals("end")) {
						break;
					}

					JSONParser jsonParser = new JSONParser();

					JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

					String rmaNumber = jsonObject.get("RMAnumber").toString();
					String rmaDate = jsonObject.get("RMAdate").toString();
					String rmaContents = jsonObject.get("RMAcontents").toString();

					// history panel�� ��� ���.
					guiAdvancedRepalcementPanel.setRelatedRMAInformation(rmaNumber, rmaDate, rmaContents);

					System.out.println("rmaNumber : " + rmaNumber + " rmaContents : " + rmaContents);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

	}

	// keyword �� �����ϴ� Company Name�� ��������.
	// �ش� company�� ���� RMA ������ ��ȸ�� ����� return �Ѵ�.
	public List<String> getCompanyNameListFromServer(String partialCompanyName) {

		List<String> resultArryList = null;
		try {

			JSONObject obj = new JSONObject();

			obj.put("Action", "requestCompanyName");

			obj.put("companyName", partialCompanyName);

			ConnectionSocket.printStream.println(obj.toJSONString());

			JSONParser jsonParser = new JSONParser();

			String input;

			resultArryList = new ArrayList<>();

			while (true) {

				try {

					System.out.println("company Name �Է� �ޱ� �� ");
					input = ConnectionSocket.bufferedReader.readLine();

					System.out.println("input : " + input);
					System.out.println("company Name �Է� �ޱ� �� ");
					if (input == null || input.equals("end")) {
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
		}

		return resultArryList;
	}
}
