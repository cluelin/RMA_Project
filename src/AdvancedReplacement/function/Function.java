package AdvancedReplacement.function;

import java.io.File;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import AdvancedReplacement.AdvancedReplacementOperation;
import AdvancedReplacement.GUIadvancedRepalcementPanel;
import Communication.Communication;

public class Function {

	public static ArrayList<File> selectedFile = new ArrayList<File>();
	AdvancedReplacementOperation advancedReplacementOperation = AdvancedReplacementOperation.getInstance();
	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = advancedReplacementOperation
			.getGUIadvancedReplacementPanel();

	private static Function instance = null;

	private Function() {

	}

	static public Function getInstance() {

		if (instance == null) {

			instance = new Function();
		}

		return instance;
	}

	public void saveRMADetailDataToServer() {

		JSONObject rmaDetailDataObj = advancedReplacementOperation.getRMAdetailJSONobj();

		if (rmaDetailDataObj != null) {
			Communication.getInstance().sendJSONobjToServer(rmaDetailDataObj);

			// 첨부파일 저장.
			for (int i = 0; i < selectedFile.size(); i++) {
				Communication.getInstance().saveAttachFile(advancedReplacementOperation.getTxtRMAnumber(),
						selectedFile.get(i));
			}

			// 저장되면 새로운 rma number를 할당받아야함.
			String rmaNumber = Communication.getInstance().getRMAnumberFromServer();
			AdvancedReplacementOperation.getInstance().setRMAnumber(rmaNumber);

			// 이전 RMA 히스토리 갱신.
			Communication.getInstance().showPreviousRMAList(
					guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem().toString(),
					guiAdvancedRepalcementPanel.getTxtSiteName().getSelectedItem().toString());

			// RMA detail 초기화
			guiAdvancedRepalcementPanel.clearRMADetail();

		}

	}

}
