package br.com.empresa.exemploqrcode;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class Loc {
	
	public String qRCode;
	public String image;
	
	public String genQrCode(int idCob, String access_token) {
		String result="";
		StringBuilder responseBuilder = new StringBuilder();
    	HttpsURLConnection conn = null;
    	try {
    		URL url = new URL ("https://api-pix.gerencianet.com.br/v2/loc/"+idCob+"/qrcode"); //Para ambiente de Produção              
	        conn = (HttpsURLConnection)url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer "+ access_token);

	        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(reader);

	        String response;        
	        while ((response = br.readLine()) != null) {
	          //System.out.println("Recebido "+response);
	          responseBuilder.append(response);
	        }
	        result=responseBuilder.toString();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na geração do QrCode");
			e.printStackTrace();
		}
    	
    	return result;
		
	}
	
	public String getQrCode(String loc) {
		try {
    		JSONObject jsonObject = new JSONObject(loc);
    		this.qRCode = jsonObject.getString("qrcode");
		} catch (Exception e) {
			System.out.println("Erro na obtenção do QrCode do loc");
			e.printStackTrace();
		}
		return this.qRCode;
	}
	
	public String getImage(String loc) {
		try {
    		JSONObject jsonObject = new JSONObject(loc);
			this.image = jsonObject.getString("imagemQrcode");
		} catch (Exception e) {
			System.out.println("Erro na obtenção do QrCode do loc");
			e.printStackTrace();
		}
		return this.image;
	}
	
	public String saveImage(String image) {
		long timeMilis=System.currentTimeMillis();
		String fileName = "";
		String base64Image = image.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		try {
			fileName="image_"+timeMilis+"_.png";
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
			File outputfile = new File(fileName);
			ImageIO.write(img, "png", outputfile);
		} catch (Exception e) {
			System.out.println("Erro ao salvar imagem");
			e.printStackTrace();
			fileName = "";
		}
		return fileName;	
	}
	
	public void showImage(String fileName) {
		try {
			File file = new File(fileName);
		    Desktop desktop = Desktop.getDesktop();
		    desktop.open(file);
		} catch (Exception e) {
			System.out.println("Erro ao abrir imagem");
			e.printStackTrace();
		}
	}

}
