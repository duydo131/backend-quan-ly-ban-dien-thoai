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
import spring_boot.generate.CustomerBill;
import spring_boot.generate.Day;
import spring_boot.generate.InfoPay;

public class BillDocx extends AbstractGenerateDocx<InfoPay> implements GenerateDocx{
	
	private Day ngay;
	private List<InfoPay> list;
	private CustomerBill info;
	
	public BillDocx(Date date, List<InfoPay> list, CustomerBill info) {
		this.list = addNo(list);
		ngay = new Day(date);
		this.info = info;
	}

	@Override
	public String generateDocx() {
		InputStream in;
		try {
			in = new FileInputStream("D:/ict/Java/program/spring_security/src/main/resources/template/DOCX/Bill.docx");
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
			 
	        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
	        fieldsMetadata.load("n", Day.class);
	        fieldsMetadata.load("p", InfoPay.class, true);
	        fieldsMetadata.load("c", CustomerBill.class);
	        
	        IContext context = report.createContext();
	        context.put("n", ngay);
	        context.put("p", list);
	        context.put("c", info);
	        
	        String file = "D:/ict/Java/program/spring_security/src/main/resources/template/genDocx/bills/Bill_" + format.format(new Date()) + ".docx";
	        
	        OutputStream out = new FileOutputStream(new File(file));
	        report.process(context, out);
	        return file;
		} catch (IOException | XDocReportException e) {
			e.printStackTrace();
			return "";
		}
	}

}
