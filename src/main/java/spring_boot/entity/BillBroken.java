package spring_boot.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill_broken")
public class BillBroken extends Bill{
	
	@OneToOne
    @JoinColumn(name = "list_broken_id")
    private ListBroken list_broken;
	
	@OneToMany(mappedBy = "bill_broken")
	private List<Guarantee> list_guarantee;

	public ListBroken getList_broken() {
		return list_broken;
	}

	public void setList_broken(ListBroken list_broken) {
		this.list_broken = list_broken;
	}

	public List<Guarantee> getList_guarantee() {
		return list_guarantee;
	}

	public void setList_guarantee(List<Guarantee> list_guarantee) {
		this.list_guarantee = list_guarantee;
	}
}
