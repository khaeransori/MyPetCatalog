/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Item;

/**
 *
 * @author Khaer Ansori
 */
@ManagedBean
@SessionScoped
public class Catalog implements Serializable{
    
    @EJB
    private ItemFacade itemFacade;
    private Item item = null;
    private List<Item> items = null;
    private PagingInfo pagingInfo = null;

    /**
     * Creates a new instance of Catalog
     */
    public Catalog() {
        pagingInfo = new PagingInfo();
    }
    
    public PagingInfo getPagingInfo() {
        if(pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getItemCount());
        }
        return pagingInfo;
    }
    
    public List<Item> getNextItems(int maxResults, int firstResult) {
        return itemFacade.findRange(maxResults, firstResult);
    }
    
    public List<Item> getItems() {
        if(items == null) {
            getPagingInfo();
            items = getNextItems(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return items;
    }
    
    public Item getItem() {
        return item;
    }
    
    public String Next() {
        reset(false);
        getPagingInfo().nextPage();
        return "index";
    }
    
    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "index";
    }
    
    public String showDetail(Item item) {
        this.item = item;
        return "detail";
    }
    
    public int getItemCount() {
        return itemFacade.getItemCount();
    }
    
    private void reset(boolean resetFirstItem) {
        item = null;
        items = null;
        pagingInfo.setItemCount(-1);
        if(resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }
}
