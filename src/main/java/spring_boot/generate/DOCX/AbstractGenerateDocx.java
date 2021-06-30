package spring_boot.generate.DOCX;

import java.text.SimpleDateFormat;
import java.util.List;

import spring_boot.generate.Docx;

public abstract class AbstractGenerateDocx<T extends Docx> implements GenerateDocx {
	protected SimpleDateFormat format = new SimpleDateFormat("E_dd_MMM_yyyy_HH_mm_ss_SSS_z");
	
	public List<T> addNo(List<T> list){
		if(list.isEmpty()) return list;
		for(int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			t.setStt(i + 1);
		}
		return list;
	}
}
