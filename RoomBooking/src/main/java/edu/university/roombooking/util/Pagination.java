package edu.university.roombooking.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class Pagination<T> extends PagedListHolder<T> {	

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init(){
		setPageSize(10);
		setMaxLinkedPages(100000000);
	}

	public void preparePage(Model model,List<T> list,Integer page,String attributeName){

		setSource(list);

		int max=getLastLinkedPage();		

		if((page==null)||(page<=0)){
			page=0;
		}else if(page>max){
			page=max;
		}else{
			page-=1;
		}

		setPage(page);

		model.addAttribute("lastPage",max+1);
		model.addAttribute("actualPage",page+1);

		model.addAttribute(attributeName,this.getPageList());
	}
}
