package spring_boot.generate.DOCX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import spring_boot.generate.Day;
import spring_boot.generate.Sales;
import spring_boot.generate.Total;

public class SalesDocx extends AbstractGenerateDocx<Sales> implements GenerateDocx{
	
	private Day ngay;
	private List<Sales> list;
	private Total total;
	
	public SalesDocx(Date date, List<Sales> list, Total total) {
		this.list = addNo(list);
		ngay = new Day(date);
		this.total = total;
	}

	@Override
	public String generateDocx() {
		InputStream in;
		try {
			in = new FileInputStream("D:/ict/Java/program/spring_security/src/main/resources/template/DOCX/Sales.docx");
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
			 
	        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
	        fieldsMetadata.load("n", Day.class);
	        fieldsMetadata.load("dt", Sales.class, true);
	        fieldsMetadata.load("t", Total.class);
	        
	        IContext context = report.createContext();
	        context.put("n", ngay);
	        context.put("dt", list);
	        context.put("t", total);
	        
	        String file = "D:/ict/Java/program/spring_security/src/main/resources/template/genDocx/sales/Sales_" + format.format(new Date()) + ".docx";
	        
	        OutputStream out = new FileOutputStream(new File(file));
	        report.process(context, out);
	        in.close();
	        return file;
		} catch (IOException | XDocReportException e) {
			e.printStackTrace();
			return "";
		}
	}

}
