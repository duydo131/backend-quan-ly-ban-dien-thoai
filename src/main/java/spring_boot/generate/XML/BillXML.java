package spring_boot.generate.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import spring_boot.generate.CustomerBill;
import spring_boot.generate.InfoPay;
import spring_boot.generate.Day;

public class BillXML {
		public static void main(String[] args) throws XDocReportException,IOException {
		     
	        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

	        fieldsMetadata.load("n", Day.class);
	        fieldsMetadata.load("p", InfoPay.class, true);
	        fieldsMetadata.load("c", CustomerBill.class);
	         
	        File xmlFieldsFile = new File("D:/ict/Java/program/spring_security/src/main/resources/template/XML/Bill/Bill.fields.xml");
	        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
	    }
}
