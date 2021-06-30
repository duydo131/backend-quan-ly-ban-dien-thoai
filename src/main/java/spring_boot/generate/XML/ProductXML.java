package spring_boot.generate.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import spring_boot.generate.Day;
import spring_boot.generate.ProductSales;

public class ProductXML {
	public static void main(String[] args) throws XDocReportException,IOException {
	     
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        fieldsMetadata.load("n", Day.class);
        fieldsMetadata.load("p", ProductSales.class, true);
         
        File xmlFieldsFile = new File("D:/ict/Java/program/spring_security/src/main/resources/template/XML/ListProduct/Product.fields.xml");
        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
    }
}
