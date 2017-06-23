package Communication;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
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

			// 받아온 RMA number 설정.
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

			// 받아온 RMA number 설정.
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
		// 이렇게 끝내도 되는지 모르겠음. 확신이없다.
		return false;
	}

	// save RMA Information To Database
	public void saveRMAInformationToServer(JSONObject rmaDetailObj) {

		try {

			ConnectionSocket.printStream.println(rmaDetailObj.toJSONString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("서버 연결 에러");
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

	// companyName과 일치하는 회사의 정보를 가져옴
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

	// 서버로부터 siteName에 대한 정보를 검색해 가지고 와서 결과를 return.
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

	// 서버로 부터 keyword에 해당하는 Item 검색
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

	// CompanyName으로 검색해서 해당 company에서 신청한 RMA를 검색해서 뿌려줌.
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

					if (input.equals("end")) {
						break;
					}

					JSONParser jsonParser = new JSONParser();

					JSONObject jsonObject = (JSONObject) jsonParser.parse(input);

					String rmaNumber = jsonObject.get("RMAnumber").toString();
					String rmaDate = jsonObject.get("RMAdate").toString();
					String rmaContents = jsonObject.get("RMAcontents").toString();
					String rmaDelivered = jsonObject.get("RMAdelivered").toString();

					boolean rmaDeliveredBool = false;

					if (rmaDelivered != null && rmaDelivered.equals("true")) {
						rmaDeliveredBool = true;
					}

					// history panel에 결과 출력.
					guiAdvancedRepalcementPanel.setRelatedRMAInformation(rmaNumber, rmaDate, rmaContents,
							rmaDeliveredBool);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

	}

	// keyword 로 시작하는 Company Name을 가져오고.
	// 해당 company의 이전 RMA 내역을 조회한 결과를 return 한다.
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

					input = ConnectionSocket.bufferedReader.readLine();

					System.out.println("input : " + input);
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

	public JSONObject getItemTableValidObject(JSONObject validationObject) {

		JSONObject itemValidationObject = null;

		ConnectionSocket.printStream.println(validationObject);

		try {

			String input = ConnectionSocket.bufferedReader.readLine();

			itemValidationObject = (JSONObject) jsonParser.parse(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemValidationObject;
	}

	// 파일 하나 저장.
	public void saveAttachFile(String rmaNumber, File selectedFile) {

		System.out.println("저장하는 파일 이름 : " + selectedFile);

		try {

			JSONObject attachFileObj = new JSONObject();

			attachFileObj.put("Action", "saveAttachFileInfo");
			attachFileObj.put("rmaNumber", rmaNumber);

			attachFileObj.put("attachFileName", selectedFile.getName());

			ConnectionSocket.printStream.println(attachFileObj);

			ConnectionSocket.printStream.flush();

			Socket dataPassSock = new Socket(ServerInformation.SERVER_IP, ServerInformation.DATA_PASS_PORT);

			FileInputStream fileInputStream = new FileInputStream(selectedFile);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

			// Get socket's output stream
			OutputStream outputStream = dataPassSock.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

			// Read File Contents into contents array
			byte[] contents = new byte[10000];

			long current = 0;
			int bufferSize = contents.length;
			long fileLength = selectedFile.length();

			// until file length.
			while (current != fileLength) {

				if (fileLength - current >= bufferSize)
					current += bufferSize;
				else {
					bufferSize = (int) (fileLength - current);
					current += bufferSize;
				}

				bufferedInputStream.read(contents, 0, bufferSize);
				dataOutputStream.write(contents, 0, bufferSize);
				System.out.println("contents.length : " + contents.length);
				System.out.println("Sending file ... " + (current * 100) / fileLength + "% complete!");

			}

			dataOutputStream.flush();
			dataOutputStream.close();
			bufferedInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
