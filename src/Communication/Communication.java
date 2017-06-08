package Communication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
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

					System.out.println("input : " + input);

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

					System.out.println("company Name 입력 받기 전 ");
					input = ConnectionSocket.bufferedReader.readLine();

					System.out.println("input : " + input);
					System.out.println("company Name 입력 받기 후 ");
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

	public void saveAttachFile(String rmaNumber, File selectedFile) {

		try {

			// 새로운 연결 소켓 열어서 파일 전송.
			Socket dataClient = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
			System.out.println("Client : connected");
			
			JSONObject attachFileObj = new JSONObject();

			attachFileObj.put("Action", "saveAttachFileInfo");
			attachFileObj.put("rmaNumber", rmaNumber);
			attachFileObj.put("attachFileName", selectedFile.getName());

			
			PrintStream printStream = new PrintStream(dataClient.getOutputStream());
			printStream.println(attachFileObj);

			FileInputStream fileInputStream = new FileInputStream(selectedFile);

			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

			OutputStream outputStream = dataClient.getOutputStream();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

			byte[] contents;
			long fileSize = selectedFile.length();

			System.out.println("fileSize : " + fileSize);
			long current = 0;

			while (current != fileSize) {
				int size = 10000;

				if (fileSize - current >= size) {

					current += size;
				} else {
					size = (int) (fileSize - current);
					current = fileSize;

				}
				contents = new byte[size];
				bufferedInputStream.read(contents, 0, size);
				outputStream.write(contents);

				System.out.print("Sending file ... " + (current * 100) / fileSize + "% complete!");

			}

			outputStream.flush();
			outputStream.close();

			fileInputStream.close();
			bufferedInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		//
		// FileInputStream fileInputStream = new FileInputStream(selectedFile);
		//
		// BufferedInputStream bufferedInputStream = new
		// BufferedInputStream(fileInputStream);
		//
		// OutputStream outputStream =
		// ConnectionSocket.getInstance().getOutputStream();
		// BufferedOutputStream bufferedOutputStream = new
		// BufferedOutputStream(outputStream);
		//
		// byte[] contents;
		// long fileSize = selectedFile.length();
		//
		// System.out.println("fileSize : " + fileSize);
		// long current = 0;
		//
		// while (current != fileSize) {
		// int size = 10000;
		//
		// if (fileSize - current >= size) {
		//
		// current += size;
		// } else {
		// size = (int) (fileSize - current);
		// current = fileSize;
		//
		// }
		// contents = new byte[size];
		// bufferedInputStream.read(contents, 0, size);
		// bufferedOutputStream.write(contents);
		//
		// System.out.print("Sending file ... " + (current * 100) / fileSize +
		// "% complete!");
		//
		// }
		//
		// bufferedOutputStream.flush();
		// bufferedOutputStream.close();
		//
		// fileInputStream.close();
		// bufferedInputStream.close();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}
}
