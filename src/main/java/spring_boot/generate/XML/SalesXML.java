package spring_boot.generate.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import spring_boot.generate.Day;
import spring_boot.generate.Sales;
import spring_boot.generate.Total;

public class SalesXML {
	public static void main(String[] args) throws XDocReportException,IOException {
	     
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        fieldsMetadata.load("n", Day.class);
        fieldsMetadata.load("dt", Sales.class, true);
        fieldsMetadata.load("t", Total.class);
        
        File xmlFieldsFile = new File("D:/ict/Java/program/spring_security/src/main/resources/template/XML/Sales/Sales.fields.xml");
        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
    }
}
