package Practica3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Prcatica3 {
	public static final String DOCUMENTO_XML = "C:\\Users\\mrmar\\OneDrive\\Escritorio\\Universidad\\1ºCuatri\\Desarrollo E Integración De Software\\EjemploStAX-main\\Práctica\\empleados.xml";
    public static final String SALARIO = "salario";
    public static final String NOMBRE = "nombre";
    
    
	public static List<String> numeroDePlazas() throws FileNotFoundException, IOException {
		System.out.println("Iniciando el documento");
		List<String> resul = new ArrayList<String>();
		String nombreTemp = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;

        try {
            inputStream = new FileInputStream(DOCUMENTO_XML);
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                
                if(xmlStreamReader.isStartElement() && NOMBRE.equals(xmlStreamReader.getLocalName())) {
                   nombreTemp=xmlStreamReader.getElementText();

                }
                if (xmlStreamReader.isStartElement() && SALARIO.equals(xmlStreamReader.getLocalName())) {
                    String salarioTemp=xmlStreamReader.getElementText();
                    if (Integer.parseInt(salarioTemp)>=30000) {
                    	resul.add(nombreTemp);
                    }
                    

                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {try {
            if (xmlStreamReader != null) xmlStreamReader.close();
            if (inputStream != null) inputStream.close();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        }
        System.out.println("Fin del documento");
        return resul;
	}
	public static void main(String[] args) {	
        try {
        	System.out.println("Empleados con salario mayor a 30000: "+ numeroDePlazas());
        } catch (IOException e) {
            e.printStackTrace();
        }


	}


}
