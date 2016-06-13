/*Used to fill pdf forms with xml format data*/
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.adobe.pdf.PDFDocument;
import com.adobe.pdf.PDFFactory;

public class PDFAssembler {

	public static String genPDF(String formTemplate,String xmlStr,String resultpdf){
		   String result = resultpdf;
		   try{
				InputStream form = new FileInputStream(formTemplate);
				PDFDocument  document = PDFFactory.openDocument(form);
				
				InputStream in =  new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));//new FileInputStream(xmlfile);
				document.importFormData(in);
				
				//Call the PDFDocument object's saveAsXDP method
				InputStream XDPIS = document.save(); //
				//Get the byte size of the InputStream object
		        int numBytes = XDPIS.available();
		        //Create an array of bytes and allocate numBytes of memory
		        byte[] XDPBytes = new byte[numBytes];
		        //Populate the byte array by calling the InputStream object’s read method
		        XDPIS.read(XDPBytes);
		        //Create an XDP file named Test.xdp and place it in the root of C
		        File myFile = new File(resultpdf);
		        //Create a FileOutputStream object
		        FileOutputStream myFileW = new FileOutputStream(myFile);
		        //Call the FileOutputStream object's write method
		        myFileW.write(XDPBytes);
		        //Close the FileOutputStream object
		        myFileW.close();
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   return result ;
		
	}
}
