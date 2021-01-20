package br.com.empresa.exemploqrcode;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Auth authProd = new Auth();
    	String access_token;
    	
    	Cob cobranca = new Cob();
    	String resultCob="";
    	int idCob=0;
        
    	Loc loc = new Loc();
    	String resultLoc;
    	String qrCode="";
    	String image="";
    	String imageName;
        
    	//----------------------------------------------------
    	//Autenticar
    	access_token = authProd.geraToken();
    	System.out.println("access_token = "+access_token);
    	
    	//----------------------------------------------------
    	//Criar uma cobrança autenticada
    	resultCob = cobranca.doCob(access_token);
    	idCob = cobranca.getIdCob(resultCob);
    	System.out.println("idCobranca = "+idCob);
    	
    	//----------------------------------------------------
    	//Emissão do QRCode de um location
    	resultLoc = loc.genQrCode(idCob, access_token);
    	qrCode = loc.getQrCode(resultLoc);
    	System.out.println("qrCode = "+qrCode);
    	
    	//----------------------------------------------------
    	//Salvar e exibir a imagem do QRCode
    	image = loc.getImage(resultLoc);
    	System.out.println("image = "+image);
    	imageName=loc.saveImage(image); //salvando a imagem
    	loc.showImage(imageName); //exibindo a imagem
    	
    	
    }
}
