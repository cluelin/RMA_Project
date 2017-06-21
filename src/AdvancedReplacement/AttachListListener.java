package AdvancedReplacement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JList;

import org.json.simple.JSONObject;

import Communication.ServerInformation;

public class AttachListListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		JList list = (JList) e.getSource();
		if (e.getClickCount() == 2) {

			// Double-click detected
			int index = list.locationToIndex(e.getPoint());
			System.out.println("index : " + index);

			if (list.getSelectedValue() != null)
				getFileFromServer();
			
		} else if (e.getClickCount() == 3) {

			// Triple-click detected
			int index = list.locationToIndex(e.getPoint());
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void getFileFromServer() {
		// index에 있는 파일 이름을 서버에 전송해서 해당 파일 복사해오기.
		String fileName = GUIadvancedRepalcementPanel.getInstance().getAttachmentList().getSelectedValue();

		String fileSavedName = GUIadvancedRepalcementPanel.getInstance().getTxtRMAnumber().getText() + fileName;

		System.out.println("fileName : " + fileSavedName);

		JSONObject fileNameObj = new JSONObject();
		fileNameObj.put("Action", "getAttachFileInfo");

		fileNameObj.put("fileName", fileSavedName);

		try {
			Socket fileLoadSock = new Socket(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
			System.out.println("Client : connected");

			PrintStream printStream = new PrintStream(fileLoadSock.getOutputStream());
			printStream.println(fileNameObj);

			// 파일 저장

			File fileDir = new File("AttachFile");

			if (!fileDir.exists()) {
				try {
					fileDir.mkdirs();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

			byte[] contents = new byte[10000];

			// Initialize the FileOutputStream to the output file's full path.
			FileOutputStream fileOutputStream = new FileOutputStream(fileDir + "/" + fileName);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

			InputStream inputStream = fileLoadSock.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

			// No of bytes read in one read() call
			int bytesRead = 0;

			while ((bytesRead = inputStream.read(contents)) != -1)
				bufferedOutputStream.write(contents, 0, bytesRead);

			bufferedOutputStream.flush();
			bufferedInputStream.close();
			fileOutputStream.close();

			System.out.println("파일 저장완료");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
